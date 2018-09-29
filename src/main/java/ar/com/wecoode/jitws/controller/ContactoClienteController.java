package ar.com.wecoode.jitws.controller;

import ar.com.wecoode.jitws.constant.RutaConstant;
import ar.com.wecoode.jitws.exception.CodigoRespuesta;
import ar.com.wecoode.jitws.exception.EstadoRespuesta;
import ar.com.wecoode.jitws.exception.EstadoRespuestaAgregar;
import ar.com.wecoode.jitws.exception.MensajeRespuesta;
import ar.com.wecoode.jitws.model.ContactoCliente;
import ar.com.wecoode.jitws.service.ContactoClienteService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Clase ContactoCliente Controller
 * @author blas
 */

@RestController
public class ContactoClienteController {
    
    //Define la url
    private final String URL = RutaConstant.URL_BASE + "/contactocliente";
    //Define la url de subcripciones a sockets
    private final String TOPIC = RutaConstant.URL_TOPIC + "/contactocliente";
    
    //Define el template para el envio de datos por socket
    @Autowired
    private SimpMessagingTemplate template;
    
    //Crea una instancia del servicio
    @Autowired
    ContactoClienteService elementoService;
    
    //Obtiene el siguiente id
    @GetMapping(value = URL + "/obtenerSiguienteId")
    @ResponseBody
    public int obtenerSiguienteId() {
        return elementoService.obtenerSiguienteId();
    }
    
    //Obtiene la lista completa
    @GetMapping(value = URL)
    @ResponseBody
    public List<ContactoCliente> listar() {
        return elementoService.listar();
    }
    
    //Obtiene una lista por nombre
    @GetMapping(value = URL + "/listarPorNombre/{nombre}")
    @ResponseBody
    public List<ContactoCliente> listarPorNombre(@PathVariable String nombre) {
        return elementoService.listarPorNombre(nombre);
    }
    
    //Obtiene por sucursal banco
    @GetMapping(value = URL + "/obtenerPorCliente/{idCliente}")
    @ResponseBody
    public ContactoCliente obtenerPorCliente(@PathVariable int idCliente) {
        return elementoService.obtenerPorCliente(idCliente);
    }
    
    //Agrega un registro
    @PostMapping(value = URL)
    public ResponseEntity<?> agregar(@RequestBody ContactoCliente elemento) {
        try {
            ContactoCliente e = elementoService.agregar(elemento);
            //Envia la nueva lista a los usuarios subscriptos
            template.convertAndSend(TOPIC + "/lista", elementoService.listar());
            return new ResponseEntity(new EstadoRespuestaAgregar(CodigoRespuesta.CREADO, 
                    MensajeRespuesta.AGREGADO, (e.getId()+1)), HttpStatus.CREATED);
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
    
    //Actualiza un registro
    @PutMapping(value = URL)
    public ResponseEntity<?> actualizar(@RequestBody ContactoCliente elemento) {
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
    
    //Elimina un registro
    @DeleteMapping(value = URL)
    public ResponseEntity<?> eliminar(@RequestBody ContactoCliente elemento) {
        try {
            elementoService.eliminar(elemento);
            return new ResponseEntity(new EstadoRespuesta(CodigoRespuesta.OK, 
                    MensajeRespuesta.ELIMINADO), HttpStatus.OK);
        } catch(Exception e) {
            //Retorna codigo y mensaje de error interno en el servidor
            return new ResponseEntity(new EstadoRespuesta(CodigoRespuesta.ERROR_INTERNO_SERVIDOR, 
                    MensajeRespuesta.ERROR_INTERNO_SERVIDOR), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
