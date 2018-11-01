//Paquete al que pertenece la clase
package ar.com.draimo.jitws.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Clase RolSubopcion.
 * Define el modelo (columnas) de la base de datos.
 * @author blas
 */

@Entity
@Table(name = "rolsubopcion")
public class RolSubopcion extends ObjetoGenerico {
    
    //Referencia a la clase Rol
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idRol", nullable = false)
    private Rol rol;
    
    //Referencia a la clase Submodulo
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idSubopcion", nullable = false)
    private Subopcion subopcion;
    
    //Define si el submodulo se muestra o no
    @Column(name = "mostrar", nullable = false)
    private boolean mostrar;
    
    //Getters y Setters de la clase

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public Subopcion getSubopcion() {
        return subopcion;
    }

    public void setSubopcion(Subopcion subopcion) {
        this.subopcion = subopcion;
    }

    public boolean getMostrar() {
        return mostrar;
    }

    public void setMostrar(boolean mostrar) {
        this.mostrar = mostrar;
    }
    
}
