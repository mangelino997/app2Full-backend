package ar.com.draimo.jitws.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Clase reparto proprio comprobante
 * Mapea con la tabla en la base de datos
 * @author blas
 */

@Entity
@Table(name = "repartotercerocomprobante")
public class RepartoTerceroComprobante extends ObjetoGenerico {
    
    //Referencia a la clase reparto tercero
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idRepartoTercero", nullable = false)
    private RepartoTercero repartoTercero;
    
    //Referencia a la clase TipoComprobante
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idTipoComprobante", nullable = false)
    private TipoComprobante tipoComprobante;
    
    //Referencia a la clase Comprobante
//    @ManyToOne(cascade = CascadeType.REFRESH)
    @Column(name = "idComprobante", nullable = false)
    private int comprobante;
    
    //Referencia a la clase estado
//    @ManyToOne(cascade = CascadeType.REFRESH)
    @Column(name = "idEstado")
    private int estado;
    
    //Getters y Setters de la clase
    
    public RepartoTercero getRepartoTercero() {
        return repartoTercero;
    }

    public void setRepartoTercero(RepartoTercero repartoTercero) {    
        this.repartoTercero = repartoTercero;
    }

    public TipoComprobante getTipoComprobante() {
        return tipoComprobante;
    }

    public void setTipoComprobante(TipoComprobante tipoComprobante) {
        this.tipoComprobante = tipoComprobante;
    }

    public int getComprobante() {
        return comprobante;
    }

    public void setComprobante(int comprobante) {
        this.comprobante = comprobante;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
    
}
