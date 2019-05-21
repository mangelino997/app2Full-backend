package ar.com.draimo.jitws.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Clase tipoFamiliar
 * Mapea con la tabla en la base de datos
 * @author blas
 */

@Entity
@Table(name = "tipofamiliar")
public class TipoFamiliar extends ObjetoGenerico {
    
    //Define el nombre
    @Column(name = "nombre",length = 45, nullable = false, unique = true)
    private String nombre;
    
    //Define es deducible impuesto/ganancia
    @Column(name = "esDeducibleImpGan",nullable = false)
    private boolean esDeducibleImpGan;
    
    //Getters y Setters de la clase

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isEsDeducibleImpGan() {
        return esDeducibleImpGan;
    }

    public void setEsDeducibleImpGan(boolean esDeducibleImpGan) {
        this.esDeducibleImpGan = esDeducibleImpGan;
    }
    
}
