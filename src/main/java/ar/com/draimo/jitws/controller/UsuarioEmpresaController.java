package ar.com.draimo.jitws.controller;

import ar.com.draimo.jitws.constant.RutaConstant;
import ar.com.draimo.jitws.dto.UsuarioDTO;
import ar.com.draimo.jitws.exception.MensajeRespuesta;
import ar.com.draimo.jitws.model.Empresa;
import ar.com.draimo.jitws.model.UsuarioEmpresa;
import ar.com.draimo.jitws.service.UsuarioEmpresaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Clase UsuarioEmpresa Controller
 * @author blas
 */

@RestController
public class UsuarioEmpresaController {
    
    //Define la url
    private final String URL = RutaConstant.URL_BASE + "/usuarioempresa";
    //Define la url de subcripciones a sockets
    private final String TOPIC = RutaConstant.URL_TOPIC + "/usuarioempresa";
    
    //Define el template para el envio de datos por socket
    @Autowired
    private SimpMessagingTemplate template;
    
    //Crea una instancia del servicio
    @Autowired
    UsuarioEmpresaService elementoService;
    
    //Obtiene el siguiente id
    @GetMapping(value = URL + "/obtenerSiguienteId")
    @ResponseBody
    public int obtenerSiguienteId() {
        return elementoService.obtenerSiguienteId();
    }
    
    //Obtiene la lista completa
    @GetMapping(value = URL)
    @ResponseBody
    public List<UsuarioEmpresa> listar() {
        return elementoService.listar();
    }
    
    //Obtiene una lista por usuario
    @GetMapping(value = URL + "/listarPorUsuario/{idUsuario}")
    @ResponseBody
    public List<UsuarioEmpresa> listarPorUsuario(@PathVariable int idUsuario) {
        return elementoService.listarPorUsuario(idUsuario);
    }
    
    //Obtiene las empresas activas del usuario
    @GetMapping(value = URL + "/listarEmpresasActivasDeUsuario/{idUsuario}")
    @ResponseBody
    public List<Empresa> listarEmpresasActivasDeUsuario(@PathVariable int idUsuario) {
        return elementoService.listarEmpresasActivasDeUsuario(idUsuario);
    }
    
    //Agrega un registro
    @PostMapping(value = URL)
    public ResponseEntity<?> agregar(@RequestBody UsuarioEmpresa elemento) {
        try {
            UsuarioEmpresa a = elementoService.agregar(elemento);
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
    public ResponseEntity<?> actualizar(@RequestBody UsuarioDTO elemento) {
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
    
    /*
     * Asigna todas las empresas a cada uno de los usuarios, manteniendo el dato
     * 'Mostrar' de cada empresa
     */
    @GetMapping(value = URL + "/eliminarTabla")
    @ResponseBody
    public List<UsuarioEmpresa> eliminarTabla() {
        return elementoService.eliminarTabla();
    }
    
    /*
     * Asigna todas las empresas a cada uno de los usuarios, manteniendo el dato
     * 'Mostrar' de cada empresa
     */
    @GetMapping(value = URL + "/reestablecerTabla")
    @ResponseBody
    public void reestablecerTabla(@RequestBody List<UsuarioEmpresa> elemento) {
        elementoService.reestablecerTabla(elemento);
    }
    
    /*
     * Asigna todas las empresas a cada uno de los usuarios, eliminando todo los
     * datos y reestableciendo desde cero
     */
    @GetMapping(value = URL + "/reestablecerTablaDesdeCero")
    @ResponseBody
    public ResponseEntity<?> reestablecerTablaDesdeCero() {
        elementoService.reestablecerTablaDesdeCero();
        try {
            //Envia la nueva lista a los usuarios subscriptos
            template.convertAndSend(TOPIC + "/listarMenu", 1);
               return MensajeRespuesta.tablaReestablecida();
        }catch(MessagingException e) {
            //Retorna codigo y mensaje de error de sicronizacion mediante socket
            return MensajeRespuesta.errorSincSocket();
        }
    }
    
}
