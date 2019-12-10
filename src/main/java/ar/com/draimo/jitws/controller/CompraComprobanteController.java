//Paquete al que pertenece el controlador
package ar.com.draimo.jitws.controller;

import ar.com.draimo.jitws.constant.RutaConstant;
import ar.com.draimo.jitws.dto.GenericoDTO;
import ar.com.draimo.jitws.dto.InitCompraComprobanteDTO;
import ar.com.draimo.jitws.exception.MensajeRespuesta;
import ar.com.draimo.jitws.model.CompraComprobante;
import ar.com.draimo.jitws.service.CompraComprobanteService;
import java.io.IOException;
import java.sql.Date;
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
 * Clase CompraComprobante Controller
 * @author blas
 */

@RestController
public class CompraComprobanteController {
    
    //Define la url
    private final String URL = RutaConstant.URL_BASE + "/compracomprobante";
    //Define la url de subcripciones a sockets
    private final String TOPIC = RutaConstant.URL_TOPIC + "/compracomprobante";
    
    //Define el template para el envio de datos por socket
    @Autowired
    private SimpMessagingTemplate template;
    
    //Crea una instancia del servicio
    @Autowired
    CompraComprobanteService elementoService;

    //Obtiene listas necesarias para inicializar el componente (front)
    @GetMapping(value = URL + "/inicializar/{idRol}/{idSubopcion}")
    @ResponseBody
    public InitCompraComprobanteDTO inicializar(@PathVariable int idRol, @PathVariable int idSubopcion) {
        return elementoService.inicializar(idRol, idSubopcion);
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
    public List<CompraComprobante> listar() {
        return elementoService.listar();
    }
    
    //Obtiene la lista por filtros
    @GetMapping(value = URL + "/listarPorFiltros/{idEmpresa}/{idProveedor}/{fechaTipo}/{fechaDesde}/{fechaHasta}/{idTipoComprobante}")
    @ResponseBody
    public Object listarPorFiltros(@PathVariable int idEmpresa, 
            @PathVariable int idProveedor, @PathVariable int fechaTipo, 
            @PathVariable Date fechaDesde, @PathVariable Date fechaHasta, @PathVariable int idTipoComprobante) throws IOException {
        return elementoService.listarPorFiltros(idEmpresa, idProveedor, fechaTipo, fechaDesde, fechaHasta, idTipoComprobante);
    }
    
    //Obtiene la lista por proveedor y empresa
    @GetMapping(value = URL + "/listarParaOrdenPago/{idEmpresa}/{idProveedor}")
    @ResponseBody
    public Object listarParaOrdenPago(@PathVariable int idEmpresa, @PathVariable int idProveedor) throws IOException {
        return elementoService.listarParaOrdenPago(idEmpresa, idProveedor);
    }
    
    //retorna si se cumple o no la unicidad
    @GetMapping(value = URL + "/verificarUnicidadComprobante/{idProveedor}/{codigoAfip}/{puntoVenta}/{numero}")
    @ResponseBody
    public boolean verificarUnicidadComprobante(@PathVariable int idProveedor,
            @PathVariable String codigoAfip, @PathVariable int puntoVenta, @PathVariable int numero) {
        return elementoService.verificarUnicidadComprobante(idProveedor, codigoAfip, puntoVenta, numero);
    }
    
    //Agrega un registro
    @PostMapping(value = URL)
    public ResponseEntity<?> agregar(@RequestBody CompraComprobante elemento) {
        try {
            CompraComprobante a = elementoService.agregar(elemento);
            //Envia la nueva lista a los usuarios subscriptos
            //template.convertAndSend(TOPIC + "/lista", elementoService.listar());
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
    public ResponseEntity<?> actualizar(@RequestBody CompraComprobante elemento) {
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
    
}
