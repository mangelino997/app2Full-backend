package ar.com.draimo.jitws.controller;

import ar.com.draimo.jitws.constant.RutaConstant;
import ar.com.draimo.jitws.exception.MensajeRespuesta;
import ar.com.draimo.jitws.model.Opcion;
import ar.com.draimo.jitws.model.RolOpcion;
import ar.com.draimo.jitws.service.RolOpcionService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.MessagingException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Clase RolOpcion Controller
 * @author blas
 */

@RestController
public class RolOpcionController {
    
    //Define la url
    private final String URL = RutaConstant.URL_BASE + "/rolopcion";
    
    //Crea una instancia del servicio
    @Autowired
    RolOpcionService elementoService;
    
    //Obtiene la lista completa
    @GetMapping(value = URL)
    @ResponseBody
    public List<RolOpcion> listar() {
        return elementoService.listar();
    }
    
    //Obtiene una lista por rol y subopcion
    @GetMapping(value = URL + "/listarPorRolSubopcion/{idRol}/{idSubopcion}")
    @ResponseBody
    public List<Opcion> listarPorRolSubopcion(@PathVariable int idRol, @PathVariable int idSubopcion) {
        return elementoService.listarPorRolYSubopcion(idRol, idSubopcion);
    }
    
    /*
     * Asigna todas las empresas a cada uno de los usuarios, eliminando todo los
     * datos y reestableciendo desde cero
     */
    @GetMapping(value = URL + "/reestablecerTablaDesdeCero")
    @ResponseBody
    public ResponseEntity<?> reestablecerTablaDesdeCero() {
        try {
            elementoService.reestablecerTablaDesdeCero();
            return MensajeRespuesta.tablaReestablecida();
        }catch(MessagingException e) {
            //Retorna codigo y mensaje de error de sicronizacion mediante socket
            return MensajeRespuesta.errorSincSocket();
        }
    }
    
}
