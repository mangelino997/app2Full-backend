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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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

    @ManyToOne
    @JoinTable(
      name = "cobranzamediopago", 
      joinColumns = @JoinColumn(name = "idChequeCartera"), 
      inverseJoinColumns = @JoinColumn(name = "idCobranza"))
    @JsonIgnoreProperties(value = {"chequeCartera", "efectivo","cobranzaAnticipo"})
    private Cobranza cobranzaOrigen;
    
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

    public Cobranza getCobranzaOrigen() {
        return cobranzaOrigen;
    }

    public void setCobranzaOrigen(Cobranza cobranzaOrigen) {
        this.cobranzaOrigen = cobranzaOrigen;
    }
    
}
