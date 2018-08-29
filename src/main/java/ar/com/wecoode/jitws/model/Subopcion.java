//Paquete al que pertenece la clase
package ar.com.wecoode.jitws.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Clase Subopcion.
 * Define el modelo (columnas) de la base de datos.
 * @author blas
 */

@Entity
@Table(name = "subopcion")
public class Subopcion extends ObjetoGenerico {
    
    //Define el nombre
    @Column(name = "nombre", nullable = false)
    private String nombre;
    
    //Define si el submodulo es una ABM
    @Column(name = "esABM", nullable = false)
    private boolean esABM;
    
    //Referencia a la clase Modulo
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idSubmodulo", nullable = false)
    private Submodulo submodulo;
    
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
    
    public Submodulo getSubmodulo() {
        return submodulo;
    }

    public void setSubmodulo(Submodulo submodulo) {
        this.submodulo = submodulo;
    }
    
}
