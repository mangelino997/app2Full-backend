package ar.com.wecoode.jitws.controller;

import ar.com.wecoode.jitws.constant.RutaConstant;
import java.time.LocalDate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Clase Fecha Controller
 * @author blas
 */

@RestController
public class FechaController {
    
    //Define la url
    private final String URL = RutaConstant.URL_BASE + "/fecha";
    
    //Obtiene el siguiente id
    @GetMapping(value = URL)
    @ResponseBody
    public LocalDate fecha() {
        return LocalDate.now();
    }
    
}
