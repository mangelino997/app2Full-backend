package ar.com.wecoode.jitws.controller;

import ar.com.wecoode.jitws.constant.RutaConstant;
import ar.com.wecoode.jitws.exception.CodigoRespuesta;
import ar.com.wecoode.jitws.exception.DuplicidadError;
import ar.com.wecoode.jitws.exception.EstadoRespuesta;
import ar.com.wecoode.jitws.exception.EstadoRespuestaAgregar;
import ar.com.wecoode.jitws.exception.MensajeRespuesta;
import ar.com.wecoode.jitws.model.ContactoBanco;
import ar.com.wecoode.jitws.service.ContactoBancoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
 * Clase ContactoBanco Controller
 * @author blas
 */

@RestController
public class ContactoBancoController {
    
    //Define la url
    private final String URL = RutaConstant.URL_BASE + "/contactobanco";
    //Define la url de subcripciones a sockets
    private final String TOPIC = RutaConstant.URL_TOPIC + "/contactobanco";
    
    //Define el template para el envio de datos por socket
    @Autowired
    private SimpMessagingTemplate template;
    
    //Crea una instancia del servicio
    @Autowired
    ContactoBancoService elementoService;
    
    //Obtiene el siguiente id
    @GetMapping(value = URL + "/obtenerSiguienteId")
    @ResponseBody
    public int obtenerSiguienteId() {
        return elementoService.obtenerSiguienteId();
    }
    
    //Obtiene la lista completa
    @GetMapping(value = URL)
    @ResponseBody
    public List<ContactoBanco> listar() {
        return elementoService.listar();
    }
    
    //Obtiene una lista por nombre
    @GetMapping(value = URL + "/listarPorNombre/{nombre}")
    @ResponseBody
    public List<ContactoBanco> listarPorNombre(@PathVariable String nombre) {
        return elementoService.listarPorNombre(nombre);
    }
    
    //Obtiene una lista de contactos por sucursal banco
    @GetMapping(value = URL + "/listarPorSucursalBanco/{id}")
    @ResponseBody
    public List<ContactoBanco> listarPorSucursalBanco(@PathVariable int id) {
        return elementoService.listarPorSucursalBanco(id);
    }
    
    //Agrega un registro
    @PostMapping(value = URL)
    public ResponseEntity<?> agregar(@RequestBody ContactoBanco elemento) {
        try {
            ContactoBanco e = elementoService.agregar(elemento);
            //Envia la nueva lista a los usuarios subscriptos
            template.convertAndSend(TOPIC + "/lista", elementoService.listar());
            return new ResponseEntity(new EstadoRespuestaAgregar(CodigoRespuesta.CREADO, 
                    MensajeRespuesta.AGREGADO, (e.getId()+1)), HttpStatus.CREATED);
        } catch(DataIntegrityViolationException e) {
            //Obtiene mensaje de duplicidad de datos
            String[] partes = e.getMostSpecificCause().getMessage().split("'");
            //Determina que columna tiene el dato duplicado
            switch (partes[3]) {
                case DuplicidadError.TELEFONO_FIJO_UNICO:
                    //Retorna codigo y mensaje de error de dato duplicado
                    return new ResponseEntity(new EstadoRespuesta(CodigoRespuesta.DATO_DUPLICADO_TELEFONO_FIJO,
                            MensajeRespuesta.DATO_DUPLICADO + " '" + elemento.getTelefonoFijo() + "'"),
                            HttpStatus.INTERNAL_SERVER_ERROR);
                case DuplicidadError.TELEFONO_MOVIL_UNICO:
                    //Retorna codigo y mensaje de error de dato duplicado
                    return new ResponseEntity(new EstadoRespuesta(CodigoRespuesta.DATO_DUPLICADO_TELEFONO_MOVIL,
                            MensajeRespuesta.DATO_DUPLICADO + " '" + elemento.getTelefonoMovil() + "'"),
                            HttpStatus.INTERNAL_SERVER_ERROR);
                case DuplicidadError.CORREOELECTRONICO_UNICO:
                    //Retorna codigo y mensaje de error de dato duplicado
                    return new ResponseEntity(new EstadoRespuesta(CodigoRespuesta.DATO_DUPLICADO_CORREOELECTRONICO,
                            MensajeRespuesta.DATO_DUPLICADO + " '" + elemento.getCorreoelectronico() + "'"),
                            HttpStatus.INTERNAL_SERVER_ERROR);
                default:
                    //Retorna codigo y mensaje de error interno en el servidor
                    return new ResponseEntity(new EstadoRespuesta(CodigoRespuesta.ERROR_INTERNO_SERVIDOR,
                            MensajeRespuesta.ERROR_INTERNO_SERVIDOR), HttpStatus.INTERNAL_SERVER_ERROR);
            }
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
    public ResponseEntity<?> actualizar(@RequestBody ContactoBanco elemento) {
        try {
            elementoService.actualizar(elemento);
            //Envia la nueva lista a los usuarios subscriptos
            template.convertAndSend(TOPIC + "/lista", elementoService.listar());
            return new ResponseEntity(new EstadoRespuesta(CodigoRespuesta.OK, 
                    MensajeRespuesta.ACTUALIZADO), HttpStatus.OK);
        } catch(DataIntegrityViolationException e) {
            //Obtiene mensaje de duplicidad de datos
            String[] partes = e.getMostSpecificCause().getMessage().split("'");
            //Determina que columna tiene el dato duplicado
            switch (partes[3]) {
                case DuplicidadError.TELEFONO_FIJO_UNICO:
                    //Retorna codigo y mensaje de error de dato duplicado
                    return new ResponseEntity(new EstadoRespuesta(CodigoRespuesta.DATO_DUPLICADO_TELEFONO_FIJO,
                            MensajeRespuesta.DATO_DUPLICADO + " '" + elemento.getTelefonoFijo() + "'"),
                            HttpStatus.INTERNAL_SERVER_ERROR);
                case DuplicidadError.TELEFONO_MOVIL_UNICO:
                    //Retorna codigo y mensaje de error de dato duplicado
                    return new ResponseEntity(new EstadoRespuesta(CodigoRespuesta.DATO_DUPLICADO_TELEFONO_MOVIL,
                            MensajeRespuesta.DATO_DUPLICADO + " '" + elemento.getTelefonoMovil() + "'"),
                            HttpStatus.INTERNAL_SERVER_ERROR);
                case DuplicidadError.CORREOELECTRONICO_UNICO:
                    //Retorna codigo y mensaje de error de dato duplicado
                    return new ResponseEntity(new EstadoRespuesta(CodigoRespuesta.DATO_DUPLICADO_CORREOELECTRONICO,
                            MensajeRespuesta.DATO_DUPLICADO + " '" + elemento.getCorreoelectronico() + "'"),
                            HttpStatus.INTERNAL_SERVER_ERROR);
                default:
                    //Retorna codigo y mensaje de error interno en el servidor
                    return new ResponseEntity(new EstadoRespuesta(CodigoRespuesta.ERROR_INTERNO_SERVIDOR,
                            MensajeRespuesta.ERROR_INTERNO_SERVIDOR), HttpStatus.INTERNAL_SERVER_ERROR);
            }
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
    public ResponseEntity<?> eliminar(@RequestBody ContactoBanco elemento) {
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
