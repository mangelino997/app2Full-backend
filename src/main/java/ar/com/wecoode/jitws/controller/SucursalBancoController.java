package ar.com.wecoode.jitws.controller;

import ar.com.wecoode.jitws.constant.RutaConstant;
import ar.com.wecoode.jitws.model.SucursalBanco;
import ar.com.wecoode.jitws.service.SucursalBancoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador Sucursal Banco
 * @author blas
 */

@RestController
public class SucursalBancoController {
    
    //Define la url
    private final String URL = RutaConstant.URL_BASE + "/sucursalBanco";
    
    //Crea una instancia del servicio
    @Autowired
    SucursalBancoService elementoService;
    
    //Obtiene el siguiente id
    @RequestMapping(value = URL + "/obtenerSiguienteId")
    @ResponseBody
    public int obtenerSiguienteId() {
        return elementoService.obtenerSiguienteId();
    }
    
    //Obtiene la lista completa
    @RequestMapping(value = URL)
    @ResponseBody
    public List<SucursalBanco> listar() {
        return elementoService.listar();
    }
    
    //Obtiene una lista por nombre
    @RequestMapping(value = URL + "/listarPorNombre/{nombre}")
    @ResponseBody
    public List<SucursalBanco> listarPorNombre(@PathVariable String nombre) {
        return elementoService.listarPorNombre(nombre);
    }
    
    //Obtiene una lista por nombre de banco
    @RequestMapping(value = URL + "/listarPorNombreBanco/{nombreBanco}")
    @ResponseBody
    public List<SucursalBanco> listarPorNombreBanco(@PathVariable String nombreBanco) {
        return elementoService.listarPorNombreBanco(nombreBanco);
    }
    
}
