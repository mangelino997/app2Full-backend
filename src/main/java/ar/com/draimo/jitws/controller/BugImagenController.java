package ar.com.draimo.jitws.controller;

import ar.com.draimo.jitws.constant.RutaConstant;
import ar.com.draimo.jitws.exception.CodigoRespuesta;
import ar.com.draimo.jitws.exception.EstadoRespuesta;
import ar.com.draimo.jitws.exception.MensajeRespuesta;
import ar.com.draimo.jitws.model.BugImagen;
import ar.com.draimo.jitws.service.BugImagenService;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Clase Barrio Controller
 * @author blas
 */

@RestController
public class BugImagenController {
    
    //Define la url
    private final String URL = RutaConstant.URL_BASE + "/bugimagen";
    //Define la url de subcripciones a sockets
    private final String TOPIC = RutaConstant.URL_TOPIC + "/bugimagen";
    
    //Define el template para el envio de datos por socket
    @Autowired
    private SimpMessagingTemplate template;
    
    //Crea una instancia del servicio
    @Autowired
    BugImagenService elementoService;
    
    //Obtiene el siguiente id
    @GetMapping(value = URL + "/obtenerSiguienteId")
    @ResponseBody
    public int obtenerSiguienteId() {
        return elementoService.obtenerSiguienteId();
    }
    
    //Obtiene por id
    @GetMapping(value = URL + "/obtenerImagenPorId/{id}")
    @ResponseBody
    public ResponseEntity<InputStreamResource> obtenerImagenPorId(@PathVariable int id) {
        try {
            BugImagen imagen = elementoService.obtenerPorId(id);
            HttpHeaders respHeaders = new HttpHeaders();
            respHeaders.add("Content-Type", imagen.getTipo());
            respHeaders.setContentLength(imagen.getTamanio());
            respHeaders.setContentDispositionFormData("attachment", imagen.getNombre());
            InputStreamResource isr = new InputStreamResource(new ByteArrayInputStream(imagen.getDatos()));
            return new ResponseEntity<InputStreamResource>(isr, respHeaders, HttpStatus.OK);
        } catch(Exception e) {
            //Muestra mensaje de error interno en el servidor
            return new ResponseEntity(new EstadoRespuesta(CodigoRespuesta.SIN_CONTENIDO, 
                    MensajeRespuesta.SIN_CONTENIDO, 0), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    //Obtiene por id
    @GetMapping(value = URL + "/obtenerPorId/{id}")
    @ResponseBody
    public ResponseEntity<InputStreamResource> obtenerPorId(@PathVariable int id) {
        try {
            BugImagen element = elementoService.obtenerPorId(id);
            HttpHeaders respHeaders = new HttpHeaders();
            respHeaders.add("Content-Type", element.getTipo());
            respHeaders.setContentLength(element.getTamanio());
            respHeaders.setContentDispositionFormData("attachment", element.getNombre());
            InputStreamResource isr = new InputStreamResource(new ByteArrayInputStream(element.getDatos()));
            return new ResponseEntity<InputStreamResource>(isr, respHeaders, HttpStatus.OK);
        } catch(IOException e) {
            //Muestra mensaje de error interno en el servidor
            return  MensajeRespuesta.sinContenido();
        }
    }
    
    //Agrega un registro
    @PostMapping(value = URL)
    public ResponseEntity<?> agregar(@RequestParam("archivo") MultipartFile archivo) {
        try {
            BugImagen a = elementoService.agregar(archivo);
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
    public ResponseEntity<?> actualizar(@RequestParam("idImagen") int idImagen, @RequestParam("archivo") MultipartFile archivo) {
        try {
            //Actualiza el registro
            elementoService.actualizar(idImagen, archivo);
            //Retorna mensaje de actualizado con exito
            return MensajeRespuesta.actualizado();
        } catch (DataIntegrityViolationException dive) {
            //Retorna mensaje de dato duplicado
            return MensajeRespuesta.datoDuplicado(dive);
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
    @DeleteMapping(value = URL)
    public ResponseEntity<?> eliminar(@RequestBody BugImagen elemento) {
        try {
            elementoService.eliminar(elemento);
            //Retorna mensaje de eliminado con exito
            return MensajeRespuesta.eliminado();
        } catch(Exception e) {
            //Retorna mensaje de error interno en el servidor
            return MensajeRespuesta.error();
        }
    }
    
}
