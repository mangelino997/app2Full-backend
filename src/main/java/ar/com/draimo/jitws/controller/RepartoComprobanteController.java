package ar.com.draimo.jitws.controller;

import ar.com.draimo.jitws.constant.RutaConstant;
import ar.com.draimo.jitws.exception.MensajeRespuesta;
import ar.com.draimo.jitws.model.RepartoComprobante;
import ar.com.draimo.jitws.service.RepartoComprobanteService;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Clase RepartoComprobante Controller
 * @author blas
 */

@RestController
public class RepartoComprobanteController {
    
    //Define la url
    private final String URL = RutaConstant.URL_BASE + "/repartocomprobante";
    //Define la url de subcripciones a sockets
    private final String TOPIC = RutaConstant.URL_TOPIC + "/repartocomprobante";
    
    //Define el template para el envio de datos por socket
    @Autowired
    private SimpMessagingTemplate template;
    
    //Crea una instancia del servicio
    @Autowired
    RepartoComprobanteService elementoService;
    
    //Obtiene el siguiente id
    @GetMapping(value = URL + "/obtenerSiguienteId")
    @ResponseBody
    public int obtenerSiguienteId() {
        return elementoService.obtenerSiguienteId();
    }
    
    //Obtiene la lista completa
    @GetMapping(value = URL)
    @ResponseBody
    public Object listar() throws IOException {
        return elementoService.listar();
    }
    
    //Obtiene la lista por RepartoPropio
    @GetMapping(value = URL + "/listarComprobantes/{idReparto}")
    @ResponseBody
    public Object listarComprobantes(@PathVariable int idReparto) throws IOException {
        return elementoService.listarComprobantes(idReparto);
    }
    
    //Obtiene la lista por RepartoPropio
    @GetMapping(value = URL + "/quitarComprobante/{id}")
    @ResponseBody
    public ResponseEntity<?> quitarComprobante(@PathVariable int id) {
        try {
            int rp = elementoService.quitarComprobante(id);
         //Envia la nueva lista a los usuarios subscriptos
            template.convertAndSend(TOPIC + "/listarComprobantes", 
                    elementoService.listarComprobantes(rp));
            //Retorna mensaje de eliminado con exito
            return MensajeRespuesta.eliminado();
        } catch (Exception e) {
            //Retorna mensaje de error
            return MensajeRespuesta.error();
        }
    }
    
    //Agrega un registro
    @PostMapping(value = URL)
    public ResponseEntity<?> agregar(@RequestBody RepartoComprobante elemento) {
        try {
            RepartoComprobante a = elementoService.agregar(elemento);
            //Envia la nueva lista a los usuarios subscriptos
            template.convertAndSend(TOPIC + "/listarComprobantes", 
                    elementoService.listarComprobantes(elemento.getReparto().getId()));
            //Confirma si el registro fue agregado. Si no devuelve mensaje de no existente
            return a.getReparto()!=null ? MensajeRespuesta.agregado(a.getId()) 
                    : MensajeRespuesta.registroNoExistente();
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
    public ResponseEntity<?> actualizar(@RequestBody RepartoComprobante elemento) {
        try {
            //Actualiza el registro
            RepartoComprobante a = elementoService.actualizar(elemento);
            //Envia la nueva lista a los usuarios subscripto
            template.convertAndSend(TOPIC + "/listarComprobantes", 
                    elementoService.listarComprobantes(elemento.getReparto().getId()));
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
    public ResponseEntity<?> eliminar(@RequestBody RepartoComprobante elemento) {
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
