//Paquete al que pertenece la clase
package ar.com.draimo.jitws.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Clase Grupo Cuenta Contable
 * Define el modelo (columnas) de la base de datos.
 * @author blas
 */

@Entity
@Table(name = "grupocuentacontable")
public class GrupoCuentaContable extends ObjetoGenerico {

    //Define el nombre
    @Column(name = "nombre", length = 45, nullable = false)
    private String nombre;

    //Getters y Setters de la clase

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}