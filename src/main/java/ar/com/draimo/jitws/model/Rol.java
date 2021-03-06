//Paquete al que pertenece la clase
package ar.com.draimo.jitws.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Clase Rol.
 * Define el modelo (columnas) de la base de datos.
 * @author blas
 */

@Entity
@Table(name = "rol")
public class Rol extends ObjetoGenerico {
    
    //Define el nombre
    @Column(name = "nombre",length = 45, nullable = false, unique = true)
    private String nombre;
    
    //Define si es desarrollador
    @Column(name = "esDesarrollador", nullable = false)
    private boolean esDesarrollador;
    
    //Getters y Setters de la clase
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean getEsDesarrollador() {
        return esDesarrollador;
    }

    public void setEsDesarrollador(boolean esDesarrollador) {
        this.esDesarrollador = esDesarrollador;
    }
    
}
