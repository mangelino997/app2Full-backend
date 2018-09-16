package ar.com.wecoode.jitws.controller;

import ar.com.wecoode.jitws.constant.RutaConstant;
import ar.com.wecoode.jitws.exception.CodigoRespuesta;
import ar.com.wecoode.jitws.exception.DuplicidadError;
import ar.com.wecoode.jitws.exception.EstadoRespuesta;
import ar.com.wecoode.jitws.exception.EstadoRespuestaAgregar;
import ar.com.wecoode.jitws.exception.MensajeRespuesta;
import ar.com.wecoode.jitws.model.SeguridadSocial;
import ar.com.wecoode.jitws.service.SeguridadSocialService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Clase SeguridadSocial Controller
 * @author blas
 */

@RestController
public class SeguridadSocialController {
    
    //Define la url
    private final String URL = RutaConstant.URL_BASE + "/seguridadsocial";
    //Define la url de subcripciones a sockets
    private final String TOPIC = RutaConstant.URL_TOPIC + "/seguridadsocial";
    
    //Define el template para el envio de datos por socket
    @Autowired
    private SimpMessagingTemplate template;
    
    //Crea una instancia del servicio
    @Autowired
    SeguridadSocialService elementoService;
    
    //Obtiene el siguiente id
    @RequestMapping(value = URL + "/obtenerSiguienteId")
    @ResponseBody
    public int obtenerSiguienteId() {
        return elementoService.obtenerSiguienteId();
    }
    
    //Obtiene la lista completa
    @RequestMapping(value = URL)
    @ResponseBody
    public List<SeguridadSocial> listar() {
        return elementoService.listar();
    }
    
    //Obtiene una lista por nombre
    @RequestMapping(value = URL + "/listarPorNombre/{nombre}")
    @ResponseBody
    public List<SeguridadSocial> listarPorNombre(@PathVariable String nombre) {
        return elementoService.listarPorNombre(nombre);
    }
    
    //Agrega un registro
    @PostMapping(value = URL)
    public ResponseEntity<?> agregar(@RequestBody SeguridadSocial elemento) {
        try {
            SeguridadSocial e = elementoService.agregar(elemento);
            //Envia la nueva lista a los usuarios subscriptos
            template.convertAndSend(TOPIC + "/lista", elementoService.listar());
            return new ResponseEntity(new EstadoRespuestaAgregar(CodigoRespuesta.CREADO, 
                    MensajeRespuesta.AGREGADO, (e.getId()+1)), HttpStatus.CREATED);
        } catch(DataIntegrityViolationException e) {
            //Obtiene mensaje de duplicidad de datos
            String[] partes = e.getMostSpecificCause().getMessage().split("'");
            //Determina que columna tiene el dato duplicado
            switch (partes[3]) {
                case DuplicidadError.NOMBRE_UNICO:
                    //Retorna codigo y mensaje de error de dato duplicado
                    return new ResponseEntity(new EstadoRespuesta(CodigoRespuesta.DATO_DUPLICADO_NOMBRE,
                            MensajeRespuesta.DATO_DUPLICADO + " '" + elemento.getNombre() + "'"),
                            HttpStatus.INTERNAL_SERVER_ERROR);
                case DuplicidadError.SITIOWEB_UNICO:
                    //Retorna codigo y mensaje de error de dato duplicado
                    return new ResponseEntity(new EstadoRespuesta(CodigoRespuesta.DATO_DUPLICADO_SITIOWEB,
                            MensajeRespuesta.DATO_DUPLICADO + " '" + elemento.getSitioWeb()+ "'"),
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
    public ResponseEntity<?> actualizar(@RequestBody SeguridadSocial elemento) {
        try {
            elementoService.actualizar(elemento);
            //Envia la nueva lista a los usuarios subscriptos
            template.convertAndSend(TOPIC + "/lista", elementoService.listar());
            return new ResponseEntity(new EstadoRespuesta(CodigoRespuesta.OK, 
                    MensajeRespuesta.ACTUALIZADO), HttpStatus.OK);
        } catch(DataIntegrityViolationException dive) {
            //Obtiene mensaje de duplicidad de datos
            String[] partes = dive.getMostSpecificCause().getMessage().split("'");
            //Determina que columna tiene el dato duplicado
            switch (partes[3]) {
                case DuplicidadError.NOMBRE_UNICO:
                    //Retorna codigo y mensaje de error de dato duplicado
                    return new ResponseEntity(new EstadoRespuesta(CodigoRespuesta.DATO_DUPLICADO_NOMBRE,
                            MensajeRespuesta.DATO_DUPLICADO + " '" + elemento.getNombre() + "'"),
                            HttpStatus.INTERNAL_SERVER_ERROR);
                case DuplicidadError.SITIOWEB_UNICO:
                    //Retorna codigo y mensaje de error de dato duplicado
                    return new ResponseEntity(new EstadoRespuesta(CodigoRespuesta.DATO_DUPLICADO_SITIOWEB,
                            MensajeRespuesta.DATO_DUPLICADO + " '" + elemento.getSitioWeb()+ "'"),
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
    public ResponseEntity<?> eliminar(@RequestBody SeguridadSocial elemento) {
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
