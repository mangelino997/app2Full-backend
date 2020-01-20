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
@Table(name = "chequecartera")
public class ChequeCartera extends ObjetoGenerico {

    //Define referencia a la clase Empresa
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idEmpresa", nullable = false)
    private Empresa empresa;

    //Define referencia a la clase banco
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idBanco", nullable = false)
    private Banco banco;

    //Define numero
    @Column(name = "numero", length = 15, nullable = false)
    private String numero;

    //Define fecha de pago
    @Column(name = "fechaPago", nullable = false)
    private Date fechaPago;

    //Define el importe
    @Column(name = "importe", nullable = true)
    private BigDecimal importe;

    //Define referencia a la clase TipoDocumentoEmisor
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idTipoDocumentoEmisor", nullable = false)
    private TipoDocumento tipoDocumentoEmisor;

    //Define el nro de doc de Emisor
    @Column(name = "numeroDocumentoEmisor", length = 15, nullable = false)
    private String numeroDocumentoEmisor;

    //Define si el cheque es electronico
    @Column(name = "eCheq", nullable = false)
    private boolean eCheq;

    //Define la referencia a la clase cobranza
//    @ManyToOne
//    @JoinTable(
//      name = "cobranzamediopago", 
//      joinColumns = @JoinColumn(name = "idChequeCartera"), 
//      inverseJoinColumns = @JoinColumn(name = "idCobranza"))
//    @JsonIgnoreProperties(value = {"efectivo","chequeCartera","cobranzaAnticipo",
//        "libroBanco","monedaCartera", "documentoCartera"})
//    private Cobranza cobranzaOrigen;
    
    //Define la referencia a la clase pago
//    @ManyToOne
//    @JoinTable(
//      name = "pagomediopago", 
//      joinColumns = @JoinColumn(name = "idChequeCartera"), 
//      inverseJoinColumns = @JoinColumn(name = "idPago"))
//    @JsonIgnoreProperties(value = {"efectivo","chequeCartera","cobranzaAnticipo",
//        "libroBanco","monedaCartera", "documentoCartera"})
//    private Pago pagoDestino;
    
    //Getters y Setters de la clase

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Banco getBanco() {
        return banco;
    }

    public void setBanco(Banco banco) {
        this.banco = banco;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
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

    public TipoDocumento getTipoDocumentoEmisor() {
        return tipoDocumentoEmisor;
    }

    public void setTipoDocumentoEmisor(TipoDocumento tipoDocumentoEmisor) {
        this.tipoDocumentoEmisor = tipoDocumentoEmisor;
    }

    public String getNumeroDocumentoEmisor() {
        return numeroDocumentoEmisor;
    }

    public void setNumeroDocumentoEmisor(String numeroDocumentoEmisor) {
        this.numeroDocumentoEmisor = numeroDocumentoEmisor;
    }

    public boolean geteCheq() {
        return eCheq;
    }

    public void seteCheq(boolean eCheq) {
        this.eCheq = eCheq;
    }

//    public Cobranza getCobranzaOrigen() {
//        return cobranzaOrigen;
//    }
//
//    public void setCobranzaOrigen(Cobranza cobranzaOrigen) {
//        this.cobranzaOrigen = cobranzaOrigen;
//    }
//
//    public Pago getPagoDestino() {
//        return pagoDestino;
//    }
//
//    public void setPagoDestino(Pago pagoDestino) {
//        this.pagoDestino = pagoDestino;
//    }
    
}
