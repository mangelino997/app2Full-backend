//Paquete al que pertenece la clase
package ar.com.draimo.jitws.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Clase AfipTipoBeneficio 
 * Define el modelo (columnas) de la base de datos.
 *
 * @author blas
 */
@Entity
@Table(name = "afiptipobeneficio")
public class AfipTipoBeneficio extends ObjetoGenerico {

    //Define descripcion
    @Column(name = "descripcion", length = 45, nullable = false)
    private String descripcion;

    //Getters y Setters de la clase
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
