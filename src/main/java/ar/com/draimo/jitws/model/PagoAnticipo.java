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
 * Clase PagoAnticipo
 * Define el modelo (columnas) de la base de datos.
 *
 * @author blas
 */
@Entity
@Table(name = "pagoanticipo")
public class PagoAnticipo extends ObjetoGenerico {

    //Define la referencia a la clase Pago
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idPago", nullable = false)
    private Pago pago;

    //Define el importe
    @Column(name = "importe", nullable = false)
    private BigDecimal importe;

    //Define el saldo
    @Column(name = "saldo", nullable = false)
    private BigDecimal saldo;

    public Pago getPago() {
        return pago;
    }

    //Getters y Setters de la clase
    public void setPago(Pago pago) {
        this.pago = pago;
    }

    public BigDecimal getImporte() {
        return importe;
    }

    public void setImporte(BigDecimal importe) {
        this.importe = importe;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }
    
}
