package ar.com.draimo.jitws.dto;

import ar.com.draimo.jitws.model.AfipCondicionIva;
import ar.com.draimo.jitws.model.Pestania;
import java.util.List;

/**
 * Data Transfer Object of personal
 * @author blas
 */
public class InitEmpresaDTO {
    
    //Define el ultimo id
    private int ultimoId;
    
    //Lista de afipCondicionIvas
    private List<AfipCondicionIva> afipCondicionIvas; 
    
    //Define las pestanias
    private List<Pestania> pestanias;

    //Define Getters y Setters

    public int getUltimoId() {
        return ultimoId;
    }

    public void setUltimoId(int ultimoId) {
        this.ultimoId = ultimoId;
    }

    public List<AfipCondicionIva> getAfipCondicionIvas() {
        return afipCondicionIvas;
    }

    public void setAfipCondicionIvas(List<AfipCondicionIva> afipCondicionIvas) {
        this.afipCondicionIvas = afipCondicionIvas;
    }

    public List<Pestania> getPestanias() {
        return pestanias;
    }

    public void setPestanias(List<Pestania> pestanias) {
        this.pestanias = pestanias;
    }
    
}