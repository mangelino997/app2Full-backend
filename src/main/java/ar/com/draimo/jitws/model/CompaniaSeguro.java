//Paquete al que pertenece la clase
package ar.com.draimo.jitws.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Clase Compañia de seguro. 
 * Define el modelo (columnas) de la base de datos.
 *
 * @author blas
 */
@Entity
@Table(name = "companiaseguro")
public class CompaniaSeguro extends ObjetoGenerico {

    //Define el nombre
    @Column(name = "nombre", length = 60, nullable = false, unique = true)
    private String nombre;

    //Getters y Setters de la clase
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
