package ar.com.draimo.jitws.dto;

import ar.com.draimo.jitws.model.Pestania;
import ar.com.draimo.jitws.model.Sucursal;
import java.util.List;

/**
 * Data Transfer Object of personal
 * @author blas
 */
public class InitViajeDTO {
    
    //Define el ultimo id
    private int ultimoId;
    
    //Lista de sucursales
    private List<Sucursal> sucursales; 
    
    //Define las pestanias
    private List<Pestania> pestanias;
    
    //Define Getters y Setters
    public int getUltimoId() {
        return ultimoId;
    }

    public void setUltimoId(int ultimoId) {
        this.ultimoId = ultimoId;
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