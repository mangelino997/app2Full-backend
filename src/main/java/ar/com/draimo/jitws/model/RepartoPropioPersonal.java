package ar.com.draimo.jitws.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Clase reparto propio personal
 * Mapea con la tabla en la base de datos
 * @author blas
 */

@Entity
@Table(name = "repartopropiopersonal")
public class RepartoPropioPersonal extends ObjetoGenerico {
    
    //Referencia a la clase reparto propio
    @JsonBackReference
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idRepartoPropio", nullable = false)
    private RepartoPropio repartoPropio;
    
    //Referencia a la clase personal
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idPersonal", nullable = false)
    private Personal personal;
    
    //Getters y Setters de la clase

    public RepartoPropio getRepartoPropio() {
        return repartoPropio;
    }

    public void setRepartoPropio(RepartoPropio repartoPropio) {
        this.repartoPropio = repartoPropio;
    }

    public Personal getPersonal() {
        return personal;
    }

    public void setPersonal(Personal personal) {
        this.personal = personal;
    }

}
