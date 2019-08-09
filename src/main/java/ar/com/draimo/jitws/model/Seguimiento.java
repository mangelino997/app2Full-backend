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
 * Clase Seguimiento
 * Define el modelo (columnas) de la base de datos
 * @author blas
 */

@Entity
@Table(name = "seguimiento")
public class Seguimiento extends ObjetoGenerico {
    
    //Define la fecha
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC-3")
    @Column(name = "fecha", nullable = false)
    private LocalDateTime fecha;
    
    //Referencia a la clase Sucursal
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idSucursal", nullable = false)
    private Sucursal sucursal;
    
    //Refrencia a la clase Seguimiento Estado
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idSeguimientoEstado", nullable = false)
    private SeguimientoEstado seguimientoEstado;
    
    //Refrencia a la clase Seguimiento Situacion
//    @ManyToOne(cascade = CascadeType.REFRESH)
//    @JoinColumn(name = "idSeguimientoSituacion", nullable = false)
//    private SeguimientoSituacion seguimientoSituacion;
    
    //Defiene los getters y setters

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

//    public SeguimientoSituacion getSeguimientoSituacion() {
//        return seguimientoSituacion;
//    }
//
//    public void setSeguimientoSituacion(SeguimientoSituacion seguimientoSituacion) {
//        this.seguimientoSituacion = seguimientoSituacion;
//    }
    
}
