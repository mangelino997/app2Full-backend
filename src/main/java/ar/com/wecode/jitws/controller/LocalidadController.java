package ar.com.wecode.jitws.controller;

import ar.com.wecode.jitws.model.Localidad;
import ar.com.wecode.jitws.service.LocalidadService;
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
public class LocalidadController {
    
    //Crea una instancia del servicio
    @Autowired
    LocalidadService elementoService;
    
    //Obtiene el siguiente id
    @RequestMapping(value = "/localidad/obtenerSiguienteId")
    @ResponseBody
    public int obtenerSiguienteId() {
        return elementoService.obtenerSiguienteId();
    }
    
    //Obtiene la lista completa
    @RequestMapping(value = "/localidad")
    @ResponseBody
    public List<Localidad> listar() {
        return elementoService.listar();
    }
    
    //Obtiene una lista por nombre
    @RequestMapping(value = "/localidad/listarPorNombre/{nombre}")
    @ResponseBody
    public List<Localidad> listarPorNombre(@PathVariable String nombre) {
        return elementoService.listarPorNombre(nombre);
    }
    
    //Obtiene una lista por provincia
    @RequestMapping(value = "/localidad/listarPorProvincia/{id}")
    @ResponseBody
    public List<Localidad> listarPorProvincia(@PathVariable int id) {
        return elementoService.listarPorProvincia(id);
    }
    
}
