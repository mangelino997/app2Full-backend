package ar.com.draimo.jitws.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Clase reparto personal
 * Mapea con la tabla en la base de datos.
 * @author blas
 */

@Entity
@Table(name = "repartopersonal")
public class RepartoPersonal extends ObjetoGenerico {
    
    //Referencia a la clase reparto
    @JsonBackReference
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idReparto", nullable = false)
    private Reparto reparto;
    
    //Referencia a la clase personal
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idPersonal", nullable = false)
    private Personal personal;
    
    //Getters y Setters de la clase
    public Reparto getReparto() {
        return reparto;
    }

    public void setReparto(Reparto reparto) {
        this.reparto = reparto;
    }

    public Personal getPersonal() {
        return personal;
    }

    public void setPersonal(Personal personal) {
        this.personal = personal;
    }

}
