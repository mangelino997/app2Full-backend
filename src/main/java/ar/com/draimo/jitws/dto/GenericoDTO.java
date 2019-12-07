package ar.com.draimo.jitws.dto;

import ar.com.draimo.jitws.model.Pestania;
import java.util.List;

/**
 * Define ZonaDTO
 * @author blas
 */
public class GenericoDTO {
    
    //Define el ultimo id
    private int ultimoId;
    
    //Define las pestanias
    private List<Pestania> pestanias;
    
    //Define Getters y Setters

    public int getUltimoId() {
        return ultimoId;
    }

    public void setUltimoId(int ultimoId) {
        this.ultimoId = ultimoId;
    }

    public List<Pestania> getPestanias() {
        return pestanias;
    }

    public void setPestanias(List<Pestania> pestanias) {
        this.pestanias = pestanias;
    }
    
}