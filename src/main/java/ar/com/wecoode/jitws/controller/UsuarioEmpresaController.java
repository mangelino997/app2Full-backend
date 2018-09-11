package ar.com.wecoode.jitws.controller;

import ar.com.wecoode.jitws.constant.RutaConstant;
import ar.com.wecoode.jitws.exception.CodigoRespuesta;
import ar.com.wecoode.jitws.exception.EstadoRespuesta;
import ar.com.wecoode.jitws.exception.MensajeRespuesta;
import ar.com.wecoode.jitws.model.Empresa;
import ar.com.wecoode.jitws.model.UsuarioEmpresa;
import ar.com.wecoode.jitws.service.UsuarioEmpresaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
 * Clase UsuarioEmpresa Controller
 * @author blas
 */

@RestController
public class UsuarioEmpresaController {
    
    //Define la url
    private final String URL = RutaConstant.URL_BASE + "/usuarioempresa";
    
    //Crea una instancia del servicio
    @Autowired
    UsuarioEmpresaService elementoService;
    
    //Obtiene el siguiente id
    @RequestMapping(value = URL + "/obtenerSiguienteId")
    @ResponseBody
    public int obtenerSiguienteId() {
        return elementoService.obtenerSiguienteId();
    }
    
    //Obtiene la lista completa
    @RequestMapping(value = URL)
    @ResponseBody
    public List<UsuarioEmpresa> listar() {
        return elementoService.listar();
    }
    
    //Obtiene una lista por usuario
    @RequestMapping(value = URL + "/listarPorUsuario/{idUsuario}")
    @ResponseBody
    public List<UsuarioEmpresa> listarPorUsuario(@PathVariable int idUsuario) {
        return elementoService.listarPorUsuario(idUsuario);
    }
    
    //Obtiene las empresas activas del usuario
    @RequestMapping(value = URL + "/listarEmpresasActivasDeUsuario/{idUsuario}")
    @ResponseBody
    public List<Empresa> listarEmpresasActivasDeUsuario(@PathVariable int idUsuario) {
        return elementoService.listarEmpresasActivasDeUsuario(idUsuario);
    }
    
    //Agrega un registro
    @PostMapping(value = URL)
    public ResponseEntity<?> agregar(@RequestBody UsuarioEmpresa elemento) {
        try {
            elementoService.agregar(elemento);
            return new ResponseEntity(new EstadoRespuesta(CodigoRespuesta.CREADO, 
                    MensajeRespuesta.AGREGADO), HttpStatus.CREATED);
        } catch(Exception e) {
            //Retorna codigo y mensaje de error interno en el servidor
            return new ResponseEntity(new EstadoRespuesta(CodigoRespuesta.ERROR_INTERNO_SERVIDOR,
                    MensajeRespuesta.ERROR_INTERNO_SERVIDOR), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    //Actualiza un registro
    @PutMapping(value = URL)
    public ResponseEntity<?> actualizar(@RequestBody List<UsuarioEmpresa> elemento) {
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
    
    //Elimina un registro
    @DeleteMapping(value = URL)
    public ResponseEntity<?> eliminar(@RequestBody UsuarioEmpresa elemento) {
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
    
    /*
     * Asigna todas las empresas a cada uno de los usuarios, manteniendo el dato
     * 'Mostrar' de cada empresa
     */
    @RequestMapping(value = URL + "/eliminarTabla")
    @ResponseBody
    public List<UsuarioEmpresa> eliminarTabla() {
        return elementoService.eliminarTabla();
    }
    
    /*
     * Asigna todas las empresas a cada uno de los usuarios, manteniendo el dato
     * 'Mostrar' de cada empresa
     */
    @RequestMapping(value = URL + "/reestablecerTabla")
    @ResponseBody
    public void reestablecerTabla(@RequestBody List<UsuarioEmpresa> elemento) {
        elementoService.reestablecerTabla(elemento);
    }
    
    /*
     * Asigna todas las empresas a cada uno de los usuarios, eliminando todo los
     * datos y reestableciendo desde cero
     */
    @RequestMapping(value = URL + "/reestablecerTablaDesdeCero")
    @ResponseBody
    public void reestablecerTablaDesdeCero() {
        elementoService.reestablecerTablaDesdeCero();
    }
    
}
