package ar.com.draimo.jitws.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Clase viajeremitotramo
 * Mapea con la tabla en la base de datos
 * @author blas
 */

@Entity
@Table(name = "viajeremitotramo")
public class ViajeRemitoTramo extends ObjetoGenerico {
    
    //Referencia a la clase viajeRemito
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idViajeRemito", nullable = false)
    private ViajeRemito viajeRemito;

    //Referencia a la clase ViajePropioTramo
    //@JsonBackReference
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idViajePropioTramo", nullable = true)
    private ViajePropioTramo viajePropioTramo;
    
    //Referencia a la clase ViajeTerceroTramo
    //@JsonBackReference
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idViajeTerceroTramo", nullable = true)
    private ViajeTerceroTramo viajeTerceroTramo;
    
    //Getters y Setters de la clase

    public ViajeRemito getViajeRemito() {
        return viajeRemito;
    }

    public void setViajeRemito(ViajeRemito viajeRemito) {
        this.viajeRemito = viajeRemito;
    }

    public ViajePropioTramo getViajePropioTramo() {
        return viajePropioTramo;
    }

    public void setViajePropioTramo(ViajePropioTramo viajePropioTramo) {
        this.viajePropioTramo = viajePropioTramo;
    }

    public ViajeTerceroTramo getViajeTerceroTramo() {
        return viajeTerceroTramo;
    }

    public void setViajeTerceroTramo(ViajeTerceroTramo viajeTerceroTramo) {
        this.viajeTerceroTramo = viajeTerceroTramo;
    }

}
