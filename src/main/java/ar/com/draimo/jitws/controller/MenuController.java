package ar.com.draimo.jitws.controller;

import ar.com.draimo.jitws.constant.RutaConstant;
import ar.com.draimo.jitws.dto.MenuDTO;
import ar.com.draimo.jitws.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Clase Menu Controller
 * @author blas
 */

@RestController
public class MenuController {
    
    //Define la url
    private final String URL = RutaConstant.URL_BASE + "/menu";
    
    //Crea una instancia del servicio
    @Autowired
    MenuService elementoService;
    
    //Obtiene la lista por rol
    @GetMapping(value = URL + "/{idRol}")
    @ResponseBody
    public MenuDTO listar(@PathVariable int idRol) {
        return elementoService.listar(idRol);
    }
    
}
