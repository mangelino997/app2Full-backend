//Paquete al que pertenece el controlador
package ar.com.draimo.jitws.controller;

import ar.com.draimo.jitws.constant.RutaConstant;
import ar.com.draimo.jitws.dto.InitFacturaDTO;
import ar.com.draimo.jitws.exception.MensajeRespuesta;
import ar.com.draimo.jitws.model.VentaComprobante;
import ar.com.draimo.jitws.service.VentaComprobanteService;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Clase VentaComprobante Controller
 * @author blas
 */
@RestController
public class VentaComprobanteController {

    //Define la url
    private final String URL = RutaConstant.URL_BASE + "/ventacomprobante";
    //Define la url de subcripciones a sockets
    private final String TOPIC = RutaConstant.URL_TOPIC + "/ventacomprobante";

    //Define el template para el envio de datos por socket
    @Autowired
    private SimpMessagingTemplate template;

    //Crea una instancia del servicio
    @Autowired
    VentaComprobanteService elementoService;

    //Obtiene el siguiente id
    @GetMapping(value = URL + "/inicializarFactura/{idEmpresa}/{idSucursal}")
    @ResponseBody
    public InitFacturaDTO inicializarFactura(@PathVariable int idEmpresa, @PathVariable int idSucursal) {
        return elementoService.inicializarFactura(idEmpresa, idSucursal);
    }

    //Obtiene el siguiente id
    @GetMapping(value = URL + "/inicializarNotaCredito/{idEmpresa}/{idSucursal}")
    @ResponseBody
    public InitFacturaDTO inicializarNotaCredito(@PathVariable int idEmpresa, @PathVariable int idSucursal) {
        return elementoService.inicializarNotaCredito(idEmpresa, idSucursal);
    }

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
    

    //Obtiene la lista por comprobantes disponibles
    @GetMapping(value = URL + "/listarComprobantesDisponibles")
    @ResponseBody
    public Object listarComprobantesDisponibles() throws IOException {
        return elementoService.listarComprobantesDisponibles();
    }

    //Obtiene la lista por tipo de comprobante
    @GetMapping(value = URL + "/listarPorTipoComprobante/{idTipoComprobante}")
    @ResponseBody
    public Object listarPorTipoComprobante(@PathVariable int idTipoComprobante) throws IOException {
        return elementoService.listarPorTipoComprobante(idTipoComprobante);
    }

    //Obtiene la lista de letras
    @GetMapping(value = URL + "/listarLetras")
    @ResponseBody
    public List<String> listarLetras() throws IOException {
        return elementoService.listarLetras();
    }

    //Obtiene la lista por cliente y empresa
    @GetMapping(value = URL + "/listarPorClienteYEmpresa/{idCliente}/{idEmpresa}")
    @ResponseBody
    public Object listarPorClienteYEmpresa(@PathVariable int idCliente, @PathVariable int idEmpresa)
            throws IOException {
        return elementoService.listarPorClienteYEmpresa(idCliente, idEmpresa);
    }

    //Obtiene la lista por cliente y empresa para cobranza
    @GetMapping(value = URL + "/listarParaCobranza/{idCliente}/{idEmpresa}")
    @ResponseBody
    public Object listarParaCobranza(@PathVariable int idCliente, @PathVariable int idEmpresa)
            throws IOException {
        return elementoService.listarParaCobranza(idCliente, idEmpresa);
    }

    //Obtiene la lista para creditos por cliente y empresa
    @GetMapping(value = URL + "/listarParaCreditosPorClienteYEmpresa/{idCliente}/{idEmpresa}")
    @ResponseBody
    public Object listarParaCreditosPorClienteYEmpresa(@PathVariable int idCliente, @PathVariable int idEmpresa)
            throws IOException {
        return elementoService.listarParaCreditosPorClienteYEmpresa(idCliente, idEmpresa);
    }

    //Obtiene un registro por puntoventa letra, tipo de comprobante y numero
    @GetMapping(value = URL + "/obtener/{puntoVenta}/{letra}/{numero}/{idTipoComprobante}")
    @ResponseBody
    public Object obtener(@PathVariable int puntoVenta, @PathVariable String letra,
            @PathVariable int numero, @PathVariable int idTipoComprobante) throws IOException {
        return elementoService.obtener(puntoVenta, letra, numero, idTipoComprobante);
    }

    //Agrega un registro
    @PostMapping(value = URL)
    public ResponseEntity<?> agregar(@RequestBody VentaComprobante elemento) {
        try {
            VentaComprobante a = elementoService.agregar(elemento);
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
    public ResponseEntity<?> actualizar(@RequestBody VentaComprobante elemento) {
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