package ar.com.draimo.jitws.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Clase seguimiento Venta Comprobante
 * Define el modelo (columnas) de la base de datos
 * @author blas
 */

@Entity
@Table(name = "seguimientoventacomprobante")
public class SeguimientoVentaComprobante extends ObjetoGenerico {
    
    //Referencia a la clase VentaComprobante
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idVentaComprobante", nullable = false)
    private VentaComprobante ventaComprobante;
    
    //Define fecha
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC-3")
    @Column(name = "fecha", nullable = false)  
    private LocalDateTime fecha;
    
    //Referencia a la clase Sucursal
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idSucursal", nullable = false)
    private Sucursal  sucursal;
    
    //Referencia a la clase SeguimientoEstado
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idSeguimientoEstado", nullable = false)
    private SeguimientoEstado seguimientoEstado;
    
    //Referencia a la clase SeguimientoSituacion
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idSeguimientoSituacion", nullable = true)
    private SeguimientoSituacion seguimientoSituacion;
    
    //Getters y setters de la clase
    public VentaComprobante getVentaComprobante() {
        return ventaComprobante;
    }

    public void setVentaComprobante(VentaComprobante ventaComprobante) {
        this.ventaComprobante = ventaComprobante;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    public SeguimientoEstado getSeguimientoEstado() {
        return seguimientoEstado;
    }

    public void setSeguimientoEstado(SeguimientoEstado seguimientoEstado) {
        this.seguimientoEstado = seguimientoEstado;
    }

    public SeguimientoSituacion getSeguimientoSituacion() {
        return seguimientoSituacion;
    }

    public void setSeguimientoSituacion(SeguimientoSituacion seguimientoSituacion) {
        this.seguimientoSituacion = seguimientoSituacion;
    }

}