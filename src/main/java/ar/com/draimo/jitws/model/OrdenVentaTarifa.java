//Paquete al que pertenece la clase
package ar.com.draimo.jitws.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Clase OrdenVentaTarifa Define el modelo (columnas) de la base de datos.
 *
 * @author blas
 */
@Entity
@Table(name = "ordenventatarifa")
public class OrdenVentaTarifa extends ObjetoGenerico {

    //Referencia a la clase OrdenVenta
    @JsonIgnoreProperties("tipoTarifas")
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "idOrdenVenta", nullable = false)
    private OrdenVenta ordenVenta;

    //Referencia a la clase TipoTarifa
    @JsonIgnoreProperties("ordenesVentas")
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "idTipoTarifa", nullable = false)
    private TipoTarifa tipoTarifa;

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

}
