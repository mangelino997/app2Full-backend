package ar.com.draimo.jitws.dto;

import ar.com.draimo.jitws.model.Pestania;
import ar.com.draimo.jitws.model.Submodulo;
import java.util.List;

/**
 * Data Transfer Object of personal
 * @author blas
 */
public class InitSubopcionDTO {
    
    //Define el ultimo id
    private int ultimoId;
    
    //Lista de submodulos
    private List<Submodulo> submodulos; 
    
    //Define las pestanias
    private List<Pestania> pestanias;
    
    //Define Getters y Setters
    public int getUltimoId() {
        return ultimoId;
    }

    public void setUltimoId(int ultimoId) {
        this.ultimoId = ultimoId;
    }

    public List<Submodulo> getSubmodulos() {
        return submodulos;
    }

    public void setSubmodulos(List<Submodulo> submodulos) {
        this.submodulos = submodulos;
    }

    public List<Pestania> getPestanias() {
        return pestanias;
    }

    public void setPestanias(List<Pestania> pestanias) {
        this.pestanias = pestanias;
    }
    
}