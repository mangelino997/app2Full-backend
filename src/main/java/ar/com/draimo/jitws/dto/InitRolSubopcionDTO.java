package ar.com.draimo.jitws.dto;

import ar.com.draimo.jitws.model.Modulo;
import ar.com.draimo.jitws.model.Rol;
import java.util.List;

/**
 * Data Transfer Object of personal
 * @author blas
 */
public class InitRolSubopcionDTO {
    
    //Lista de roles
    private List<Rol> roles; 
    
    //Define las pestanias
    private List<Modulo> modulos;
    
    //Define Getters y Setters
    public List<Rol> getRoles() {
        return roles;
    }

    public void setRoles(List<Rol> roles) {
        this.roles = roles;
    }

    public List<Modulo> getModulos() {
        return modulos;
    }

    public void setModulos(List<Modulo> modulos) {
        this.modulos = modulos;
    }
    
}