package ar.com.wecoode.jitws.controller;

import ar.com.wecoode.jitws.constant.RutaConstant;
import ar.com.wecoode.jitws.model.Pais;
import ar.com.wecoode.jitws.model.Provincia;
import ar.com.wecoode.jitws.service.ProvinciaService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Clase Provincia Controller
 * @author blas
 */

@RestController
public class ProvinciaController {
    
    //Define la url
    private final String URL = RutaConstant.URL_BASE + "/provincia";
    
    //Crea una instancia del servicio
    @Autowired
    ProvinciaService elementoService;
    
    //Obtiene el siguiente id
    @RequestMapping(value = URL + "/obtenerSiguienteId")
    @ResponseBody
    public int obtenerSiguienteId() {
        return elementoService.obtenerSiguienteId();
    }
    
    //Obtiene la lista completa
    @RequestMapping(value = URL)
    @ResponseBody
    public List<Provincia> listar() {
        return elementoService.listar();
    }
    
    //Obtiene una lista por nombre
    @RequestMapping(value = URL + "/listarPorNombre/{nombre}")
    @ResponseBody
    public List<Provincia> listarPorNombre(@PathVariable String nombre) {
        return elementoService.listarPorNombre(nombre);
    }
    
    //Obtiene una lista por provincia
    @RequestMapping(value = URL + "/listarPorPais/{id}")
    @ResponseBody
    public List<Provincia> listarPorPais(@RequestBody Optional<Pais> pais) {
        return elementoService.listarPorPais(pais);
    }
    
}
