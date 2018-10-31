package ar.com.wecoode.jitws.controller;

import ar.com.wecoode.jitws.constant.RutaConstant;
import ar.com.wecoode.jitws.exception.CodigoRespuesta;
import ar.com.wecoode.jitws.exception.EstadoRespuesta;
import ar.com.wecoode.jitws.exception.EstadoRespuestaAgregar;
import ar.com.wecoode.jitws.exception.MensajeRespuesta;
import ar.com.wecoode.jitws.model.ViajePropioTramo;
import ar.com.wecoode.jitws.model.ViajeRemito;
import ar.com.wecoode.jitws.service.ViajeRemitoService;
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
 * Clase ViajeRemito Controller
 * @author blas
 */

@RestController
public class ViajeRemitoController {
    
    //Define la url
    private final String URL = RutaConstant.URL_BASE + "/viajeremito";
    //Define la url de subcripciones a sockets
    private final String TOPIC = RutaConstant.URL_TOPIC + "/viajeremito";
    
    //Define el template para el envio de datos por socket
    @Autowired
    private SimpMessagingTemplate template;
    
    //Crea una instancia del servicio
    @Autowired
    ViajeRemitoService elementoService;
    
    //Obtiene el siguiente id
    @GetMapping(value = URL + "/obtenerSiguienteId")
    @ResponseBody
    public int obtenerSiguienteId() {
        return elementoService.obtenerSiguienteId();
    }
    
    //Obtiene la lista completa
    @GetMapping(value = URL)
    @ResponseBody
    public List<ViajeRemito> listar() {
        return elementoService.listar();
    }
    
    //Obtiene una lista por numero
    @GetMapping(value = URL + "/listarPorNumero/{numero}")
    @ResponseBody
    public List<ViajeRemito> listarPorNumero(@PathVariable int numero) {
        return elementoService.listarPorNumero(numero);
    }
    
    //Obtiene una lista de remitos pendientes por sucursal
    @GetMapping(value = URL + "/listarPendientesPorSucursal/{idSucursal}")
    @ResponseBody
    public List<ViajeRemito> listarPendientesPorSucursal(@PathVariable int idSucursal) {
        return elementoService.listarPendientesPorSucursal(idSucursal);
    }
    
    //Obtiene una lista de remitos pendientes por filtro
    @GetMapping(value = URL + "/listarPendientesPorFiltro/{idSucursal}/{idSucursalDestino}/{numeroCamion}")
    @ResponseBody
    public List<ViajeRemito> listarPendientesPorFiltro(@PathVariable int idSucursal, 
            @PathVariable int idSucursalDestino, @PathVariable short numeroCamion) {
        return elementoService.listarPendientesPorFiltro(idSucursal, idSucursalDestino, numeroCamion);
    }
    
    //Obtiene una lista de remitos asignados por filtro
    @GetMapping(value = URL + "/listarAsignadosPorFiltro/{idSucursal}/{idSucursalDestino}"
            + "/{numeroCamion}/{idViajePropioTramo}")
    @ResponseBody
    public List<ViajeRemito> listarAsignadosPorFiltro(@PathVariable int idSucursal, 
            @PathVariable int idSucursalDestino, @PathVariable short numeroCamion, 
            @PathVariable int idViajePropioTramo) {
        return elementoService.listarAsignadosPorFiltro(idSucursal, idSucursalDestino, 
                numeroCamion, idViajePropioTramo);
    }
    
    //Asigna remitos
    @PutMapping(value = URL + "/asignar")
    public ResponseEntity<?> asignar(@RequestBody ViajePropioTramo elemento) {
        try {
            elementoService.asignar(elemento);
            return new ResponseEntity(new EstadoRespuesta(CodigoRespuesta.OK, 
                    MensajeRespuesta.ACTUALIZADO), HttpStatus.OK);
        } catch (ObjectOptimisticLockingFailureException oolfe) {
            //Retorna codigo y mensaje de error de operacion actualizada por otra transaccion
            return new ResponseEntity(new EstadoRespuesta(CodigoRespuesta.TRANSACCION_NO_ACTUALIZADA,
                    MensajeRespuesta.TRANSACCION_NO_ACTUALIZADA), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch(Exception e) {
            //Retorna codigo y mensaje de error interno en el servidor
            return new ResponseEntity(new EstadoRespuesta(CodigoRespuesta.ERROR_INTERNO_SERVIDOR,
                    MensajeRespuesta.ERROR_INTERNO_SERVIDOR), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    //Quita remitos
    @PutMapping(value = URL + "/quitar")
    public ResponseEntity<?> quitar(@RequestBody ViajePropioTramo elemento) {
        try {
            elementoService.quitar(elemento);
            return new ResponseEntity(new EstadoRespuesta(CodigoRespuesta.OK, 
                    MensajeRespuesta.ACTUALIZADO), HttpStatus.OK);
        } catch (ObjectOptimisticLockingFailureException oolfe) {
            //Retorna codigo y mensaje de error de operacion actualizada por otra transaccion
            return new ResponseEntity(new EstadoRespuesta(CodigoRespuesta.TRANSACCION_NO_ACTUALIZADA,
                    MensajeRespuesta.TRANSACCION_NO_ACTUALIZADA), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch(Exception e) {
            //Retorna codigo y mensaje de error interno en el servidor
            return new ResponseEntity(new EstadoRespuesta(CodigoRespuesta.ERROR_INTERNO_SERVIDOR,
                    MensajeRespuesta.ERROR_INTERNO_SERVIDOR), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    //Agrega un registro
    @PostMapping(value = URL)
    public ResponseEntity<?> agregar(@RequestBody ViajeRemito elemento) {
        try {
            ViajeRemito e = elementoService.agregar(elemento);
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
    public ResponseEntity<?> actualizar(@RequestBody ViajeRemito elemento) {
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
    public ResponseEntity<?> eliminar(@RequestBody ViajeRemito elemento) {
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
