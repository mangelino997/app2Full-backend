//Paquete al que pertenece la clase
package ar.com.wecode.jitws.model;

import java.math.BigDecimal;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Clase Viaje Precio
 * Define el modelo (columnas) de la base de datos.
 * @author blas
 */

@Entity
@Table(name = "viajeprecio")
public class ViajePrecio extends ObjetoGenerico {

    //Referencia a la clase Viaje Tipo
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idViajeTipo", nullable = false)
    private ViajeTipo viajeTipo;
    
    //Referencia a la clase Viaje Tarifa
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idViajeTarifa", nullable = false)
    private ViajeTarifa viajeTarifa;
    
    //Define el costo propio
    @Column(name = "costoPropio", nullable = true)
    private BigDecimal costoPropio;
    
    //Define el costo tercero
    @Column(name = "costoTercero", nullable = true)
    private BigDecimal costoTercero;

    //Getters y Setters de la clase

    public ViajeTipo getViajeTipo() {
        return viajeTipo;
    }

    public void setViajeTipo(ViajeTipo viajeTipo) {
        this.viajeTipo = viajeTipo;
    }

    public ViajeTarifa getViajeTarifa() {
        return viajeTarifa;
    }

    public void setViajeTarifa(ViajeTarifa viajeTarifa) {
        this.viajeTarifa = viajeTarifa;
    }

    public BigDecimal getCostoPropio() {
        return costoPropio;
    }

    public void setCostoPropio(BigDecimal costoPropio) {
        this.costoPropio = costoPropio;
    }

    public BigDecimal getCostoTercero() {
        return costoTercero;
    }

    public void setCostoTercero(BigDecimal costoTercero) {
        this.costoTercero = costoTercero;
    }
    
}