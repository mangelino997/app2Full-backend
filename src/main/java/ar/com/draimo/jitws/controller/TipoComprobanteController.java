//Paquete al que pertenece el controlador
package ar.com.draimo.jitws.controller;

import ar.com.draimo.jitws.constant.RutaConstant;
import ar.com.draimo.jitws.dto.GenericoDTO;
import ar.com.draimo.jitws.exception.MensajeRespuesta;
import ar.com.draimo.jitws.model.TipoComprobante;
import ar.com.draimo.jitws.service.TipoComprobanteService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
 * Clase TipoComprobante Controller
 *
 * @author blas
 */
@RestController
public class TipoComprobanteController {

    //Define la url
    private final String URL = RutaConstant.URL_BASE + "/tipocomprobante";
    //Define la url de subcripciones a sockets
    private final String TOPIC = RutaConstant.URL_TOPIC + "/tipocomprobante";

    //Define el template para el envio de datos por socket
    @Autowired
    private SimpMessagingTemplate template;

    //Crea una instancia del servicio
    @Autowired
    TipoComprobanteService elementoService;

    //Obtiene listas necesarias para inicializar el componente (front)
    @GetMapping(value = URL + "/inicializar/{idRol}/{idSubopcion}")
    @ResponseBody
    public GenericoDTO inicializar(@PathVariable int idRol, @PathVariable int idSubopcion) {
        return elementoService.inicializar(idRol, idSubopcion);
    }

    //Obtiene el siguiente id
    @GetMapping(value = URL + "/obtenerSiguienteId")
    @ResponseBody
    public int obtenerSiguienteId() {
        return elementoService.obtenerSiguienteId();
    }

    //Obtiene un registro por id
    @GetMapping(value = URL + "/obtenerPorId/{id}")
    @ResponseBody
    public TipoComprobante obtenerPorId(@PathVariable int id) {
        return elementoService.obtenerPorId(id);
    }

    //Obtiene la lista completa
    @GetMapping(value = URL)
    @ResponseBody
    public List<TipoComprobante> listar() {
        return elementoService.listar();
    }
    
    //Obtiene una lista por nombre
    @GetMapping(value = URL + "/listarPorNombre/{nombre}")
    @ResponseBody
    public List<TipoComprobante> listarPorNombre(@PathVariable String nombre) {
        return elementoService.listarPorNombre(nombre);
    }

    //Obtiene una lista por esta activo ingreso carga igual true
    @GetMapping(value = URL + "/listarActivosCompraCarga")
    @ResponseBody
    public List<TipoComprobante> listarActivosCompraCarga() {
        return elementoService.listarEstaActivoCompraCarga();
    }
    
    //Obtiene una lista por esta activo venta carga igual true
    @GetMapping(value = URL + "/listarActivosVentaCarga")
    @ResponseBody
    public List<TipoComprobante> listarActivosVentaCarga() {
        return elementoService.listarEstaActivoVentaCarga();
    }
    
    //Obtiene una lista por esta activo ingreso carga igual true
    @GetMapping(value = URL + "/listarActivosIngresoCarga")
    @ResponseBody
    public List<TipoComprobante> listarActivosIngresoCarga() {
        return elementoService.listarEstaActivoIngresoCarga();
    }

    //Obtiene una lista por esta activo Reparto igual true
    @GetMapping(value = URL + "/listarActivosReparto")
    @ResponseBody
    public List<TipoComprobante> listarActivosReparto() {
        return elementoService.listarEstaActivoReparto();
    }

    //Obtiene una lista por numeracion punto venta igual true
    @GetMapping(value = URL + "/listarConNumeracionPuntoVenta")
    @ResponseBody
    public List<TipoComprobante> listarConNumeracionPuntoVenta() {
        return elementoService.listarNumeracionPuntoVenta();
    }

    //Obtiene una lista para emision de factura
    @GetMapping(value = URL + "/listarParaEmisionFactura")
    @ResponseBody
    public List<TipoComprobante> listarParaEmisionDeFactura() {
        return elementoService.listarParaEmisionFactura();
    }

    //Obtiene una lista para emision de nota de credito
    @GetMapping(value = URL + "/listarParaNotaCredito")
    @ResponseBody
    public List<TipoComprobante> listarParaNotaCredito() {
        return elementoService.listarParaEmisionNotaCredito();
    }

    //Agrega un registro
    @PostMapping(value = URL)
    public ResponseEntity<?> agregar(@RequestBody TipoComprobante elemento) {
        try {
            TipoComprobante a = elementoService.agregar(elemento);
            //Envia la nueva lista a los usuarios subscriptos
            //template.convertAndSend(TOPIC + "/lista", elementoService.listar());
            //Retorna mensaje de agregado con exito
            return MensajeRespuesta.agregado(a.getId());
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
    public ResponseEntity<?> actualizar(@RequestBody TipoComprobante elemento) {
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
        } catch (DataIntegrityViolationException dive) {
            //Retorna mensaje de dato duplicado
            return MensajeRespuesta.datoDuplicado(dive);
        } catch (Exception e) {
            //Retorna mensaje de error interno en el servidor
            return MensajeRespuesta.error();
        }
    }

}