//Paquete al que pertenece la clase
package ar.com.wecode.jitws.model;

import java.math.BigDecimal;
import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Clase Viaje Tercero Tramo
 * Define el modelo (columnas) de la base de datos.
 * @author blas
 */

@Entity
@Table(name = "viajetercerotramo")
public class ViajeTerceroTramo extends ObjetoGenerico {

    //Referencia a la clase Viaje Tercero
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idViajeTercero", nullable = false)
    private ViajeTercero viajeTercero;
    
    //Referencia a la clase Tramo
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idTramo", nullable = false)
    private Tramo tramo;
    
    //Define el numero de orden
    @Column(name = "numeroOrden", nullable = false)
    private short numeroOrden;
    
    //Define una fecha tramo
    @Column(name = "fechaTramo", nullable = false)
    private Date fechaTramo;
    
    //Define una fecha alta
    @Column(name = "fechaAlta", nullable = false)
    private Date fechaAlta;
    
    //Referencia a la clase Empresa
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idEmpresa", nullable = false)
    private Empresa empresa;
    
    //Define un km
    @Column(name = "km", nullable = false)
    private short km;
    
    //Referencia a la clase Usuario
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idUsuario", nullable = false)
    private Usuario usuario;
    
    //Define observaciones
    @Column(name = "observaciones", nullable = true)
    private String observaciones;
    
    //Referencia a la clase Viaje Tipo
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idViajeTipo", nullable = false)
    private ViajeTipo viajeTipo;
    
    //Referencia a la clase Viaje Tipo Carga
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idViajeTipoCarga", nullable = false)
    private ViajeTipoCarga viajeTipoCarga;
    
    //Referencia a la clase Viaje Tarifa
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idViajeTarifa", nullable = false)
    private ViajeTarifa viajeTarifa;
    
    //Referencia a la clase Viaje Unidad Negocio
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idViajeUnidadNegocio", nullable = false)
    private ViajeUnidadNegocio viajeUnidadNegocio;
    
    //Define la cantidad
    @Column(name = "cantidad", nullable = false)
    private short cantidad;
    
    //Define el precio unitario
    @Column(name = "precioUnitario", nullable = false)
    private BigDecimal precioUnitario;
    
    //Define el importe
    @Column(name = "importe", nullable = false)
    private BigDecimal importe;
    
    //Getters y Setters de la clase

    public ViajeTercero getViajeTercero() {
        return viajeTercero;
    }

    public void setViajeTercero(ViajeTercero viajeTercero) {
        this.viajeTercero = viajeTercero;
    }

    public Tramo getTramo() {
        return tramo;
    }

    public void setTramo(Tramo tramo) {
        this.tramo = tramo;
    }

    public short getNumeroOrden() {
        return numeroOrden;
    }

    public void setNumeroOrden(short numeroOrden) {
        this.numeroOrden = numeroOrden;
    }
    
    public Date getFechaTramo() {
        return fechaTramo;
    }

    public void setFechaTramo(Date fechaTramo) {
        this.fechaTramo = fechaTramo;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public short getKm() {
        return km;
    }

    public void setKm(short km) {
        this.km = km;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public ViajeTipo getViajeTipo() {
        return viajeTipo;
    }

    public void setViajeTipo(ViajeTipo viajeTipo) {
        this.viajeTipo = viajeTipo;
    }

    public ViajeTipoCarga getViajeTipoCarga() {
        return viajeTipoCarga;
    }

    public void setViajeTipoCarga(ViajeTipoCarga viajeTipoCarga) {
        this.viajeTipoCarga = viajeTipoCarga;
    }

    public ViajeTarifa getViajeTarifa() {
        return viajeTarifa;
    }

    public void setViajeTarifa(ViajeTarifa viajeTarifa) {
        this.viajeTarifa = viajeTarifa;
    }

    public ViajeUnidadNegocio getViajeUnidadNegocio() {
        return viajeUnidadNegocio;
    }

    public void setViajeUnidadNegocio(ViajeUnidadNegocio viajeUnidadNegocio) {
        this.viajeUnidadNegocio = viajeUnidadNegocio;
    }

    public short getCantidad() {
        return cantidad;
    }

    public void setCantidad(short cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public BigDecimal getImporte() {
        return importe;
    }

    public void setImporte(BigDecimal importe) {
        this.importe = importe;
    }
    
}