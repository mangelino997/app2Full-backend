//Paquete al que pertenece la clase
package ar.com.draimo.jitws.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.sql.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Clase Compania Seguro Poliza 
 * Define el modelo (columnas) de la base de datos.
 *
 * @author blas
 */
@Entity
@Table(name = "companiaseguropoliza")
public class CompaniaSeguroPoliza extends ObjetoGenerico {

    //Referencia a la clase Compania Seguro
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idCompaniaSeguro", nullable = false)
    private CompaniaSeguro companiaSeguro;

    //Referencia a la clase Empresa
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idEmpresa", nullable = false)
    private Empresa empresa;

    //Define el numero de poliza
    @Column(name = "numeroPoliza", length = 15, nullable = false)
    private String numeroPoliza;

    //Define el vencimiento de la poliza
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC-3")
    @Column(name = "vtoPoliza", nullable = false)
    private Date vtoPoliza;

    //Referencia a la clase Pdf
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idPdf", nullable = true)
    private Pdf pdf;

    //Getters y Setters de la clase
    public CompaniaSeguro getCompaniaSeguro() {
        return companiaSeguro;
    }

    public void setCompaniaSeguro(CompaniaSeguro companiaSeguro) {
        this.companiaSeguro = companiaSeguro;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public String getNumeroPoliza() {
        return numeroPoliza;
    }

    public void setNumeroPoliza(String numeroPoliza) {
        this.numeroPoliza = numeroPoliza;
    }

    public Date getVtoPoliza() {
        return vtoPoliza;
    }

    public void setVtoPoliza(Date vtoPoliza) {
        this.vtoPoliza = vtoPoliza;
    }

    public Pdf getPdf() {
        return pdf;
    }

    public void setPdf(Pdf pdf) {
        this.pdf = pdf;
    }

}
