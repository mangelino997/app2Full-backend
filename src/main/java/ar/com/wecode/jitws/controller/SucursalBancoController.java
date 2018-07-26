package ar.com.wecode.jitws.controller;

import ar.com.wecode.jitws.model.SucursalBanco;
import ar.com.wecode.jitws.service.SucursalBancoService;
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
    
    //Crea una instancia del servicio
    @Autowired
    SucursalBancoService elementoService;
    
    //Obtiene el siguiente id
    @RequestMapping(value = "/sucursalBanco/obtenerSiguienteId")
    @ResponseBody
    public int obtenerSiguienteId() {
        return elementoService.obtenerSiguienteId();
    }
    
    //Obtiene la lista completa
    @RequestMapping(value = "/sucursalBanco")
    @ResponseBody
    public List<SucursalBanco> listar() {
        return elementoService.listar();
    }
    
    //Obtiene una lista por nombre
    @RequestMapping(value = "/sucursalBanco/listarPorNombre/{nombre}")
    @ResponseBody
    public List<SucursalBanco> listarPorNombre(@PathVariable String nombre) {
        return elementoService.listarPorNombre(nombre);
    }
    
    //Obtiene una lista por nombre de banco
    @RequestMapping(value = "/sucursalBanco/listarPorNombreBanco/{nombreBanco}")
    @ResponseBody
    public List<SucursalBanco> listarPorNombreBanco(@PathVariable String nombreBanco) {
        return elementoService.listarPorNombreBanco(nombreBanco);
    }
    
}
