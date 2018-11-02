//Paquete al que pertenece la clase
package ar.com.draimo.jitws.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Clase Marca de vehiculo.
 * Define el modelo (columnas) de la base de datos.
 * @author blas
 */

@Entity
@Table(name = "marcavehiculo")
public class MarcaVehiculo extends ObjetoGenerico {
    
    //Define el nombre
    @Column(name = "nombre", nullable = false, unique = true)
    private String nombre;
    
    //Getters y Setters de la clase

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
}