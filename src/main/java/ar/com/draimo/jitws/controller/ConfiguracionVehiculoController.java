package ar.com.draimo.jitws.controller;

import ar.com.draimo.jitws.constant.RutaConstant;
import ar.com.draimo.jitws.exception.CodigoRespuesta;
import ar.com.draimo.jitws.exception.EstadoRespuesta;
import ar.com.draimo.jitws.exception.EstadoRespuestaAgregar;
import ar.com.draimo.jitws.exception.MensajeRespuesta;
import ar.com.draimo.jitws.model.ConfiguracionVehiculo;
import ar.com.draimo.jitws.service.ConfiguracionVehiculoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Clase ConfiguracionVehiculo Controller
 * @author blas
 */

@RestController
public class ConfiguracionVehiculoController {
    
    //Define la url
    private final String URL = RutaConstant.URL_BASE + "/configuracionvehiculo";
    //Define la url de subcripciones a sockets
    private final String TOPIC = RutaConstant.URL_TOPIC + "/configuracionvehiculo";
    
    //Define el template para el envio de datos por socket
    @Autowired
    private SimpMessagingTemplate template;
    
    //Crea una instancia del servicio
    @Autowired
    ConfiguracionVehiculoService elementoService;
    
    //Obtiene el siguiente id
    @GetMapping(value = URL + "/obtenerSiguienteId")
    @ResponseBody
    public int obtenerSiguienteId() {
        return elementoService.obtenerSiguienteId();
    }
    
    //Obtiene la lista completa
    @GetMapping(value = URL)
    @ResponseBody
    public List<ConfiguracionVehiculo> listar() {
        return elementoService.listar();
    }
    
    //Obtiene una lista por nombre
    @GetMapping(value = URL + "/listarPorTipoVehiculoMarcaVehiculo/"
            + "{idTipoVehiculo}/{idMarcaVehiculo}")
    @ResponseBody
    public List<ConfiguracionVehiculo> listarPorTipoVehiculoMarcaVehiculo(
            @PathVariable int idTipoVehiculo, @PathVariable int idMarcaVehiculo) {
        return elementoService
                .listarPorTipoVehiculoMarcaVehiculo(idTipoVehiculo, idMarcaVehiculo);
    }
    
    //Agrega un registro
    @PostMapping(value = URL)
    public ResponseEntity<?> agregar(@RequestBody ConfiguracionVehiculo elemento) {
        try {
            ConfiguracionVehiculo e = elementoService.agregar(elemento);
            //Envia la nueva lista a los usuarios subscriptos
            template.convertAndSend(TOPIC + "/lista", elementoService.listar());
            return new ResponseEntity<>(new EstadoRespuestaAgregar(CodigoRespuesta.CREADO, 
                    MensajeRespuesta.AGREGADO, (e.getId()+1)), HttpStatus.CREATED);
        } catch(MessagingException e) {
            //Retorna codigo y mensaje de error de sicronizacion mediante socket
            return new ResponseEntity<>(new EstadoRespuesta(CodigoRespuesta.ERROR_SINC_SOCKET,
                    MensajeRespuesta.ERROR_SINC_SOCKET), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch(Exception e) {
            //Retorna codigo y mensaje de error interno en el servidor
            return new ResponseEntity<>(new EstadoRespuesta(CodigoRespuesta.ERROR_INTERNO_SERVIDOR,
                    MensajeRespuesta.ERROR_INTERNO_SERVIDOR), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    //Actualiza un registro
    @PutMapping(value = URL)
    public ResponseEntity<?> actualizar(@RequestBody ConfiguracionVehiculo elemento) {
        try {
            elementoService.actualizar(elemento);
            //Envia la nueva lista a los usuarios subscriptos
            template.convertAndSend(TOPIC + "/lista", elementoService.listar());
            return new ResponseEntity<>(new EstadoRespuesta(CodigoRespuesta.OK, 
                    MensajeRespuesta.ACTUALIZADO), HttpStatus.OK);
        } catch (ObjectOptimisticLockingFailureException oolfe) {
            //Retorna codigo y mensaje de error de operacion actualizada por otra transaccion
            return new ResponseEntity<>(new EstadoRespuesta(CodigoRespuesta.TRANSACCION_NO_ACTUALIZADA,
                    MensajeRespuesta.TRANSACCION_NO_ACTUALIZADA), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch(MessagingException e) {
            //Retorna codigo y mensaje de error de sicronizacion mediante socket
            return new ResponseEntity<>(new EstadoRespuesta(CodigoRespuesta.ERROR_SINC_SOCKET,
                    MensajeRespuesta.ERROR_SINC_SOCKET), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch(Exception e) {
            //Retorna codigo y mensaje de error interno en el servidor
            return new ResponseEntity<>(new EstadoRespuesta(CodigoRespuesta.ERROR_INTERNO_SERVIDOR,
                    MensajeRespuesta.ERROR_INTERNO_SERVIDOR), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    //Elimina un registro
    @DeleteMapping(value = URL)
    public ResponseEntity<?> eliminar(@RequestBody ConfiguracionVehiculo elemento) {
        try {
            elementoService.eliminar(elemento);
            return new ResponseEntity<>(new EstadoRespuesta(CodigoRespuesta.OK, 
                    MensajeRespuesta.ELIMINADO), HttpStatus.OK);
        } catch(Exception e) {
            //Retorna codigo y mensaje de error interno en el servidor
            return new ResponseEntity<>(new EstadoRespuesta(CodigoRespuesta.ERROR_INTERNO_SERVIDOR, 
                    MensajeRespuesta.ERROR_INTERNO_SERVIDOR), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
