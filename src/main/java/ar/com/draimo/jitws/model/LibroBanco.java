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
 * Clase libro banco Define el modelo (columnas) de la base de datos.
 *
 * @author blas
 */
@Entity
@Table(name = "librobanco")
public class LibroBanco extends ObjetoGenerico {

    //Define referencia a la clase TipoDocumentoCartera
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idCuentaBancaria", nullable = false)
    private CuentaBancaria cuentaBancaria;

    //Define fecha 
    @Column(name = "fecha", nullable = false)
    private Date fecha;

    //Define el importe
    @Column(name = "importe", nullable = false)
    private BigDecimal importe;

    //Define esIngreso
    @Column(name = "esDebito", nullable = false)
    private boolean esDebito;

    //Getters y Setters de la clase

    public CuentaBancaria getCuentaBancaria() {
        return cuentaBancaria;
    }

    public void setCuentaBancaria(CuentaBancaria cuentaBancaria) {
        this.cuentaBancaria = cuentaBancaria;
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

    public boolean isEsDebito() {
        return esDebito;
    }

    public void setEsDebito(boolean esDebito) {
        this.esDebito = esDebito;
    }

}
