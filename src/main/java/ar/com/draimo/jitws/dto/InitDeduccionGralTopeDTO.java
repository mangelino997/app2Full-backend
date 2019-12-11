package ar.com.draimo.jitws.dto;

import ar.com.draimo.jitws.model.AfipDeduccionGeneral;
import ar.com.draimo.jitws.model.Pestania;
import java.util.List;

/**
 * Data Transfer Object of personal
 * @author blas
 */
public class InitDeduccionGralTopeDTO {
    
    //Define el ultimo id
    private int ultimoId;
    
    //Lista de categorias
    private List<AfipDeduccionGeneral> afipDeduccionGenerales; 
    
    //Lista de anios
    private List<Short> anios; 
    
    //Define las pestanias
    private List<Pestania> pestanias;
    
    //Define Getters y Setters
    public int getUltimoId() {
        return ultimoId;
    }

    public void setUltimoId(int ultimoId) {
        this.ultimoId = ultimoId;
    }

    public List<AfipDeduccionGeneral> getAfipDeduccionGenerales() {
        return afipDeduccionGenerales;
    }

    public void setAfipDeduccionGenerales(List<AfipDeduccionGeneral> afipDeduccionGenerales) {
        this.afipDeduccionGenerales = afipDeduccionGenerales;
    }
    
    public List<Short> getAnios() {
        return anios;
    }

    public void setAnios(List<Short> anios) {
        this.anios = anios;
    }
    
    public List<Pestania> getPestanias() {
        return pestanias;
    }

    public void setPestanias(List<Pestania> pestanias) {
        this.pestanias = pestanias;
    }
    
}