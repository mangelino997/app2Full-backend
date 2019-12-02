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
 * Clase MonedaCartera 
 * Define el modelo (columnas) de la base de datos.
 *
 * @author blas
 */
@Entity
@Table(name = "monedacartera")
public class MonedaCartera extends ObjetoGenerico {

    //Define referencia a la clase Empresa
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idEmpresa", nullable = false)
    private Empresa empresa;

    //Define referencia a la clase moneda
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idMoneda", nullable = false)
    private Moneda moneda;

    //Define cantidad
    @Column(name = "cantidad", nullable = false)
    private BigDecimal cantidad;

    //Define el importe
    @Column(name = "importe", nullable = true)
    private BigDecimal importe;

    //Define esIngreso
    @Column(name = "esIngreso", nullable = false)
    private boolean esIngreso;

    //Define la referencia a la clase cobranza
    @ManyToOne
    @JoinTable(
      name = "cobranzamediopago", 
      joinColumns = @JoinColumn(name = "idMonedaCartera"), 
      inverseJoinColumns = @JoinColumn(name = "idCobranza"))
    @JsonIgnoreProperties(value = {"efectivo","chequeCartera","cobranzaAnticipo",
        "libroBanco","monedaCartera", "documentoCartera"})
    private Cobranza cobranzaOrigen;

    //Define la referencia a la clase pago
    @ManyToOne
    @JoinTable(
      name = "pagomediopago", 
      joinColumns = @JoinColumn(name = "idMonedaCartera"), 
      inverseJoinColumns = @JoinColumn(name = "idPago"))
    @JsonIgnoreProperties(value = {"efectivo","chequeCartera","cobranzaAnticipo",
        "libroBanco","monedaCartera", "documentoCartera"})
    private Pago pagoDestino;
    
    //Getters y Setters de la clase

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Moneda getMoneda() {
        return moneda;
    }

    public void setMoneda(Moneda moneda) {
        this.moneda = moneda;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }
    
    public BigDecimal getImporte() {
        return importe;
    }

    public void setImporte(BigDecimal importe) {
        this.importe = importe;
    }

    public boolean isEsIngreso() {
        return esIngreso;
    }

    public void setEsIngreso(boolean esIngreso) {
        this.esIngreso = esIngreso;
    }

    public Cobranza getCobranzaOrigen() {
        return cobranzaOrigen;
    }

    public void setCobranzaOrigen(Cobranza cobranzaOrigen) {
        this.cobranzaOrigen = cobranzaOrigen;
    }

    public Pago getPagoDestino() {
        return pagoDestino;
    }

    public void setPagoDestino(Pago pagoDestino) {
        this.pagoDestino = pagoDestino;
    }
    
}
