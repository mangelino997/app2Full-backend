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
 * Clase CompraCptePercepcionJurisd 
 * Define el modelo (columnas) de la base de
 * datos.
 *
 * @author blas
 */
@Entity
@Table(name = "compracptepercepcionjurisd")
public class CompraCptePercepcionJurisd extends ObjetoGenerico {

    //Referencia a la clase compraComprobantePercepcion
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idCompraComprobantePercepcion", nullable = false)
    private CompraComprobantePercepcion compraComprobantePercepcion;

    //Referencia a la clase Provincia
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idProvincia", nullable = false)
    private Provincia provincia;

    //Define el importe
    @Column(name = "importe", nullable = false)
    private BigDecimal importe;

    //Getters y Setters de la clase
    public CompraComprobantePercepcion getCompraComprobantePercepcion() {
        return compraComprobantePercepcion;
    }

    public void setCompraComprobantePercepcion(CompraComprobantePercepcion compraComprobantePercepcion) {
        this.compraComprobantePercepcion = compraComprobantePercepcion;
    }

    public Provincia getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }

    public BigDecimal getImporte() {
        return importe;
    }

    public void setImporte(BigDecimal importe) {
        this.importe = importe;
    }

}
