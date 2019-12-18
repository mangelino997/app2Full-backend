package ar.com.draimo.jitws.dto;

import ar.com.draimo.jitws.model.AfipConceptoSueldoGrupo;
import ar.com.draimo.jitws.model.Pestania;
import java.util.List;

/**
 * Data Transfer Object of personal
 * @author blas
 */
public class InitAfipCptoSueldoDTO {
    
    //Define el ultimo id
    private int ultimoId;
    
    //Lista de sucursales
    private List<AfipConceptoSueldoGrupo> afipConceptoSueldoGrupos;  
    
    //Lista de pestanias
    private List<Pestania> pestanias;

    //Define Getters y Setters

    public int getUltimoId() {
        return ultimoId;
    }

    public void setUltimoId(int ultimoId) {
        this.ultimoId = ultimoId;
    }

    public List<AfipConceptoSueldoGrupo> getAfipConceptoSueldoGrupos() {
        return afipConceptoSueldoGrupos;
    }

    public void setAfipConceptoSueldoGrupos(List<AfipConceptoSueldoGrupo> afipConceptoSueldoGrupos) {
        this.afipConceptoSueldoGrupos = afipConceptoSueldoGrupos;
    }

    public List<Pestania> getPestanias() {
        return pestanias;
    }

    public void setPestanias(List<Pestania> pestanias) {
        this.pestanias = pestanias;
    }
    
}