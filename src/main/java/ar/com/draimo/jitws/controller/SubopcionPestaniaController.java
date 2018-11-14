package ar.com.draimo.jitws.controller;

import ar.com.draimo.jitws.constant.RutaConstant;
import ar.com.draimo.jitws.dto.SubopcionPestaniaDTO;
import ar.com.draimo.jitws.exception.CodigoRespuesta;
import ar.com.draimo.jitws.exception.EstadoRespuesta;
import ar.com.draimo.jitws.exception.MensajeRespuesta;
import ar.com.draimo.jitws.model.Pestania;
import ar.com.draimo.jitws.model.SubopcionPestania;
import ar.com.draimo.jitws.service.SubopcionPestaniaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Clase RolSubopcion Controller
 * @author blas
 */

@RestController
public class SubopcionPestaniaController {
    
    //Define la url
    private final String URL = RutaConstant.URL_BASE + "/subopcionpestania";
    //Define la url de subcripciones a sockets
    private final String TOPIC = RutaConstant.URL_TOPIC + "/subopcionpestania";
    
    //Define el template para el envio de datos por socket
    @Autowired
    private SimpMessagingTemplate template;
    
    //Crea una instancia del servicio
    @Autowired
    SubopcionPestaniaService elementoService;
    
    //Obtiene la lista completa
    @GetMapping(value = URL)
    @ResponseBody
    public List<SubopcionPestania> listar() {
        return elementoService.listar();
    }
    
    //Obtiene una lista por rol y subopcion
    @GetMapping(value = URL + "/listarPorRolSubopcion/{idRol}/{idSubopcion}")
    @ResponseBody
    public List<Pestania> listarPorRolSubopcion(@PathVariable int idRol, @PathVariable int idSubopcion) {
        return elementoService.listarPorRolSubopcion(idRol, idSubopcion);
    }
    
    //Actualiza un registro
    @PutMapping(value = URL)
    public ResponseEntity<?> actualizar(@RequestBody SubopcionPestaniaDTO elemento) {
        try {
            elementoService.actualizar(elemento);
            //Envia la nueva lista a los usuarios subscriptos
            template.convertAndSend(TOPIC + "/lista", elementoService.listar());
            return new ResponseEntity(new EstadoRespuesta(CodigoRespuesta.OK, 
                    MensajeRespuesta.ACTUALIZADO), HttpStatus.OK);
        } catch (ObjectOptimisticLockingFailureException oolfe) {
            //Retorna codigo y mensaje de error de operacion actualizada por otra transaccion
            return new ResponseEntity(new EstadoRespuesta(CodigoRespuesta.TRANSACCION_NO_ACTUALIZADA,
                    MensajeRespuesta.TRANSACCION_NO_ACTUALIZADA), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch(MessagingException e) {
            //Retorna codigo y mensaje de error de sicronizacion mediante socket
            return new ResponseEntity(new EstadoRespuesta(CodigoRespuesta.ERROR_SINC_SOCKET,
                    MensajeRespuesta.ERROR_SINC_SOCKET), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch(Exception e) {
            //Retorna codigo y mensaje de error interno en el servidor
            return new ResponseEntity(new EstadoRespuesta(CodigoRespuesta.ERROR_INTERNO_SERVIDOR,
                    MensajeRespuesta.ERROR_INTERNO_SERVIDOR), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    //Asigna todas las pestañas a cada una de las subopciones
    @GetMapping(value = URL + "/asignarPestaniasASubopciones")
    @ResponseBody
    public void asignarPestaniasASubopciones() {
        elementoService.asignarPestaniasASubopciones();
    }
    
    /*
     * Asigna todas las pestañas a cada una de las subopciones, eliminando todo los
     * datos y reestableciendo desde cero
     */
    @GetMapping(value = URL + "/reestablecerTablaDesdeCero")
    @ResponseBody
    public ResponseEntity<?> reestablecerTablaDesdeCero() {
        elementoService.reestablecerTablaDesdeCero();
        try {
            //Envia la nueva lista a los usuarios subscriptos
            template.convertAndSend(TOPIC + "/listarMenu", 1);
            return new ResponseEntity<>(new EstadoRespuesta(CodigoRespuesta.OK, 
                    MensajeRespuesta.TABLA_REESTABLECIDA), HttpStatus.OK);
        } catch(MessagingException e) {
            //Retorna codigo y mensaje de error de sicronizacion mediante socket
            return new ResponseEntity<>(new EstadoRespuesta(CodigoRespuesta.ERROR_SINC_SOCKET,
                    MensajeRespuesta.ERROR_SINC_SOCKET), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
