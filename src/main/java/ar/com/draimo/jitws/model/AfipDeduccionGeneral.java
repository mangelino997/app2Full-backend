//Paquete al que pertenece la clase
package ar.com.draimo.jitws.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Clase AfipDeduccionGeneral
 * Define el modelo (columnas) de la base de datos.
 * @author blas
 */

@Entity
@Table(name = "afipdeducciongeneral")
public class AfipDeduccionGeneral extends ObjetoGenerico {

    //Define el descripcion
    @Column(name = "descripcion", length = 80, nullable = false)
    private String descripcion;

    //Getters y Setters de la clase

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}