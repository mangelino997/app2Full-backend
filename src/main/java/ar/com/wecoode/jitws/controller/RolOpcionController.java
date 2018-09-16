package ar.com.wecoode.jitws.controller;

import ar.com.wecoode.jitws.constant.RutaConstant;
import ar.com.wecoode.jitws.model.Opcion;
import ar.com.wecoode.jitws.model.RolOpcion;
import ar.com.wecoode.jitws.service.RolOpcionService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
    //Define la url de subcripciones a sockets
    private final String TOPIC = RutaConstant.URL_TOPIC + "/rolopcion";
    
    //Define el template para el envio de datos por socket
    @Autowired
    private SimpMessagingTemplate template;
    
    //Crea una instancia del servicio
    @Autowired
    RolOpcionService elementoService;
    
    //Obtiene la lista completa
    @RequestMapping(value = URL)
    @ResponseBody
    public List<RolOpcion> listar() {
        return elementoService.listar();
    }
    
    //Obtiene una lista por rol y subopcion
    @RequestMapping(value = URL + "/listarPorRolSubopcion/{idRol}/{idSubopcion}")
    @ResponseBody
    public List<Opcion> listarPorRolSubopcion(@PathVariable int idRol, @PathVariable int idSubopcion) {
        return elementoService.listarPorRolSubopcion(idRol, idSubopcion);
    }
    
}
