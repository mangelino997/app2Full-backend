package ar.com.wecoode.jitws.controller;

import ar.com.wecoode.jitws.constant.RutaConstant;
import ar.com.wecoode.jitws.dto.SubopcionPestaniaDTO;
import ar.com.wecoode.jitws.exception.CodigoRespuesta;
import ar.com.wecoode.jitws.exception.EstadoRespuesta;
import ar.com.wecoode.jitws.exception.MensajeRespuesta;
import ar.com.wecoode.jitws.model.Pestania;
import ar.com.wecoode.jitws.model.SubopcionPestania;
import ar.com.wecoode.jitws.service.SubopcionPestaniaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Clase RolSubopcion Controller
 * @author blas
 */

@RestController
public class SubopcionPestaniaController {
    
    //Define la url
    private final String URL = RutaConstant.URL_BASE + "/subopcionpestania";
    
    //Crea una instancia del servicio
    @Autowired
    SubopcionPestaniaService elementoService;
    
    //Obtiene la lista completa
    @RequestMapping(value = URL)
    @ResponseBody
    public List<SubopcionPestania> listar() {
        return elementoService.listar();
    }
    
    //Obtiene una lista por rol y subopcion
    @RequestMapping(value = URL + "/listarPorRolSubopcion/{idRol}/{idSubopcion}")
    @ResponseBody
    public List<Pestania> listarPorRolSubopcion(@PathVariable int idRol, @PathVariable int idSubopcion) {
        return elementoService.listarPorRolSubopcion(idRol, idSubopcion);
    }
    
    //Actualiza un registro
    @PutMapping(value = URL)
    public ResponseEntity<?> actualizar(@RequestBody SubopcionPestaniaDTO elemento) {
        try {
            elementoService.actualizar(elemento);
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
    
    //Asigna todas las pestañas a cada una de las subopciones
    @RequestMapping(value = URL + "/asignarPestaniasASubopciones")
    @ResponseBody
    public void asignarPestaniasASubopciones() {
        elementoService.asignarPestaniasASubopciones();
    }
    
    /*
     * Asigna todas las pestañas a cada una de las subopciones, eliminando todo los
     * datos y reestableciendo desde cero
     */
    @RequestMapping(value = URL + "/reestablecerTablaDesdeCero")
    @ResponseBody
    public void reestablecerTablaDesdeCero() {
        elementoService.reestablecerTablaDesdeCero();
    }
    
}
