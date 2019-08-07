package ar.com.draimo.jitws.controller;

import ar.com.draimo.jitws.constant.RutaConstant;
import ar.com.draimo.jitws.exception.MensajeRespuesta;
import ar.com.draimo.jitws.model.OrdenVentaTarifa;
import ar.com.draimo.jitws.service.OrdenVentaTarifaService;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
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
 * Clase OrdenVentaTarifa Controller
 * @author blas
 */

@RestController
public class OrdenVentaTarifaController {
    
    //Define la url
    private final String URL = RutaConstant.URL_BASE + "/ordenventatarifa";
    //Define la url de subcripciones a sockets
    private final String TOPIC = RutaConstant.URL_TOPIC + "/ordenventatarifa";
    
    //Define el template para el envio de datos por socket
    @Autowired
    private SimpMessagingTemplate template;
    
    //Crea una instancia del servicio
    @Autowired
    OrdenVentaTarifaService elementoService;
    
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
    
    //Obtiene una lista por id de orden venta
    @GetMapping(value = URL + "/listarPorOrdenVenta/{idOrdenVenta}")
    @ResponseBody
    public Object listarPorOrdenVenta(@PathVariable int idOrdenVenta) throws IOException {
        return elementoService.listarPorOrdenVenta(idOrdenVenta);
    }
    
//    //Obtiene una lista por id de orden venta y preciosDesde
//    @GetMapping(value = URL + "/listarPorOrdenVentaYPreciosDesde/{idOrdenVenta}/{preciosDesde}")
//    @ResponseBody
//    public Object listarPorOrdenVentaYPreciosDesde(@PathVariable int idOrdenVenta, @PathVariable Date preciosDesde) throws IOException {
//        return elementoService.listarPorOrdenVentaYPreciosDesde(idOrdenVenta, preciosDesde);
//    }
    
    //Agrega un registro
    @PostMapping(value = URL)
    public ResponseEntity<?> agregar(@RequestBody OrdenVentaTarifa elemento) {
        try {
            Object a = elementoService.agregar(elemento);
//            template.convertAndSend(TOPIC + "/listaEscalas", 
//                    elementoService.listarPorOrdenVenta(elemento.getOrdenVenta().getId()));
            //Retorna mensaje de agregado con exito
            return new ResponseEntity(a, HttpStatus.CREATED);
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
    public ResponseEntity<?> actualizar(@RequestBody OrdenVentaTarifa elemento) {
        try {
            //Actualiza el registro
            Object a =elementoService.actualizar(elemento);
            //Envia la nueva lista a los usuarios subscripto
//            template.convertAndSend(TOPIC + "/listaEscalas", 
//                    elementoService.listarPorOrdenVenta(elemento.getOrdenVenta().getId()));
            //Retorna mensaje de actualizado con exito
            return new ResponseEntity(a, HttpStatus.OK);
        } catch (DataIntegrityViolationException dive) {
            //Retorna mensaje de dato duplicado
            return MensajeRespuesta.datoDuplicado(dive);
        } catch(ObjectOptimisticLockingFailureException oolfe) {
            //Retorna mensaje de transaccion no actualizada
            return MensajeRespuesta.transaccionNoActualizada();
        }catch(MessagingException e) {
            //Retorna codigo y mensaje de error de sicronizacion mediante socket
            return MensajeRespuesta.errorSincSocket();
        } catch (Exception e) {
            //Retorna mensaje de error interno en el servidor
            return MensajeRespuesta.error();
        }
        
    }
    
    //Elimina un registro
    @DeleteMapping(value = URL + "/{id}")
    public ResponseEntity<?> eliminar(@PathVariable int id) {
        try {
            //Elimina el registro
            elementoService.eliminar(id);
            //Envia la nueva lista a los usuarios subscripto
//            template.convertAndSend(TOPIC + "/listaEscalas", 
//                    elementoService.listarPorOrdenVenta(elemento.getOrdenVenta().getId()));
            //Retorna mensaje de eliminado con exito
            return MensajeRespuesta.eliminado();
        }catch (DataIntegrityViolationException dive) {
            //Retorna mensaje de dato duplicado
            return MensajeRespuesta.datoDuplicado(dive);
        } catch(MessagingException e) {
            //Retorna mensaje de error interno en el servidor
            return MensajeRespuesta.error();
        }
    }
    
}