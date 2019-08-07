package ar.com.draimo.jitws.controller;

import ar.com.draimo.jitws.constant.RutaConstant;
import ar.com.draimo.jitws.exception.MensajeRespuesta;
import ar.com.draimo.jitws.model.Usuario;
import ar.com.draimo.jitws.service.UsuarioService;
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
 * Clase Usuario Controller
 * @author blas
 */

@RestController
public class UsuarioController {
    
    //Define la url
    private final String URL = RutaConstant.URL_BASE + "/usuario";
    //Define la url de subcripciones a sockets
    private final String TOPIC = RutaConstant.URL_TOPIC + "/usuario";
    
    //Define el template para el envio de datos por socket
    @Autowired
    private SimpMessagingTemplate template;
    
    //Crea una instancia del servicio
    @Autowired
    UsuarioService elementoService;
    
    //Obtiene el siguiente id
    @GetMapping(value = URL + "/obtenerSiguienteId")
    @ResponseBody
    public int obtenerSiguienteId() {
        return elementoService.obtenerSiguienteId();
    }
    
    //Obtiene la lista completa
    @GetMapping(value = URL)
    @ResponseBody
    public List<Usuario> listar() {
        return elementoService.listar();
    }
    
    //Obtiene una lista por username
    @GetMapping(value = URL + "/obtenerPorUsername/{username}")
    @ResponseBody
    public Usuario obtenerPorUsername(@PathVariable String username) {
        return elementoService.obtenerPorUsername(username);
    }
    
    //Obtiene un listado por nombre
    @GetMapping(value = URL + "/listarPorNombre/{nombre}")
    @ResponseBody
    public List<Usuario> listarPorNombre(@PathVariable String nombre) {
        return elementoService.listarPorNombre(nombre);
    }
    
    //Obtiene una lista por rol
    @GetMapping(value = URL + "/listarPorRol/{idRol}")
    @ResponseBody
    public List<Usuario> listarPorRol(@PathVariable int idRol) {
        return elementoService.listarPorRol(idRol);
    }
    
    //Obtiene una lista por rol secundario
    @GetMapping(value = URL + "/listarPorRolSecundario/{idRol}")
    @ResponseBody
    public List<Usuario> listarPorRolSecundario(@PathVariable int idRol) {
        return elementoService.listarPorRolSecundario(idRol);
    }
    
    //Obtiene una lista de usuarios por empresa
    @GetMapping(value = URL + "/listarPorEmpresa/{idEmpresa}")
    @ResponseBody
    public List<Usuario> listarPorEmpresa(@PathVariable int idEmpresa) {
        return elementoService.listarPorEmpresa(idEmpresa);
    }
    
    //Agrega un registro
    @PostMapping(value = URL)
    public ResponseEntity<?> agregar(@RequestBody Usuario elemento) {
        try {
            Usuario a = elementoService.agregar(elemento);
            //Envia la nueva lista a los usuarios subscriptos
            template.convertAndSend(TOPIC + "/lista", elementoService.listar());
            //Retorna mensaje de agregado con exito
            return MensajeRespuesta.agregado(a.getId());
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
    public ResponseEntity<?> actualizar(@RequestBody Usuario elemento) {
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
    
    //Actualiza un registro
    @PutMapping(value = URL + "/actualizarContrasenia")
    public ResponseEntity<?> actualizarContrasenia(@RequestBody Usuario elemento) {
        try {
            //Actualiza el registro
            elementoService.actualizarContrasenia(elemento);
            //Retorna mensaje de actualizado con exito
            return MensajeRespuesta.actualizado();
        } catch(Exception e) {
            //Retorna mensaje de error interno en el servidor
            return MensajeRespuesta.error();
        }
    }
    
    //Elimina un registro
    @DeleteMapping(value = URL + "/{id}")
    public ResponseEntity<?> eliminar(@PathVariable int id) {
        try {
            elementoService.eliminar(id);
            //Retorna mensaje de eliminado con exito
            return MensajeRespuesta.eliminado();
        }catch (DataIntegrityViolationException dive) {
            //Retorna mensaje de dato duplicado
            return MensajeRespuesta.datoDuplicado(dive);
        } catch(Exception e) {
            //Retorna mensaje de error interno en el servidor
            return MensajeRespuesta.error();
        }
    }
    
}
