//Paquete al que pertenece el controlador
package ar.com.draimo.jitws.controller;

import ar.com.draimo.jitws.constant.RutaConstant;
import ar.com.draimo.jitws.dto.InitPuntoVentaDTO;
import ar.com.draimo.jitws.exception.MensajeRespuesta;
import ar.com.draimo.jitws.model.PuntoVenta;
import ar.com.draimo.jitws.service.PuntoVentaService;
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
 * Clase PuntoVenta Controller
 *
 * @author blas
 */
@RestController
public class PuntoVentaController {

    //Define la url
    private final String URL = RutaConstant.URL_BASE + "/puntoventa";
    //Define la url de subcripciones a sockets
    private final String TOPIC = RutaConstant.URL_TOPIC + "/puntoventa";

    //Define el template para el envio de datos por socket
    @Autowired
    private SimpMessagingTemplate template;

    //Crea una instancia del servicio
    @Autowired
    PuntoVentaService elementoService;

    //obtiene los listados para inicializar
    @GetMapping(value = URL + "/inicializar/{rol}/{subopcion}")
    @ResponseBody
    public InitPuntoVentaDTO inicializar(@PathVariable int rol, @PathVariable int subopcion) {
        return elementoService.inicializar(rol, subopcion);
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
    public List<PuntoVenta> listar() {
        return elementoService.listar();
    }

    //Obtiene una lista por sucursal
    @GetMapping(value = URL + "/listarPorSucursal/{id}")
    @ResponseBody
    public List<PuntoVenta> listarPorSucursal(@PathVariable int id) {
        return elementoService.listarPorSucursal(id);
    }
    
    //Obtiene una lista por empresa
    @GetMapping(value = URL + "/listarPorEmpresa/{id}")
    @ResponseBody
    public List<PuntoVenta> listarPorEmpresa(@PathVariable int id) {
        return elementoService.listarPorEmpresa(id);
    }

    //Obtiene una lista por sucursal y empresa
    @GetMapping(value = URL + "/listarPorSucursalYEmpresa/{idSucursal}/{idEmpresa}")
    @ResponseBody
    public List<PuntoVenta> listarPorSucursalYEmpresa(@PathVariable int idSucursal,
            @PathVariable int idEmpresa) {
        return elementoService.listarPorSucursalYEmpresa(idSucursal, idEmpresa);
    }

    //Obtiene una lista por sucursal y empresa y agregra la letra
    @GetMapping(value = URL + "/listarPorSucursalYEmpresaLetra/{idSucursal}/{idEmpresa}")
    @ResponseBody
    public List<PuntoVenta> listarPorSucursalYEmpresaLetra(@PathVariable int idSucursal,
            @PathVariable int idEmpresa) {
        return elementoService.listarPorSucursalYEmpresaLetra(idSucursal, idEmpresa);
    }

    //Obtiene una lista de hablitidos por sucursal, empresa y fe
    @GetMapping(value = URL + "/listarHabilitadosPorSucursalEmpresaYFe/{idSucursal}/{idEmpresa}")
    @ResponseBody
    public List<PuntoVenta> listarHabilitadosPorSucursalEmpresaYFe(@PathVariable int idSucursal,
            @PathVariable int idEmpresa) {
        return elementoService.listarHabilitadosPorSucursalEmpresaYFe(idSucursal, idEmpresa);
    }

    //Obtiene una lista por sucursal, empresa y tipo comprobante
    @GetMapping(value = URL + "/listarPorEmpresaYSucursalYTipoComprobante/{idEmpresa}/{idSucursal}/{idTipoComprobante}")
    @ResponseBody
    public List<PuntoVenta> listarPorEmpresaYSucursalYTipoComprobante(@PathVariable int idEmpresa,
            @PathVariable int idSucursal, @PathVariable int idTipoComprobante) {
        return elementoService.listarPorEmpresaYSucursalYTipoComprobante(idEmpresa,
                idSucursal, idTipoComprobante);
    }

    //Obtiene el numero por punto de venta, codigo afip, sucursal y empresa
    @GetMapping(value = URL + "/obtenerNumero/{puntoVenta}/{codigoAfip}/{idSucursal}/{idEmpresa}")
    @ResponseBody
    public int obtenerNumero(@PathVariable int puntoVenta, @PathVariable String codigoAfip,
            @PathVariable int idSucursal, @PathVariable int idEmpresa) {
        return elementoService.obtenerNumero(puntoVenta, codigoAfip, idSucursal, idEmpresa);
    }

    //Agrega un registro
    @PostMapping(value = URL)
    public ResponseEntity<?> agregar(@RequestBody PuntoVenta elemento) {
        try {
            PuntoVenta a = elementoService.agregar(elemento);
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
    public ResponseEntity<?> actualizar(@RequestBody PuntoVenta elemento) {
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
        } catch (Exception e) {
            //Retorna mensaje de error interno en el servidor
            return MensajeRespuesta.error();
        }
    }

}