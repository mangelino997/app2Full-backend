//Paquete al que pertenece la clase
package ar.com.draimo.jitws.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.math.BigDecimal;
import java.sql.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Clase ChequeCartera Define el modelo (columnas) de la base de datos.
 *
 * @author blas
 */
@Entity
@Table(name = "chequeraitem")
public class ChequeraItem extends ObjetoGenerico {

    //Define referencia a la clase Chequera
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idChequera", nullable = false)
    private Chequera chequera;

    //Define numero
    @Column(name = "numeroCheque", length = 8, nullable = false)
    private int numeroCheque;

    //Define fecha de pago
    @Column(name = "fechaPago", nullable = false)
    private Date fechaPago;

    //Define el importe
    @Column(name = "importe", nullable = true)
    private BigDecimal importe;
    
    //Define referencia a la clase banco
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idLibroBanco", nullable = true)
    private LibroBanco libroBanco;

    //Define la referencia a la clase pago
    @ManyToOne
    @JoinTable(
      name = "pagomediopago", 
      joinColumns = @JoinColumn(name = "idChequeraItem"), 
      inverseJoinColumns = @JoinColumn(name = "idPago"))
    @JsonIgnoreProperties(value = {"efectivo","chequeCartera","cobranzaAnticipo",
        "libroBanco","monedaCartera", "documentoCartera"})
    private Pago pagoDestino;

    //Getters y Setters de la clase
    public Chequera getChequera() {
        return chequera;
    }

    public void setChequera(Chequera chequera) {
        this.chequera = chequera;
    }

    public int getNumeroCheque() {
        return numeroCheque;
    }

    public void setNumeroCheque(int numeroCheque) {
        this.numeroCheque = numeroCheque;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public BigDecimal getImporte() {
        return importe;
    }

    public void setImporte(BigDecimal importe) {
        this.importe = importe;
    }

    public LibroBanco getLibroBanco() {
        return libroBanco;
    }

    public void setLibroBanco(LibroBanco libroBanco) {
        this.libroBanco = libroBanco;
    }

    public Pago getPagoDestino() {
        return pagoDestino;
    }

    public void setPagoDestino(Pago pagoDestino) {
        this.pagoDestino = pagoDestino;
    }

}
