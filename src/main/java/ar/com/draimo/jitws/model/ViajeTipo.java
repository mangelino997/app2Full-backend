//Paquete al que pertenece la clase
package ar.com.draimo.jitws.model;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Clase Viaje Tipo
 * Define el modelo (columnas) de la base de datos.
 * @author blas
 */

@Entity
@Table(name = "viajetipo")
public class ViajeTipo extends ObjetoGenerico {

    //Define el nombre
    @Column(name = "nombre",length = 45, nullable = false)
    private String nombre;
    
    //Define la abreviatura
    @Column(name = "abreviatura",length = 10, nullable = false)
    private String abreviatura;
    
    //Define el costoPorKmPropio
    @Column(name = "costoPorKmPropio", nullable = true)
    private BigDecimal costoPorKmPropio;
    
    //Define el costoPorKmTercero
    @Column(name = "costoPorKmTercero", nullable = true)
    private BigDecimal costoPorKmTercero;

    //Getters y Setters de la clase
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAbreviatura() {
        return abreviatura;
    }

    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }

    public BigDecimal getCostoPorKmPropio() {
        return costoPorKmPropio;
    }

    public void setCostoPorKmPropio(BigDecimal costoPorKmPropio) {
        this.costoPorKmPropio = costoPorKmPropio;
    }

    public BigDecimal getCostoPorKmTercero() {
        return costoPorKmTercero;
    }

    public void setCostoPorKmTercero(BigDecimal costoPorKmTercero) {
        this.costoPorKmTercero = costoPorKmTercero;
    }
    
}