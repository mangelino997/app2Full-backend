package ar.com.draimo.jitws.controller;

import ar.com.draimo.jitws.constant.RutaConstant;
import ar.com.draimo.jitws.dto.RolSubopcionDTO;
import ar.com.draimo.jitws.exception.MensajeRespuesta;
import ar.com.draimo.jitws.model.RolSubopcion;
import ar.com.draimo.jitws.model.Submodulo;
import ar.com.draimo.jitws.model.Subopcion;
import ar.com.draimo.jitws.service.MenuService;
import ar.com.draimo.jitws.service.RolSubopcionService;
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
        return elementoService.listarPorRolYModulo(idRol, idModulo);
    }
    
    //Obtiene una lista por rol y submodulo
    @GetMapping(value = URL + "/listarPorRolSubmodulo/{idRol}/{idSubmodulo}")
    @ResponseBody
    public List<Subopcion> listarPorRolSubmodulo(@PathVariable int idRol, @PathVariable int idSubmodulo) {
        return elementoService.listarSubopcionesPorRolYSubmodulo(idRol, idSubmodulo);
    }
    
    //Obtiene una lista por rol y submodulo para armado de menu por rol
    @GetMapping(value = URL + "/listarPorRolYSubmodulo/{idRol}/{idSubmodulo}")
    @ResponseBody
    public RolSubopcionDTO listarPorRolYSubmodulo(@PathVariable int idRol, @PathVariable int idSubmodulo) {
        return elementoService.listarPorRolYSubmodulo(idRol, idSubmodulo);
    }
    
    //Actualiza un registro
    @PutMapping(value = URL)
    public ResponseEntity<?> actualizar(@RequestBody RolSubopcionDTO elemento) {
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
    
    /*
     * Asigna todas las empresas a cada uno de los usuarios, eliminando todo los
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
