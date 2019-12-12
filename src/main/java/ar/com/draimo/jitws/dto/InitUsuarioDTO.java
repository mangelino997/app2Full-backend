package ar.com.draimo.jitws.dto;

import ar.com.draimo.jitws.model.Pestania;
import ar.com.draimo.jitws.model.Rol;
import ar.com.draimo.jitws.model.Sucursal;
import java.util.List;

/**
 * Data Transfer Object of personal
 * @author blas
 */
public class InitUsuarioDTO {
    
    //Define el ultimo id
    private int ultimoId;
    
    //Lista de roles
    private List<Rol> roles; 
    
    //Lista de sucursales
    private List<Sucursal> sucursales;  
    
    //Lista de rubros
    private List<Pestania> pestanias;

    //Define Getters y Setters

    public int getUltimoId() {
        return ultimoId;
    }

    public void setUltimoId(int ultimoId) {
        this.ultimoId = ultimoId;
    }

    public List<Rol> getRoles() {
        return roles;
    }

    public void setRoles(List<Rol> roles) {
        this.roles = roles;
    }

    public List<Sucursal> getSucursales() {
        return sucursales;
    }

    public void setSucursales(List<Sucursal> sucursales) {
        this.sucursales = sucursales;
    }
    
    public List<Pestania> getPestanias() {
        return pestanias;
    }

    public void setPestanias(List<Pestania> pestanias) {
        this.pestanias = pestanias;
    }
    
}