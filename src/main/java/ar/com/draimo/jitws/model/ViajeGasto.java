//Paquete al que pertenece la clase
package ar.com.draimo.jitws.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;
import java.sql.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Clase Viaje Propio Gasto
 * Define el modelo (columnas) de la base de datos.
 * @author blas
 */

@Entity
@Table(name = "viajegasto")
public class ViajeGasto extends ObjetoGenerico {

    //Referencia a la clase Viaje
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idViaje", nullable = true)
    private Viaje viaje;
    
    //Referencia a la clase Reparto Propio
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idReparto", nullable = true)
    private RepartoPropio reparto;
    
    //Referencia a la clase Sucursal
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idSucursal", nullable = false)
    private Sucursal sucursal;
    
    //Referencia a la clase Usuario
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idUsuarioAlta", nullable = false)
    private Usuario usuarioAlta;
    
    //Referencia a la clase TipoComprobante
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idTipoComprobante", nullable = false)
    private TipoComprobante tipoComprobante;
    
    //Referencia a la clase Rubro Producto
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idRubroProducto", nullable = false)
    private RubroProducto rubroProducto;
    
    //Define la fecha
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC-3")
    @Column(name = "fecha", nullable = false)
    private Date fecha;
    
    //Define la cantidad
    @Column(name = "cantidad", nullable = false)
    private short cantidad;
    
    //Define el precio unitario
    @Column(name = "precioUnitario", nullable = false)
    private BigDecimal precioUnitario;
    
    //Define el importe
    @Column(name = "importe", nullable = false)
    private BigDecimal importe;
    
    //Define las observaciones
    @Column(name = "observaciones",length = 60, nullable = true)
    private String observaciones;
    
    //Define si esta anulado
    @Column(name = "estaAnulado", nullable = true)
    private boolean estaAnulado;
    
    //Define las observaciones de anulado
    @Column(name = "observacionesAnulado",length = 60, nullable = true)
    private String observacionesAnulado;

    //Getters y Setters de la clase

    public Viaje getViaje() {
        return viaje;
    }

    public void setViaje(Viaje viaje) {
        this.viaje = viaje;
    }

    public RepartoPropio getReparto() {
        return reparto;
    }

    public void setReparto(RepartoPropio reparto) {
        this.reparto = reparto;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    public Usuario getUsuarioAlta() {
        return usuarioAlta;
    }

    public void setUsuarioAlta(Usuario usuarioAlta) {
        this.usuarioAlta = usuarioAlta;
    }

    public TipoComprobante getTipoComprobante() {
        return tipoComprobante;
    }

    public void setTipoComprobante(TipoComprobante tipoComprobante) {
        this.tipoComprobante = tipoComprobante;
    }

    public RubroProducto getRubroProducto() {
        return rubroProducto;
    }

    public void setRubroProducto(RubroProducto rubroProducto) {
        this.rubroProducto = rubroProducto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
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