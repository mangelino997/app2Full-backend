/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.draimo.jitws.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.ManyToOne;


/**
 *Clase ConceptoSueldoLiqAsoc
 * Define el modelo(columnas) de la base de datos
 * @author blas
 */
@Entity
@Table(name = "conceptosueldoliqasoc")
public class ConceptoSueldoLiqAsoc extends ObjetoGenerico {
    
    @ManyToOne (cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idConceptoSueldo", nullable = false)
    private ConceptoSueldo conceptoSueldo;
    
    @ManyToOne (cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idTipoLiquidacionSueldo", nullable = false)
    private TipoLiquidacionSueldo tipoLiquidacionSueldo;

    public ConceptoSueldo getConceptoSueldo() {
        return conceptoSueldo;
    }

    public void setConceptoSueldo(ConceptoSueldo conceptoSueldo) {
        this.conceptoSueldo = conceptoSueldo;
    }

    public TipoLiquidacionSueldo getTipoLiquidacionSueldo() {
        return tipoLiquidacionSueldo;
    }

    public void setTipoLiquidacionSueldo(TipoLiquidacionSueldo tipoLiquidacionSueldo) {
        this.tipoLiquidacionSueldo = tipoLiquidacionSueldo;
    }
    
}