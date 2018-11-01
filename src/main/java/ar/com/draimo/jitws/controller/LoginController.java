package ar.com.draimo.jitws.controller;

import java.security.Principal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Clase Login Controller
 * @author blas
 */

@RestController
public class LoginController {
    
    //Mapea el metodo con la url
    @RequestMapping(value = "/login")
    public Principal login(Principal principal) {
        return principal;
    }
    
}
