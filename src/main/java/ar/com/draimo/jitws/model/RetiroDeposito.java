package ar.com.draimo.jitws.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Clase retiro deposito
 * Mapea con la tabla en la base de datos
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
    
    //Define fecha de reggistracion
    @Column(name = "fechaRegistracion", nullable = false)
    private LocalDateTime fechaRegistracion;
    
    //Define fecha salida
    @Column(name = "fechaSalida", nullable = false)
    private LocalDate fechaSalida;
    
    //Define hora salida
    @Column(name = "horaSalida", nullable = false)
    private LocalTime horaSalida;
    
    //Define observaciones
    @Column(name = "observaciones",length = 100)
    private String observaciones;
    
    //Referencia a la clase Usuario
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idUsuarioAlta", nullable = false)
    private Usuario usuarioAlta;
    
    //Define estaCerrada
    @Column(name = "estaCerrada", nullable = false)
    private boolean estaCerrada;
    
    //Referencia a la clase TipoDocumento
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idTipoDocumento", nullable = false)
    private TipoDocumento tipoDocumento;
    
    //Define numero de documento
    @Column(name = "numeroDocumento", length = 15, nullable = false)
    private String numeroDocumento;

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

    public LocalDate getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(LocalDate fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public LocalTime getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(LocalTime horaSalida) {
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
    
}
