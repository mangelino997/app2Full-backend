//Paquete al que pertenece la clase
package ar.com.wecoode.jitws.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Clase RolOpcion
 * Define el modelo (columnas) de la base de datos.
 * @author blas
 */

@Entity
@Table(name = "rolopcion")
public class RolOpcion extends ObjetoGenerico {
    
    //Referencia a la clase Rol
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idRol", nullable = false)
    private Rol rol;
    
    //Referencia a la clase Opcion
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idOpcion", nullable = false)
    private Opcion opcion;
    
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

    public Opcion getOpcion() {
        return opcion;
    }

    public void setOpcion(Opcion opcion) {
        this.opcion = opcion;
    }

    public boolean getMostrar() {
        return mostrar;
    }

    public void setMostrar(boolean mostrar) {
        this.mostrar = mostrar;
    }
    
}
