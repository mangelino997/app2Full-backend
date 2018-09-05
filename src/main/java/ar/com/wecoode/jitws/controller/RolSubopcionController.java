package ar.com.wecoode.jitws.controller;

import ar.com.wecoode.jitws.constant.RutaConstant;
import ar.com.wecoode.jitws.dto.RolSubopcionDTO;
import ar.com.wecoode.jitws.exception.CodigoRespuesta;
import ar.com.wecoode.jitws.exception.EstadoRespuesta;
import ar.com.wecoode.jitws.exception.MensajeRespuesta;
import ar.com.wecoode.jitws.model.RolSubopcion;
import ar.com.wecoode.jitws.model.Submodulo;
import ar.com.wecoode.jitws.model.Subopcion;
import ar.com.wecoode.jitws.service.RolSubopcionService;
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
public class RolSubopcionController {
    
    //Define la url
    private final String URL = RutaConstant.URL_BASE + "/rolsubopcion";
    
    //Crea una instancia del servicio
    @Autowired
    RolSubopcionService elementoService;
    
    //Obtiene la lista completa
    @RequestMapping(value = URL)
    @ResponseBody
    public List<RolSubopcion> listar() {
        return elementoService.listar();
    }
    
    //Obtiene una lista por rol y modulo
    @RequestMapping(value = URL + "/listarPorRolModulo/{idRol}/{idModulo}")
    @ResponseBody
    public List<Submodulo> listarPorRolModulo(@PathVariable int idRol, @PathVariable int idModulo) {
        return elementoService.listarPorRolModulo(idRol, idModulo);
    }
    
    //Obtiene una lista por rol y submodulo
    @RequestMapping(value = URL + "/listarPorRolSubmodulo/{idRol}/{idSubmodulo}")
    @ResponseBody
    public List<Subopcion> listarPorRolSubmodulo(@PathVariable int idRol, @PathVariable int idSubmodulo) {
        return elementoService.listarPorRolSubmodulo(idRol, idSubmodulo);
    }
    
    //Actualiza un registro
    @PutMapping(value = URL)
    public ResponseEntity<?> actualizar(@RequestBody RolSubopcionDTO elemento) {
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
    
    //Agrega una subopcion a todos los roles
    @RequestMapping(value = URL + "/agregarSubopcionARoles")
    @ResponseBody
    public void agregarSubopcionARoles(@RequestBody Subopcion elemento) {
        elementoService.agregarSubopcionARoles(elemento);
    }
    
    //Elimina una subopcion por id de todos los roles
    @RequestMapping(value = URL + "/eliminarSubopcionDeRoles/{idSubopcion}")
    @ResponseBody
    public void eliminarSubopcionDeRoles(@PathVariable int idSubopcion) {
        elementoService.eliminarSubopcionDeRoles(idSubopcion);
    }
    
    /*
     * Asigna todas las subopciones a cada uno de los roles, eliminando todo los
     * datos y reestableciendo desde cero
     */
    @RequestMapping(value = URL + "/reestablecerTablaDesdeCero")
    @ResponseBody
    public void reestablecerTablaDesdeCero() {
        elementoService.reestablecerTablaDesdeCero();
    }
    
}
