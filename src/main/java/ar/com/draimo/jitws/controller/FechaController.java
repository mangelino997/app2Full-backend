package ar.com.draimo.jitws.controller;

import ar.com.draimo.jitws.constant.RutaConstant;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
    
    //Obtiene la fecha actual
    @GetMapping(value = URL)
    @ResponseBody
    public LocalDate fecha() {
        return LocalDate.now();
    }
    
    //Obtiene la fecha y hora actual
    @GetMapping(value = URL + "/obtenerFechaYHora")
    @ResponseBody
    public LocalDateTime fechaYHora() {
        return LocalDateTime.now();
    }
    
}
