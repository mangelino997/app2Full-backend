package ar.com.draimo.jitws.controller;
import ar.com.draimo.jitws.constant.RutaConstant;
import ar.com.draimo.jitws.exception.MensajeRespuesta;
import ar.com.draimo.jitws.model.Personal;
import ar.com.draimo.jitws.service.PersonalService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Clase Personal Controller
 * @author blas
 */

@RestController
public class PersonalController {
    
    //Define la url
    private final String URL = RutaConstant.URL_BASE + "/personal";
    //Define la url de subcripciones a sockets
    private final String TOPIC = RutaConstant.URL_TOPIC + "/personal";
    
    //Define el template para el envio de datos por socket
    @Autowired
    private SimpMessagingTemplate template;
    
    //Crea una instancia del servicio
    @Autowired
    PersonalService elementoService;
    
    //Obtiene el siguiente id
    @GetMapping(value = URL + "/obtenerSiguienteId")
    @ResponseBody
    public int obtenerSiguienteId() {
        return elementoService.obtenerSiguienteId();
    }
    
    //Obtiene la lista completa
    @GetMapping(value = URL)
    @ResponseBody
    public List<Personal> listar() {
        return elementoService.listar();
    }
    
    //Obtiene la lista de choferes de corta distancia por alias
    @GetMapping(value = URL + "/listarChoferesCortaDistanciaPorAlias/{alias}")
    @ResponseBody
    public List<Personal> listarChoferesCortaDistanciaPorAliasOrdenados(@PathVariable 
            String alias) {
        return elementoService.listarChoferesCortaDistanciaOrdenadoPorNombre(alias);
    }
    
    //Obtiene la lista de choferes de larga distancia por alias
    @GetMapping(value = URL + "/listarChoferesLargaDistanciaPorAlias/{alias}")
    @ResponseBody
    public List<Personal> listarChoferesLargaDistanciaPorAliasOrdenados(@PathVariable 
            String alias) {
        return elementoService.listarChoferesLargaDistanciaOrdenadoPorNombre(alias);
    }
    
    //Obtiene la lista completa
    @GetMapping(value = URL + "/listarAcompaniantesPorAlias/{alias}")
    @ResponseBody
    public List<Personal> listarAcompaniantesPorAliasOrdenados(@PathVariable String alias) {
        return elementoService.listarAcompaniantesOrdenadoPorNombre(alias);
    }
    
    //Obtiene una lista por alias
    @GetMapping(value = URL + "/listarPorAlias/{alias}")
    @ResponseBody
    public List<Personal> listarPorAlias(@PathVariable String alias) {
        return elementoService.listarPorAlias(alias);
    }
    
    //Obtiene una lista por alias y empresa
    @GetMapping(value = URL + "/listarPorAliasYEmpresa/{alias}/{idEmpresa}")
    @ResponseBody
    public List<Personal> listarPorAliasyEmpresa(@PathVariable String alias, @PathVariable int idEmpresa) {
        return elementoService.listarPorAliasYEmpresa(alias, idEmpresa);
    }
    
    //Obtiene una lista de choferes por alias
    @GetMapping(value = URL + "/listarChoferPorAlias/{alias}")
    @ResponseBody
    public List<Personal> listarChoferPorNombre(@PathVariable String alias) {
        return elementoService.listarChoferPorAlias(alias);
    }
    
    //Agrega un registro
    @PostMapping(value = URL)
    public ResponseEntity<?> agregar(@RequestBody Personal elemento) {
        try {
            //Agrega el registro
            Personal personal = elementoService.agregar(elemento);
            //Actualiza inmediatamente el registro para establecer el alias
            elementoService.establecerAlias(personal);
            //Envia la nueva lista a los usuarios subscriptos
            template.convertAndSend(TOPIC + "/lista", elementoService.listar());
            //Retorna mensaje de agregado con exito
            return MensajeRespuesta.agregado(personal.getId());
        } catch (DataIntegrityViolationException dive) {
            //Retorna mensaje de dato duplicado
            return MensajeRespuesta.datoDuplicado(dive);
        } catch(MessagingException e) {
            //Retorna codigo y mensaje de error de sicronizacion mediante socket
            return MensajeRespuesta.errorSincSocket();
        } catch (Exception e) {
            //Retorna mensaje de error interno en el servidor
            return MensajeRespuesta.error();
        }
    }
    
    //Actualiza un registro
    @PutMapping(value = URL)
    public ResponseEntity<?> actualizar(@RequestBody Personal elemento) {
        try {
            //Actualiza el registro
            elementoService.actualizar(elemento);
            //Envia la nueva lista a los usuarios subscripto
            template.convertAndSend(TOPIC + "/lista", elementoService.listar());
            //Retorna mensaje de actualizado con exito
            return MensajeRespuesta.actualizado();
        } catch (DataIntegrityViolationException dive) {
            //Retorna mensaje de dato duplicado
            return MensajeRespuesta.datoDuplicado(dive);
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
    
    //Elimina un registro
    @DeleteMapping(value = URL)
    public ResponseEntity<?> eliminar(@RequestBody Personal elemento) {
        try {
            elementoService.eliminar(elemento);
            //Retorna mensaje de eliminado con exito
            return MensajeRespuesta.eliminado();
        } catch(Exception e) {
            //Retorna mensaje de error interno en el servidor
            return MensajeRespuesta.error();
        }
    }
    
}
