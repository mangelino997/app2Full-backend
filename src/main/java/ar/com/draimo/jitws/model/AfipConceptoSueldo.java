//Paquete al que pertenece la clase
package ar.com.draimo.jitws.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Clase AfipConceptoSueldo
 * Define el modelo (columnas) de la base de datos.
 *
 * @author blas
 */
@Entity
@Table(name = "afipconceptosueldo")
public class AfipConceptoSueldo extends ObjetoGenerico {
    
    //Define el nombre
    @Column(name = "nombre", length = 140, nullable = false)
    private String nombre;

    //Referencia a la clase AfipConceptoSueldoGrupo
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idAfipConceptoSueldoGrupo", nullable = true)
    private AfipConceptoSueldoGrupo afipConceptoSueldoGrupo;

    //Getters y Setters de la clase
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public AfipConceptoSueldoGrupo getAfipConceptoSueldoGrupo() {
        return afipConceptoSueldoGrupo;
    }

    public void setAfipConceptoSueldoGrupo(AfipConceptoSueldoGrupo afipConceptoSueldoGrupo) {
        this.afipConceptoSueldoGrupo = afipConceptoSueldoGrupo;
    }
    
}
