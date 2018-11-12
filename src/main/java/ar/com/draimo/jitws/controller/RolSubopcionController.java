package ar.com.draimo.jitws.controller;

import ar.com.draimo.jitws.constant.RutaConstant;
import ar.com.draimo.jitws.dto.RolSubopcionDTO;
import ar.com.draimo.jitws.exception.CodigoRespuesta;
import ar.com.draimo.jitws.exception.EstadoRespuesta;
import ar.com.draimo.jitws.exception.MensajeRespuesta;
import ar.com.draimo.jitws.model.RolSubopcion;
import ar.com.draimo.jitws.model.Submodulo;
import ar.com.draimo.jitws.model.Subopcion;
import ar.com.draimo.jitws.service.MenuService;
import ar.com.draimo.jitws.service.RolSubopcionService;
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
public class RolSubopcionController {
    
    //Define la url
    private final String URL = RutaConstant.URL_BASE + "/rolsubopcion";
    //Define la url de subcripciones a sockets
    private final String TOPIC = RutaConstant.URL_TOPIC + "/rolsubopcion";
    
    //Define el template para el envio de datos por socket
    @Autowired
    private SimpMessagingTemplate template;
    
    //Crea una instancia del servicio
    @Autowired
    RolSubopcionService elementoService;

    //Crea una instancia del servicio menu
    @Autowired
    MenuService menuService;
    
    //Obtiene la lista completa
    @GetMapping(value = URL)
    @ResponseBody
    public List<RolSubopcion> listar() {
        return elementoService.listar();
    }
    
    //Obtiene una lista por rol y modulo
    @GetMapping(value = URL + "/listarPorRolModulo/{idRol}/{idModulo}")
    @ResponseBody
    public List<Submodulo> listarPorRolModulo(@PathVariable int idRol, @PathVariable int idModulo) {
        return elementoService.listarPorRolModulo(idRol, idModulo);
    }
    
    //Obtiene una lista por rol y submodulo
    @GetMapping(value = URL + "/listarPorRolSubmodulo/{idRol}/{idSubmodulo}")
    @ResponseBody
    public List<Subopcion> listarPorRolSubmodulo(@PathVariable int idRol, @PathVariable int idSubmodulo) {
        return elementoService.listarPorRolSubmodulo(idRol, idSubmodulo);
    }
    
    //Actualiza un registro
    @PutMapping(value = URL)
    public ResponseEntity<?> actualizar(@RequestBody RolSubopcionDTO elemento) {
        try {
            elementoService.actualizar(elemento);
            //Envia la nueva lista a los usuarios subscriptos
            template.convertAndSend(TOPIC + "/lista", elementoService.listar());
            return new ResponseEntity<>(new EstadoRespuesta(CodigoRespuesta.OK, 
                    MensajeRespuesta.ACTUALIZADO), HttpStatus.OK);
        } catch (ObjectOptimisticLockingFailureException oolfe) {
            //Retorna codigo y mensaje de error de operacion actualizada por otra transaccion
            return new ResponseEntity<>(new EstadoRespuesta(CodigoRespuesta.TRANSACCION_NO_ACTUALIZADA,
                    MensajeRespuesta.TRANSACCION_NO_ACTUALIZADA), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch(MessagingException e) {
            //Retorna codigo y mensaje de error de sicronizacion mediante socket
            return new ResponseEntity<>(new EstadoRespuesta(CodigoRespuesta.ERROR_SINC_SOCKET,
                    MensajeRespuesta.ERROR_SINC_SOCKET), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch(Exception e) {
            //Retorna codigo y mensaje de error interno en el servidor
            return new ResponseEntity<>(new EstadoRespuesta(CodigoRespuesta.ERROR_INTERNO_SERVIDOR,
                    MensajeRespuesta.ERROR_INTERNO_SERVIDOR), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    //Agrega una subopcion a todos los roles
    @GetMapping(value = URL + "/agregarSubopcionARoles")
    @ResponseBody
    public void agregarSubopcionARoles(@RequestBody Subopcion elemento) {
        elementoService.agregarSubopcionARoles(elemento);
    }
    
    //Elimina una subopcion por id de todos los roles
    @GetMapping(value = URL + "/eliminarSubopcionDeRoles/{idSubopcion}")
    @ResponseBody
    public void eliminarSubopcionDeRoles(@PathVariable int idSubopcion) {
        elementoService.eliminarSubopcionDeRoles(idSubopcion);
    }
    
    /*
     * Asigna todas las subopciones a cada uno de los roles, eliminando todo los
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
