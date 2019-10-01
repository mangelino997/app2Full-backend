package ar.com.draimo.jitws.controller;

import ar.com.draimo.jitws.constant.RutaConstant;
import ar.com.draimo.jitws.dto.SubopcionPestaniaDTO;
import ar.com.draimo.jitws.exception.MensajeRespuesta;
import ar.com.draimo.jitws.model.Pestania;
import ar.com.draimo.jitws.model.SubopcionPestania;
import ar.com.draimo.jitws.service.SubopcionPestaniaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Clase SubopcionPestania Controller
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
        return elementoService.listarPestaniasPorRolYSubopcion(idRol, idSubopcion);
    }
    
    //Obtiene una lista por rol y subopcion para actualizar estado mostrar
    @GetMapping(value = URL + "/obtenerPestaniasPorRolYSubopcion/{idRol}/{idSubopcion}")
    @ResponseBody
    public SubopcionPestaniaDTO obtenerPestaniasPorRolYSubopcion(@PathVariable int idRol, @PathVariable int idSubopcion) {
        return elementoService.obtenerPestaniasPorRolYSubopcion(idRol, idSubopcion);
    }
    
    //Actualiza un registro
    @PutMapping(value = URL)
    public ResponseEntity<?> actualizar(@RequestBody SubopcionPestaniaDTO elemento) {
        try {
            //Actualiza el registro
            elementoService.actualizar(elemento);
            //Envia la nueva lista a los usuarios subscripto
            //template.convertAndSend(TOPIC + "/lista", elementoService.listar());
            //Retorna mensaje de actualizado con exito
            return MensajeRespuesta.actualizado();
        } catch (DataIntegrityViolationException dive) {
            //Retorna mensaje de dato duplicado
            return MensajeRespuesta.datoDuplicado(dive);
        } catch (JpaObjectRetrievalFailureException jorfe) {
            //Retorna mensaje de dato inexistente
            return MensajeRespuesta.datoInexistente("a", jorfe.getMessage());
        } catch(ObjectOptimisticLockingFailureException oolfe) {
            //Retorna mensaje de transaccion no actualizada
            return MensajeRespuesta.transaccionNoActualizada();
        }catch(MessagingException e) {
            //Retorna codigo y mensaje de error de sicronizacion mediante socket
            return MensajeRespuesta.errorSincSocket();
        } catch(Exception e) {
            //Retorna mensaje de error interno en el servidor
            return MensajeRespuesta.error();
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
        try {
            elementoService.reestablecerTablaDesdeCero();
            //Envia la nueva lista a los usuarios subscriptos
            //template.convertAndSend(TOPIC + "/listarMenu", 1);
               return MensajeRespuesta.tablaReestablecida();
        }catch(MessagingException e) {
            //Retorna codigo y mensaje de error de sicronizacion mediante socket
            return MensajeRespuesta.errorSincSocket();
        }
    }
    
}
