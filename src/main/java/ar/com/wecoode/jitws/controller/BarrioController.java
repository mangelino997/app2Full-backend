package ar.com.wecoode.jitws.controller;

import ar.com.wecoode.jitws.constant.RutaConstant;
import ar.com.wecoode.jitws.model.Barrio;
import ar.com.wecoode.jitws.service.BarrioService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Clase Barrio Controller
 * @author blas
 */

@RestController
public class BarrioController {
    
    //Define la url
    private final String URL = RutaConstant.URL_BASE + "/barrio";
    
    //Crea una instancia del servicio
    @Autowired
    BarrioService elementoService;
    
    //Obtiene el siguiente id
    @RequestMapping(value = URL + "/obtenerSiguienteId")
    @ResponseBody
    public int obtenerSiguienteId() {
        return elementoService.obtenerSiguienteId();
    }
    
    //Obtiene la lista completa
    @RequestMapping(value = URL)
    @ResponseBody
    public List<Barrio> listar() {
        return elementoService.listar();
    }
    
    //Obtiene una lista por nombre
    @RequestMapping(value = URL + "/listarPorNombre/{nombre}")
    @ResponseBody
    public List<Barrio> listarPorNombre(@PathVariable String nombre) {
        return elementoService.listarPorNombre(nombre);
    }
    
}
