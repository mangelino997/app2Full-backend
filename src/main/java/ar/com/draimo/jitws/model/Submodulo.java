//Paquete al que pertenece la clase
package ar.com.draimo.jitws.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Clase Submodulo. 
 * Define el modelo (columnas) de la base de datos.
 *
 * @author blas
 */
@Entity
@Table(name = "submodulo")
public class Submodulo extends ObjetoGenerico {

    //Define el nombre
    @Column(name = "nombre", length = 45, nullable = false)
    private String nombre;

    //Referencia a la clase Supermodulo
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idModulo", nullable = false)
    private Modulo modulo;

    //Getters y Setters de la clase
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Modulo getModulo() {
        return modulo;
    }

    public void setModulo(Modulo modulo) {
        this.modulo = modulo;
    }

}
