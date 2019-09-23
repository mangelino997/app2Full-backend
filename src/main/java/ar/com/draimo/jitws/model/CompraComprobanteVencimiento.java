//Paquete al que pertenece la clase
package ar.com.draimo.jitws.model;

import java.math.BigDecimal;
import java.sql.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Clase CompraComprobanteVencimiento 
 * Define el modelo (columnas) de la base de
 * datos.
 *
 * @author blas
 */
@Entity
@Table(name = "compracomprobantevencimiento")
public class CompraComprobanteVencimiento extends ObjetoGenerico {

    //Referencia a la clase compraComprobante
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idCompraComprobante", nullable = false)
    private CompraComprobante compraComprobante;

    //Define fecha
    @Column(name = "fecha", nullable = false)
    private Date fecha;

    //Define el importe
    @Column(name = "importe", nullable = false)
    private BigDecimal importe;

    //Getters y Setters de la clase
    public CompraComprobante getCompraComprobante() {
        return compraComprobante;
    }

    public void setCompraComprobante(CompraComprobante compraComprobante) {
        this.compraComprobante = compraComprobante;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public BigDecimal getImporte() {
        return importe;
    }

    public void setImporte(BigDecimal importe) {
        this.importe = importe;
    }

}
