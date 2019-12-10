package ar.com.draimo.jitws.dto;

import ar.com.draimo.jitws.model.EscalaTarifa;
import java.util.List;

/**
 * Data Transfer Object of personal
 * @author blas
 */
public class InitEscalaTarifaDTO {
    
    //Define el ultimo id
    private int ultimoId;
    
    //Lista de escalaTarifas
    private List<EscalaTarifa> escalaTarifas; 

    //Define Getters y Setters

    public int getUltimoId() {
        return ultimoId;
    }

    public void setUltimoId(int ultimoId) {
        this.ultimoId = ultimoId;
    }

    public List<EscalaTarifa> getEscalaTarifas() {
        return escalaTarifas;
    }

    public void setEscalaTarifas(List<EscalaTarifa> escalaTarifas) {
        this.escalaTarifas = escalaTarifas;
    }
    
}