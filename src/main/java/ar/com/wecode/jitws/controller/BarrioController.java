package ar.com.wecode.jitws.controller;

import ar.com.wecode.jitws.model.Barrio;
import ar.com.wecode.jitws.service.BarrioService;
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
    
    //Crea una instancia del servicio
    @Autowired
    BarrioService elementoService;
    
    //Obtiene el siguiente id
    @RequestMapping(value = "/barrio/obtenerSiguienteId")
    @ResponseBody
    public int obtenerSiguienteId() {
        return elementoService.obtenerSiguienteId();
    }
    
    //Obtiene la lista completa
    @RequestMapping(value = "/barrio")
    @ResponseBody
    public List<Barrio> listar() {
        return elementoService.listar();
    }
    
    //Obtiene una lista por nombre
    @RequestMapping(value = "/barrio/listarPorNombre/{nombre}")
    @ResponseBody
    public List<Barrio> listarPorNombre(@PathVariable String nombre) {
        return elementoService.listarPorNombre(nombre);
    }
    
}
