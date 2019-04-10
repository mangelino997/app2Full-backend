//Paquete al que pertenece la clase
package ar.com.draimo.jitws.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;
import java.sql.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Clase Viaje Tercero Efectivo
 * Define el modelo (columnas) de la base de datos.
 * @author blas
 */

@Entity
@Table(name = "viajeterceroefectivo")
public class ViajeTerceroEfectivo extends ObjetoGenerico {
    
    //Referencia a la clase Viaje Tercero
    @JsonBackReference
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idViajeTercero", nullable = true)
    private ViajeTercero viajeTercero;
    
    //Referencia a la clase Reparto Tercero
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idRepartoTercero", nullable = true)
    private RepartoTercero repartoTercero;
    
    //Referencia a la clase Empresa
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idEmpresa", nullable = false)
    private Empresa empresa;
    
    //Referencia a la clase Sucursal
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idSucursal", nullable = false)
    private Sucursal sucursal;
    
    //Referencia a la clase Usuario
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idUsuario", nullable = false)
    private Usuario usuario;
    
    //Referencia a la clase Tipo Comprobante
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idTipoComprobante", nullable = false)
    private TipoComprobante tipoComprobante;

    //Define la fecha
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC-3")
    @Column(name = "fecha", nullable = false)
    private Date fecha;
    
    //Define la fecha caja
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC-3")
    @Column(name = "fechaCaja", nullable = false)
    private Date fechaCaja;
    
    //Define el importe
    @Column(name = "importe", nullable = false)
    private BigDecimal importe;
    
    //Define las observaciones
    @Column(name = "observaciones",length = 60, nullable = true)
    private String observaciones;
    
    //Define si esta anulado
    @Column(name = "estaAnulado", nullable = false)
    private boolean estaAnulado;
    
    //Define las observaciones de anulacion
    @Column(name = "observacionesAnulado",length = 60, nullable = true)
    private String observacionesAnulado;

    //Getters y Setters de la clase

    public ViajeTercero getViajeTercero() {
        return viajeTercero;
    }

    public void setViajeTercero(ViajeTercero viajeTercero) {
        this.viajeTercero = viajeTercero;
    }

    public RepartoTercero getRepartoTercero() {
        return repartoTercero;
    }

    public void setRepartoTercero(RepartoTercero repartoTercero) {
        this.repartoTercero = repartoTercero;
    }

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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public TipoComprobante getTipoComprobante() {
        return tipoComprobante;
    }

    public void setTipoComprobante(TipoComprobante tipoComprobante) {
        this.tipoComprobante = tipoComprobante;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getFechaCaja() {
        return fechaCaja;
    }

    public void setFechaCaja(Date fechaCaja) {
        this.fechaCaja = fechaCaja;
    }

    public BigDecimal getImporte() {
        return importe;
    }

    public void setImporte(BigDecimal importe) {
        this.importe = importe;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public boolean getEstaAnulado() {
        return estaAnulado;
    }

    public void setEstaAnulado(boolean estaAnulado) {
        this.estaAnulado = estaAnulado;
    }

    public String getObservacionesAnulado() {
        return observacionesAnulado;
    }

    public void setObservacionesAnulado(String observacionesAnulado) {
        this.observacionesAnulado = observacionesAnulado;
    }
    
}