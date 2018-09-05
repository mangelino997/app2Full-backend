package ar.com.wecoode.jitws.controller;

import ar.com.wecoode.jitws.constant.RutaConstant;
import ar.com.wecoode.jitws.exception.CodigoRespuesta;
import ar.com.wecoode.jitws.exception.DuplicidadError;
import ar.com.wecoode.jitws.exception.EstadoRespuesta;
import ar.com.wecoode.jitws.exception.MensajeRespuesta;
import ar.com.wecoode.jitws.model.Sindicato;
import ar.com.wecoode.jitws.service.SindicatoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
 * Clase Sindicato Controller
 * @author blas
 */

@RestController
public class SindicatoController {
    
    //Define la url
    private final String URL = RutaConstant.URL_BASE + "/sindicato";
    
    //Crea una instancia del servicio
    @Autowired
    SindicatoService elementoService;
    
    //Obtiene el siguiente id
    @RequestMapping(value = URL + "/obtenerSiguienteId")
    @ResponseBody
    public int obtenerSiguienteId() {
        return elementoService.obtenerSiguienteId();
    }
    
    //Obtiene la lista completa
    @RequestMapping(value = URL)
    @ResponseBody
    public List<Sindicato> listar() {
        return elementoService.listar();
    }
    
    //Obtiene una lista por nombre
    @RequestMapping(value = URL + "/listarPorNombre/{nombre}")
    @ResponseBody
    public List<Sindicato> listarPorNombre(@PathVariable String nombre) {
        return elementoService.listarPorNombre(nombre);
    }
    
    //Agrega un registro
    @PostMapping(value = URL)
    public ResponseEntity<?> agregar(@RequestBody Sindicato elemento) {
        try {
            elementoService.agregar(elemento);
            return new ResponseEntity(new EstadoRespuesta(CodigoRespuesta.CREADO, 
                    MensajeRespuesta.AGREGADO), HttpStatus.CREATED);
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
        } catch(Exception e) {
            //Retorna codigo y mensaje de error interno en el servidor
            return new ResponseEntity(new EstadoRespuesta(CodigoRespuesta.ERROR_INTERNO_SERVIDOR,
                    MensajeRespuesta.ERROR_INTERNO_SERVIDOR), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    //Actualiza un registro
    @PutMapping(value = URL)
    public ResponseEntity<?> actualizar(@RequestBody Sindicato elemento) {
        try {
            elementoService.actualizar(elemento);
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
        } catch(Exception e) {
            //Retorna codigo y mensaje de error interno en el servidor
            return new ResponseEntity(new EstadoRespuesta(CodigoRespuesta.ERROR_INTERNO_SERVIDOR,
                    MensajeRespuesta.ERROR_INTERNO_SERVIDOR), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    //Elimina un registro
    @DeleteMapping(value = URL)
    public ResponseEntity<?> eliminar(@RequestBody Sindicato elemento) {
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
