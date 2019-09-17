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
 * Clase Viaje Remito Seguimiento
 * Define el modelo (columnas) de la base de datos
 * @author blas
 */

@Entity
@Table(name = "viajeremitoseguimiento")
public class ViajeRemitoSeguimiento extends ObjetoGenerico {
    
    //Referencia a ViajeRemito
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idViajeRemito", nullable = false)
    private ViajeRemito viajeRemito;
    
    //Define fecha
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC-3")
    @Column(name = "fecha", nullable = false)  
    private LocalDateTime fecha;
    
    //Referencia a Sucursal
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idSucursal", nullable = false)
    private Sucursal  sucursal;
    
    //Referencia a SeguimientoEstado
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idSeguimientoEstado", nullable = false)
    private SeguimientoEstado seguimientoEstado;
    
    //Referencia a SeguimientoSituacion
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idSeguimientoSituacion", nullable = true)
    private SeguimientoSituacion seguimientoSituacion;
    
    //Define los getters y setters

    public ViajeRemito getViajeRemito() {
        return viajeRemito;
    }

    public void setViajeRemito(ViajeRemito viajeRemito) {
        this.viajeRemito = viajeRemito;
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
