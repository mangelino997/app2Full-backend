//Paquete al que pertenece la clase
package ar.com.draimo.jitws.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.math.BigDecimal;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Clase CobranzaAnticipo
 * Define el modelo (columnas) de la base de datos.
 *
 * @author blas
 */
@Entity
@Table(name = "cobranzaanticipo")
public class CobranzaAnticipo extends ObjetoGenerico {

    //Define la referencia a la clase cobranza
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idCobranza", nullable = false)
    private Cobranza cobranza;

    //Define el importe
    @Column(name = "importe", nullable = false)
    private BigDecimal importe;

    //Define el saldo
    @Column(name = "saldo", nullable = false)
    private BigDecimal saldo;

    @ManyToOne
    @JoinTable(
      name = "cobranzamediopago", 
      joinColumns = @JoinColumn(name = "idCobranzaAnticipo"), 
      inverseJoinColumns = @JoinColumn(name = "idCobranza"))
    @JsonIgnoreProperties(value = {"efectivo","chequeCartera","cobranzaAnticipo"})
    private Cobranza cobranzaOrigen;
    
    //Getters y Setters de la clase

    public Cobranza getCobranza() {
        return cobranza;
    }

    public void setCobranza(Cobranza cobranza) {
        this.cobranza = cobranza;
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

    public Cobranza getCobranzaOrigen() {
        return cobranzaOrigen;
    }

    public void setCobranzaOrigen(Cobranza cobranzaOrigen) {
        this.cobranzaOrigen = cobranzaOrigen;
    }
    
}
