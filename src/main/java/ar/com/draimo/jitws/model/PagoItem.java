//Paquete al que pertenece la clase
package ar.com.draimo.jitws.model;

import java.math.BigDecimal;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Clase PagoItem
 * Define el modelo (columnas) de la base de datos.
 *
 * @author blas
 */
@Entity
@Table(name = "pagoitem")
public class PagoItem extends ObjetoGenerico {

    //Define la referencia a la clase Pago
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idPago", nullable = false)
    private Pago pago;

    //Define la referencia a la clase CompraComprobante
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idCompraComprobante", nullable = false)
    private CompraComprobante compraComprobante;

    //Define el importe
    @Column(name = "importe", nullable = false)
    private BigDecimal importe;

    public Pago getPago() {
        return pago;
    }

    public void setPago(Pago pago) {
        this.pago = pago;
    }

    public CompraComprobante getCompraComprobante() {
        return compraComprobante;
    }

    //Getters y Setters de la clase
    public void setCompraComprobante(CompraComprobante compraComprobante) {
        this.compraComprobante = compraComprobante;
    }

    public BigDecimal getImporte() {
        return importe;
    }

    public void setImporte(BigDecimal importe) {
        this.importe = importe;
    }

}
