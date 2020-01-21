package ar.com.draimo.jitws.dto;

import ar.com.draimo.jitws.model.Pestania;
import ar.com.draimo.jitws.model.TipoConceptoSueldo;
import ar.com.draimo.jitws.model.UnidadMedidaSueldo;
import java.util.List;

/**
 * Define Inicializador de ConceptoSueldo
 * @author marina
 */
public class InitConceptoSueldoDTO {
    
    //Define pestanias
    private List<Pestania> pestanias;
    
    //Define TipoConceptoSueldo
    private List<TipoConceptoSueldo> tiposConceptosSueldos;
    
    //Define UnidadMedidaSueldo
    private List<UnidadMedidaSueldo> unidadesMedidasSueldos;
    
    //Getters y setters de la clase

    public List<Pestania> getPestanias() {
        return pestanias;
    }

    public void setPestanias(List<Pestania> pestanias) {
        this.pestanias = pestanias;
    }

    public List<TipoConceptoSueldo> getTiposConceptosSueldos() {
        return tiposConceptosSueldos;
    }

    public void setTiposConceptosSueldos(List<TipoConceptoSueldo> tiposConceptosSueldos) {
        this.tiposConceptosSueldos = tiposConceptosSueldos;
    }

    public List<UnidadMedidaSueldo> getUnidadesMedidasSueldos() {
        return unidadesMedidasSueldos;
    }

    public void setUnidadesMedidasSueldos(List<UnidadMedidaSueldo> unidadesMedidasSueldos) {
        this.unidadesMedidasSueldos = unidadesMedidasSueldos;
    }
    
}