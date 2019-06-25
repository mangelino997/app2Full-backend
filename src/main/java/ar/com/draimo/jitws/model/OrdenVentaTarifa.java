//Paquete al que pertenece la clase
package ar.com.draimo.jitws.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.sql.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Clase OrdenVentaTarifa
 * Define el modelo (columnas) de la base de datos.
 * @author blas
 */

@Entity
@Table(name = "ordenventatarifa")
public class OrdenVentaTarifa extends ObjetoGenerico {

    //Referencia a la clase OrdenVenta
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idOrdenVenta", nullable = false)
    private OrdenVenta ordenVenta;
    
    //Referencia a la clase TipoTarifa
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idTipoTarifa", nullable = false)
    private TipoTarifa tipoTarifa;
    
    //Define la fecha desde que estan los precios
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC-3")
    @Column(name = "preciosDesde", nullable = false)
    private Date preciosDesde;
    
    //Getters y Setters de la clase

    public OrdenVenta getOrdenVenta() {
        return ordenVenta;
    }

    public void setOrdenVenta(OrdenVenta ordenVenta) {
        this.ordenVenta = ordenVenta;
    }

    public TipoTarifa getTipoTarifa() {
        return tipoTarifa;
    }

    public void setTipoTarifa(TipoTarifa tipoTarifa) {
        this.tipoTarifa = tipoTarifa;
    }

    public Date getPreciosDesde() {
        return preciosDesde;
    }

    public void setPreciosDesde(Date preciosDesde) {
        this.preciosDesde = preciosDesde;
    }
    
}