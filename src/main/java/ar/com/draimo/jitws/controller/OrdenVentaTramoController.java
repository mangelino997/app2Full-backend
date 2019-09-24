package ar.com.draimo.jitws.controller;

import ar.com.draimo.jitws.constant.RutaConstant;
import ar.com.draimo.jitws.exception.MensajeRespuesta;
import ar.com.draimo.jitws.model.OrdenVentaTramo;
import ar.com.draimo.jitws.service.OrdenVentaTramoService;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Clase OrdenVentaTramo Controller
 *
 * @author blas
 */
@RestController
public class OrdenVentaTramoController {

    //Define la url
    private final String URL = RutaConstant.URL_BASE + "/ordenventatramo";
    //Define la url de subcripciones a sockets
    private final String TOPIC = RutaConstant.URL_TOPIC + "/ordenventatramo";

    //Define el template para el envio de datos por socket
    @Autowired
    private SimpMessagingTemplate template;

    //Crea una instancia del servicio
    @Autowired
    OrdenVentaTramoService elementoService;

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

    //Obtiene una lista por id de orden venta tarifa
    @GetMapping(value = URL + "/listarPorOrdenVentaTarifa/{id}")
    @ResponseBody
    public Object listarPorOrdenVentaTarifa(@PathVariable int id) throws IOException {
        return elementoService.listarPorOrdenVentaTarifa(id);
    }

    //Obtiene una lista por id de orden venta y preciosDesde
    @GetMapping(value = URL + "/listarPorOrdenVenta/{idOrdenVenta}")
    @ResponseBody
    public Object listarPorOrdenVenta(@PathVariable int idOrdenVenta) throws IOException {
        return elementoService.listarPorOrdenVenta(idOrdenVenta);
    }

    //Obtiene una lista por id de orden venta y preciosDesde
    @GetMapping(value = URL + "/listarPorOrdenVentaYPreciosDesde/{idOrdenVenta}/{preciosDesde}")
    @ResponseBody
    public Object listarPorOrdenVentaYPreciosDesde(@PathVariable int idOrdenVenta, @PathVariable String preciosDesde) throws IOException {
        return elementoService.listarPorOrdenVentaYPreciosDesde(idOrdenVenta, preciosDesde);
    }

    //Obtiene una lista de fechas por orden de venta
    @GetMapping(value = URL + "/listarFechasPorOrdenVenta/{idOrdenVenta}")
    @ResponseBody
    public Object listarFechasPorOrdenVenta(@PathVariable int idOrdenVenta) throws IOException {
        return elementoService.listarFechasPorOrdenVenta(idOrdenVenta);
    }

    //Agrega un registro
    @PostMapping(value = URL)
    public ResponseEntity<?> agregar(@RequestBody OrdenVentaTramo elemento) {
        try {
            int a = elementoService.agregar(elemento);
            //Envia la nueva lista a los usuarios subscriptos
//            template.convertAndSend(TOPIC + "/lista", elementoService.listar());
            //Retorna mensaje de agregado con exito
            return new ResponseEntity(a, HttpStatus.CREATED);
        } catch (DataIntegrityViolationException dive) {
            //Retorna mensaje de dato duplicado
            return MensajeRespuesta.datoDuplicado(dive);
        } catch (MessagingException e) {
            //Retorna codigo y mensaje de error de sicronizacion mediante socket
            return MensajeRespuesta.errorSincSocket();
        } catch (Exception e) {
            //Retorna mensaje de error interno en el servidor
            return MensajeRespuesta.error();
        }
    }

    //Actualiza un registro
    @PutMapping(value = URL)
    public ResponseEntity<?> actualizar(@RequestBody OrdenVentaTramo elemento) {
        try {
            //Actualiza el registro
            int a = elementoService.actualizar(elemento);
            //Envia la nueva lista a los usuarios subscripto
//            template.convertAndSend(TOPIC + "/lista", elementoService.listar());
            //Retorna mensaje de actualizado con exito
            return new ResponseEntity(a, HttpStatus.OK);
        } catch (DataIntegrityViolationException dive) {
            //Retorna mensaje de dato duplicado
            return MensajeRespuesta.datoDuplicado(dive);
        } catch (JpaObjectRetrievalFailureException jorfe) {
            //Retorna mensaje de dato Inexistente
            return MensajeRespuesta.datoInexistente("a", jorfe.getMessage());
        } catch (ObjectOptimisticLockingFailureException oolfe) {
            //Retorna mensaje de transaccion no actualizada
            return MensajeRespuesta.transaccionNoActualizada();
        } catch (MessagingException e) {
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
            elementoService.eliminar(id);
            //Retorna mensaje de eliminado con exito
            return MensajeRespuesta.eliminado();
        } catch (Exception e) {
            //Retorna mensaje de error interno en el servidor
            return MensajeRespuesta.error();
        }
    }

}
