package ar.com.draimo.jitws.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Clase retiro deposito
 * Mapea con la tabla en la base de datos.
 * 
 * @author blas
 */

@Entity
@Table(name = "retirodeposito")
public class RetiroDeposito extends ObjetoGenerico {
    
    //Referencia a la clase empresa
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idEmpresa", nullable = false)
    private Empresa empresa;
    
    //Referencia a la clase sucursal
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idSucursal", nullable = false)
    private Sucursal sucursal;
    
    //Referencia a la clase TipoComprobante
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idTipoComprobante", nullable = false)
    private TipoComprobante tipoComprobante;
    
    //Define fechaRegistracion
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC-3")
    @Column(name = "fechaRegistracion", nullable = false)  
    private LocalDateTime  fechaRegistracion;
    
    //Define fecha salida
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC-3")
    @Column(name = "fechaSalida", nullable = false)
    private Date fechaSalida;
    
    //Define hora salida
    @JsonFormat(pattern = "HH:mm:ss", timezone = "UTC-3")
    @Column(name = "horaSalida", nullable = false)
    private Time horaSalida;
    
    //Define observaciones
    @Column(name = "observaciones",length = 100)
    private String observaciones;
    
    //Referencia a la clase Usuario (alta)
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idUsuarioAlta", nullable = false)
    private Usuario usuarioAlta;
    
    //Define si estaCerrada
    @Column(name = "estaCerrada", nullable = false)
    private boolean estaCerrada;
    
    //Referencia a la clase TipoDocumento
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idTipoDocumento", nullable = false)
    private TipoDocumento tipoDocumento;
    
    //Define numero de documento
    @Column(name = "numeroDocumento", length = 15, nullable = false)
    private String numeroDocumento;

    //Referencia a la clase Pdf (dni)
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idPdfDni", nullable = true)
    private Pdf pdfDni;
    
    //Define getters y setters de la clase
    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    public TipoComprobante getTipoComprobante() {
        return tipoComprobante;
    }

    public void setTipoComprobante(TipoComprobante tipoComprobante) {
        this.tipoComprobante = tipoComprobante;
    }

    public LocalDateTime getFechaRegistracion() {
        return fechaRegistracion;
    }

    public void setFechaRegistracion(LocalDateTime fechaRegistracion) {
        this.fechaRegistracion = fechaRegistracion;
    }
    
    public Date getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public Time getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(Time horaSalida) {
        this.horaSalida = horaSalida;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Usuario getUsuarioAlta() {
        return usuarioAlta;
    }

    public void setUsuarioAlta(Usuario usuarioAlta) {
        this.usuarioAlta = usuarioAlta;
    }

    public boolean isEstaCerrada() {
        return estaCerrada;
    }

    public void setEstaCerrada(boolean estaCerrada) {
        this.estaCerrada = estaCerrada;
    }

    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public Pdf getPdfDni() {
        return pdfDni;
    }

    public void setPdfDni(Pdf pdfDni) {
        this.pdfDni = pdfDni;
    }
    
}