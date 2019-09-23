//Paquete al que pertenece la clase
package ar.com.draimo.jitws.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Clase Opcion 
 * Define el modelo (columnas) de la base de datos.
 *
 * @author blas
 */
@Entity
@Table(name = "opcion")
public class Opcion extends ObjetoGenerico {

    //Define el nombre
    @Column(name = "nombre", length = 45, nullable = false)
    private String nombre;

    //Define si la opcion es un ABM
    @Column(name = "esABM", nullable = false)
    private boolean esABM;

    //Referencia a la clase Subopcion
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idSubopcion", nullable = false)
    private Subopcion subopcion;

    //Getters y Setters de la clase
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean getEsABM() {
        return esABM;
    }

    public void setEsABM(boolean esABM) {
        this.esABM = esABM;
    }

    public Subopcion getSubopcion() {
        return subopcion;
    }

    public void setSubopcion(Subopcion subopcion) {
        this.subopcion = subopcion;
    }

}
