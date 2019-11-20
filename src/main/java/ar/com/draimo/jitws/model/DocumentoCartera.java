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
 * Clase DocumentoCartera Define el modelo (columnas) de la base de datos.
 *
 * @author blas
 */
@Entity
@Table(name = "documentocartera")
public class DocumentoCartera extends ObjetoGenerico {

    //Define referencia a la clase Empresa
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idEmpresa", nullable = false)
    private Empresa empresa;

    //Define referencia a la clase TipoDocumentoCartera
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idTipoDocumentoCartera", nullable = false)
    private TipoDocumentoCartera tipoDocumentoCartera;

    //Define el nro de doc
    @Column(name = "numeroDocumento", length = 8, nullable = false)
    private int numeroDocumento;

    //Define fecha de pago
    @Column(name = "fechaPago", nullable = false)
    private Date fechaPago;

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
      joinColumns = @JoinColumn(name = "idDocumentoCartera"), 
      inverseJoinColumns = @JoinColumn(name = "idCobranza"))
    @JsonIgnoreProperties(value = {"efectivo","chequeCartera","cobranzaAnticipo",
        "libroBanco","monedaCartera", "documentoCartera"})
    private Cobranza cobranzaOrigen;
    
    //Getters y Setters de la clase

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public TipoDocumentoCartera getTipoDocumentoCartera() {
        return tipoDocumentoCartera;
    }

    public void setTipoDocumentoCartera(TipoDocumentoCartera tipoDocumentoCartera) {
        this.tipoDocumentoCartera = tipoDocumentoCartera;
    }

    public int getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(int numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
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

    public boolean isEsIngreso() {
        return esIngreso;
    }

    public void setEsIngreso(boolean esIngreso) {
        this.esIngreso = esIngreso;
    }

}
