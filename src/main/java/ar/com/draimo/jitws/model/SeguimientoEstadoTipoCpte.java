package ar.com.draimo.jitws.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Clase Seguimiento Estado Tipo Cte
 * Define el modelo (columnas) de la base de datos.
 * 
 * @author blas
 */

@Entity
@Table(name = "seguimientoestadotipocpte")
public class SeguimientoEstadoTipoCpte extends ObjetoGenerico {
    
    //Referencia a la clase seguimientoEstado
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idSeguimientoEstado",nullable = false)
    private SeguimientoEstado seguimientoEstado;
    
    //Referencia a la clase TipoComprobante
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idTipoComprobante",nullable = false)
    private TipoComprobante tipoComprobante;
    
    //Getters y Setters de la clase
    public SeguimientoEstado getSeguimientoEstado() {
        return seguimientoEstado;
    }

    public void setSeguimientoEstado(SeguimientoEstado seguimientoEstado) {
        this.seguimientoEstado = seguimientoEstado;
    }

    public TipoComprobante getTipoComprobante() {
        return tipoComprobante;
    }

    public void setTipoComprobante(TipoComprobante tipoComprobante) {
        this.tipoComprobante = tipoComprobante;
    }

}