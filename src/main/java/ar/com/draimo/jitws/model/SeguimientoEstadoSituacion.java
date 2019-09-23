package ar.com.draimo.jitws.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Clase Seguimiento Estado Situacion
 * Define el modelo (columnas) de la base de datos.
 * @author blas
 */

@Entity
@Table(name = "seguimientoestadosituacion")
public class SeguimientoEstadoSituacion extends ObjetoGenerico {
    
    //Referencia a la clase seguimientoEstado
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idSeguimientoEstado",nullable = false)
    private SeguimientoEstado seguimientoEstado;
    
    //Referencia a la clase seguimientoSituacion
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idSeguimientoSituacion",nullable = false)
    private SeguimientoSituacion seguimientoSituacion;
    
    //Getters y Setters de la clase
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