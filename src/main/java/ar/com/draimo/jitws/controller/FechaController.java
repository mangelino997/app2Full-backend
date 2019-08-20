package ar.com.draimo.jitws.controller;

import ar.com.draimo.jitws.constant.Funcion;
import ar.com.draimo.jitws.constant.RutaConstant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
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
    
    //Obtiene la hora actual
    @GetMapping(value = URL + "/obtenerHora")
    @ResponseBody
    public LocalTime hora() {
        return LocalTime.now();
    }
    
    //Obtiene la fecha y hora actual
    @GetMapping(value = URL + "/obtenerFechaYHora")
    @ResponseBody
    public LocalDateTime fechaYHora() {
        return LocalDateTime.now();
    }
    
    //Obtiene un listado de anios desde el actual hasta 15 años mas
    @GetMapping(value = URL + "/listarAnios")
    @ResponseBody
    public List<Short> listarAnios() {
        List<Short> anios = new ArrayList<>();
        short anio = Funcion.anioInicio;
        for (short i = anio; i < anio+15; i++) {
            anios.add((short)i);
        }
        return anios;
    }
    
    //Obtiene un listado de anios de el actual, el anterior y el posterior
    @GetMapping(value = URL + "/listarAniosMasMenosUno")
    @ResponseBody
    public List<Short> listarAniosMasMenosUno() {
        List<Short> anios = new ArrayList<>();
        int iAnio = LocalDate.now().getYear();
        short anio = Short.parseShort(String.valueOf(iAnio));
        short anioAnterior = Short.parseShort(String.valueOf(iAnio-1));
        short anioPosterior =Short.parseShort(String.valueOf(iAnio +1));
        anios.add(anioAnterior);
        anios.add(anio);
        anios.add(anioPosterior);
        return anios;
    }
    
    //Obtiene un listado de anios de el actual, el anterior y el posterior
    @GetMapping(value = URL + "/listarAnioFiscal")
    @ResponseBody
    public List<Short> listarAnioFiscal() {
        List<Short> anios = new ArrayList<>();
        int iAnio = LocalDate.now().getYear();
        short anio = Short.parseShort(String.valueOf(iAnio));
        short anioPosterior = Short.parseShort(String.valueOf(iAnio +1));
        anios.add(anio);
        anios.add(anioPosterior);
        return anios;
    }
    
}
