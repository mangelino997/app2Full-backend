//Paquete al que pertenece la clase
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
 * Clase Reparto Propio
 * Define el modelo (columnas) de la base de datos.
 * @author blas
 */

@Entity
@Table(name = "repartopropio")
public class RepartoPropio extends ObjetoGenerico {
    
    //Referencia a la claase empresa
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idEmpresa", nullable = false)
    private Empresa empresa;
    
    //Referencia a la clase sucursal
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idSucursal", nullable = false)
    private Sucursal sucursal;
    
    //Referencia a la clase tipoComprobante
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idTipoComprobante", nullable = false)
    private TipoComprobante tipoComprobante;
    
    //Define fechaRegistracion
    @JsonFormat(shape = JsonFormat.Shape.STRING ,pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "fechaRegistracion", nullable = false)  
    private LocalDateTime fechaRegistracion;
    
    //Define fechaSalida
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "ART")
    @Column(name = "fechaSalida", nullable = false)
    private Date fechaSalida;
    
    //Define horaSalida
    @JsonFormat(pattern = "HH:mm:ss", timezone = "ART")
    @Column(name = "horaSalida", nullable = false)
    private Time horaSalida;
    
    //Referencia a la clase vehiculo
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idVehiculo", nullable = false)
    private Vehiculo vehiculo;
    
    //Referencia a la clase vehiculo
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idVehiculoRemolque", nullable = true)
    private Vehiculo vehiculoRemolque;
    
    //Referencia a la clase Personal
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idPersonal", nullable = false)
    private Personal personal;
    
    //Referencia a la clase Zona
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idZona", nullable = false)
    private Zona zona;
    
    //Define observaciones
    @Column(name = "observaciones", length = 100, nullable = true)
    private String observaciones;
    
    //Referencia a la clase usuario
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idUsuarioAlta", nullable = false)
    private Usuario usuarioAlta;
    
    //Define si esta cerrada
    @Column(name = "estaCerrada", nullable = false)
    private boolean estaCerrada;
    
    //Getters y Setters de la clase

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

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public Vehiculo getVehiculoRemolque() {
        return vehiculoRemolque;
    }

    public void setVehiculoRemolque(Vehiculo vehiculoRemolque) {
        this.vehiculoRemolque = vehiculoRemolque;
    }

    public Personal getPersonal() {
        return personal;
    }

    public void setPersonal(Personal personal) {
        this.personal = personal;
    }

    public Zona getZona() {
        return zona;
    }

    public void setZona(Zona zona) {
        this.zona = zona;
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

}