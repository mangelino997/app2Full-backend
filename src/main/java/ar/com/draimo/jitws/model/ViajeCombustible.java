//Paquete al que pertenece la clase
package ar.com.draimo.jitws.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
 * Clase Viaje Combustible
 * Define el modelo (columnas) de la base de datos.
 * @author blas
 */

@Entity
@Table(name = "viajecombustible")
public class ViajeCombustible extends ObjetoGenerico {
    
    //Referencia a la clase Viaje Propio
    @JsonIgnoreProperties(value ={"viajeTramos","viajeCombustibles",
        "viajeEfectivos","viajeInsumos","viajeGastos","viajePeajes"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idViaje", nullable = true)
    private Viaje viaje;
    
    //Referencia a la clase Reparto
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idReparto", nullable = true)
    private Reparto reparto;
    
    //Referencia a la clase Proveedor
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idProveedor", nullable = false)
    private Proveedor proveedor;
    
    //Referencia a la clase Sucursal
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idSucursal", nullable = false)
    private Sucursal sucursal;
    
    //Referencia a la clase Usuario
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idUsuarioAlta", nullable = false)
    private Usuario usuarioAlta;
    
    //Referencia a la clase Tipo Comprobante
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idTipoComprobante", nullable = false)
    private TipoComprobante tipoComprobante;

    //Define la fecha
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC-3")
    @Column(name = "fecha", nullable = false)
    private Date fecha;
    
    //Referencia a la clase InsumoProducto
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idInsumoProducto", nullable = false)
    private InsumoProducto insumoProducto;
    
    //Define la cantidad
    @Column(name = "cantidad", nullable = false)
    private BigDecimal cantidad;
    
    //Define el precio unitario
    @Column(name = "precioUnitario", nullable = false)
    private BigDecimal precioUnitario;
    
    //Define las observaciones
    @Column(name = "observaciones",length = 60, nullable = true)
    private String observaciones;
    
    //Define si esta anulado
    @Column(name = "estaAnulado", nullable = true)
    private boolean estaAnulado;
    
    //Define las observaciones de anulacion
    @Column(name = "observacionesAnulado",length = 60, nullable = true)
    private String observacionesAnulado;

    //Getters y Setters de la clase

    public Viaje getViaje() {
        return viaje;
    }

    public void setViaje(Viaje viaje) {
        this.viaje = viaje;
    }

    public Reparto getReparto() {
        return reparto;
    }

    public void setReparto(Reparto reparto) {
        this.reparto = reparto;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public InsumoProducto getInsumoProducto() {
        return insumoProducto;
    }

    public void setInsumoProducto(InsumoProducto insumoProducto) {
        this.insumoProducto = insumoProducto;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
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