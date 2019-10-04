//Paquete al que pertenece el controlador
package ar.com.draimo.jitws.controller;

import ar.com.draimo.jitws.constant.RutaConstant;
import ar.com.draimo.jitws.exception.MensajeRespuesta;
import ar.com.draimo.jitws.model.OrdenVentaEscala;
import ar.com.draimo.jitws.service.OrdenVentaEscalaService;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Clase OrdenVentaEscala Controller
 *
 * @author blas
 */
@RestController
public class OrdenVentaEscalaController {

    //Define la url
    private final String URL = RutaConstant.URL_BASE + "/ordenventaescala";
    //Define la url de subcripciones a sockets
    private final String TOPIC = RutaConstant.URL_TOPIC + "/ordenventaescala";

    //Define el template para el envio de datos por socket
    @Autowired
    private SimpMessagingTemplate template;

    //Crea una instancia del servicio
    @Autowired
    OrdenVentaEscalaService elementoService;

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

    //Obtiene una lista con escalas tarifas asignadas
    @GetMapping(value = URL + "/listarConEscalaTarifa")
    @ResponseBody
    public Object listarConEscalaTarifa() throws IOException {
        return elementoService.listarConEscalaTarifa();
    }

    //Obtiene una lista por orden venta tarifas 
    @GetMapping(value = URL + "/listarPorOrdenVentaTarifa/{id}")
    @ResponseBody
    public Object listarPorOrdenVentaTarifa(@PathVariable int id) throws IOException {
        return elementoService.listarPorOrdenVentaTarifa(id);
    }

    //Obtiene una lista por id de orden venta
    @GetMapping(value = URL + "/listarPorOrdenVenta/{idOrdenVenta}")
    @ResponseBody
    public Object listarPorOrdenVenta(@PathVariable int idOrdenVenta) throws IOException {
        return elementoService.listarPorOrdenVenta(idOrdenVenta);
    }

    //Obtiene una lista por id de orden venta tarifa y preciosDesde
    @GetMapping(value = URL + "/listarPorOrdenVentaTarifaYPreciosDesde/{idOrdenVentaTarifa}/{preciosDesde}")
    @ResponseBody
    public Object listarPorOrdenVentaTarifaYPreciosDesde(@PathVariable int idOrdenVentaTarifa, @PathVariable String preciosDesde) throws IOException {
        return elementoService.listarPorOrdenVentaTarifaYPreciosDesde(idOrdenVentaTarifa, preciosDesde);
    }

    //Obtiene una lista de fechas de preciosDesde por ordenVentaTarifa
    @GetMapping(value = URL + "/listarPreciosDesdePorOrdenVentaTarifa/{idOrdenVentaTarifa}")
    @ResponseBody
    public List<Date> listarPreciosDesdePorOrdenVentaTarifa(@PathVariable int idOrdenVentaTarifa) {
        return elementoService.listarPreciosDesdePorOrdenVentaTarifa(idOrdenVentaTarifa);
    }

    //Obtiene el precio flete
    @GetMapping(value = URL + "/obtenerPrecioFlete/{idOrdenVenta}/{valor}")
    @ResponseBody
    public BigDecimal obtenerPrecioFlete(@PathVariable int idOrdenVenta, @PathVariable String valor) {
        return elementoService.obtenerPrecioFlete(idOrdenVenta, valor);
    }

    //Agrega un registro
    @PostMapping(value = URL)
    public ResponseEntity<?> agregar(@RequestBody OrdenVentaEscala elemento) {
        try {
            Object a = elementoService.agregar(elemento);
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
    public ResponseEntity<?> actualizar(@RequestBody OrdenVentaEscala elemento) {
        try {
            //Actualiza el registro
            int a = elementoService.actualizar(elemento);
            //Envia la nueva lista a los usuarios subscripto
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
    @PutMapping(value = URL + "/{id}")
    public ResponseEntity<?> eliminar(@PathVariable int id) {
        try {
            //Elimina el registro
            elementoService.eliminar(id);
            //Retorna mensaje de eliminado con exito
            return MensajeRespuesta.eliminado();
        } catch (MessagingException e) {
            //Retorna mensaje de error interno en el servidor
            return MensajeRespuesta.error();
        } catch (Exception e) {
            //Retorna mensaje de error interno en el servidor
            return MensajeRespuesta.error();
        }
    }

}