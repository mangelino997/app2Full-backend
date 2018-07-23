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
    
    @Autowired
    BarrioService elementoService;
    
    //Mapea el metodo con la url para listar
    @RequestMapping(value = "/barrio")
    @ResponseBody
    public List<Barrio> listar() {
        return elementoService.listar();
    }
    
    //Mapea el metedo con la url para listar por nombre
    @RequestMapping(value = "/barrio/listarPorNombre/{nombre}")
    @ResponseBody
    public List<Barrio> listarPorNombre(@PathVariable String nombre) {
        return elementoService.listarPorNombre(nombre);
    }
    
}
