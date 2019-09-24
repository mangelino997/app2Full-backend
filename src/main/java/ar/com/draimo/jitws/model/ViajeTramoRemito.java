package ar.com.draimo.jitws.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Clase viajetramoremito
 * Mapea con la tabla en la base de datos
 * @author blas
 */

@Entity
@Table(name = "viajetramoremito")
public class ViajeTramoRemito extends ObjetoGenerico {
    
    //Referencia a la clase viajeRemito
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idViajeRemito", nullable = false)
    private ViajeRemito viajeRemito;

    //Referencia a la clase ViajeTramo
    //@JsonBackReference
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idViajeTramo", nullable = true)
    private ViajeTramo viajeTramo;
    
    //Getters y Setters de la clase
    public ViajeRemito getViajeRemito() {
        return viajeRemito;
    }

    public void setViajeRemito(ViajeRemito viajeRemito) {
        this.viajeRemito = viajeRemito;
    }

    public ViajeTramo getViajeTramo() {
        return viajeTramo;
    }

    public void setViajeTramo(ViajeTramo viajeTramo) {
        this.viajeTramo = viajeTramo;
    }

}