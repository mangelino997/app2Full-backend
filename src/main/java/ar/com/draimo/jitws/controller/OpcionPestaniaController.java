package ar.com.draimo.jitws.controller;

import ar.com.draimo.jitws.constant.RutaConstant;
import ar.com.draimo.jitws.dto.OpcionPestaniaDTO;
import ar.com.draimo.jitws.exception.MensajeRespuesta;
import ar.com.draimo.jitws.model.Pestania;
import ar.com.draimo.jitws.service.OpcionPestaniaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Clase OpcionPestania Controller
 * @author blas
 */

@RestController
public class OpcionPestaniaController {
    
    //Define la url
    private final String URL = RutaConstant.URL_BASE + "/opcionpestania";
    //Define la url de subcripciones a sockets
    private final String TOPIC = RutaConstant.URL_TOPIC + "/opcionpestania";
    
    //Define el template para el envio de datos por socket
    @Autowired
    private SimpMessagingTemplate template;
    
    //Crea una instancia del servicio
    @Autowired
    OpcionPestaniaService elementoService;
    
    //Obtiene una lista por rol y opcion
    @GetMapping(value = URL + "/listarPorRolOpcion/{idRol}/{idOpcion}")
    @ResponseBody
    public List<Pestania> listarPorRolOpcion(@PathVariable int idRol, @PathVariable int idOpcion) {
        return elementoService.listarPorRolOpcion(idRol, idOpcion);
    }
    
    //Actualiza un registro
    @PutMapping(value = URL)
    public ResponseEntity<?> actualizar(@RequestBody OpcionPestaniaDTO elemento) {
        try {
            //Actualiza el registro
            elementoService.actualizar(elemento);
            //Retorna mensaje de actualizado con exito
            return MensajeRespuesta.actualizado();
        } catch (DataIntegrityViolationException dive) {
            //Retorna mensaje de dato duplicado
            return MensajeRespuesta.datoDuplicado(dive);
        } catch (JpaObjectRetrievalFailureException jorfe) {
            //Retorna mensaje de dato Inexistente
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
    
    //Asigna todas las pestañas a cada una de las opciones
    @PostMapping(value = URL + "/asignarPestaniasAOpciones")
    public ResponseEntity<?> asignarPestaniasAOpciones() {
        try {
            elementoService.asignarPestaniasAOpciones();
               return MensajeRespuesta.asignado();
        }catch(MessagingException e) {
            //Retorna codigo y mensaje de error de sicronizacion mediante socket
            return MensajeRespuesta.errorSincSocket();
        }
    }
    
}
