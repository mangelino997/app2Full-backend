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
//    @ManyToOne(cascade = CascadeType.REFRESH)
    @Column(name = "idRepartoTercero", nullable = false)
    private int repartoTercero;
    
    //Referencia a la clase TipoComprobante
//    @ManyToOne(cascade = CascadeType.REFRESH)
    @Column(name = "idTipoComprobante", nullable = false)
    private int tipoComprobante;
    
    //Referencia a la clase Comprobante
//    @ManyToOne(cascade = CascadeType.REFRESH)
    @Column(name = "idComprobante", nullable = false)
    private int comprobante;
    
    //Getters y Setters de la clase

    public int getRepartoTercero() {
        return repartoTercero;
    }

    public void setRepartoTercero(int repartoTercero) {
        this.repartoTercero = repartoTercero;
    }

    public int getTipoComprobante() {
        return tipoComprobante;
    }

    public void setTipoComprobante(int tipoComprobante) {
        this.tipoComprobante = tipoComprobante;
    }

    public int getComprobante() {
        return comprobante;
    }

    public void setComprobante(int comprobante) {
        this.comprobante = comprobante;
    }
    
}
