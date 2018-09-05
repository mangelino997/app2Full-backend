package ar.com.wecoode.jitws.controller;

import ar.com.wecoode.jitws.constant.RutaConstant;
import ar.com.wecoode.jitws.dto.MenuDTO;
import ar.com.wecoode.jitws.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
    @RequestMapping(value = URL + "/{idRol}")
    @ResponseBody
    public MenuDTO listar(@PathVariable int idRol) {
        return elementoService.listar(idRol);
    }
    
}
