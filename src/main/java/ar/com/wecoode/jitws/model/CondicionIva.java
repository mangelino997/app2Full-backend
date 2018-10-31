//Paquete al que pertenece la clase
package ar.com.wecoode.jitws.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Clase Condicion Iva
 * Define el modelo (columnas) de la base de datos.
 * @author blas
 */

@Entity
@Table(name = "condicioniva")
public class CondicionIva extends ObjetoGenerico {
    
    //Define el nombre
    @Column(name = "nombre", nullable = false)
    private String nombre;
    
    //Define la abreviatura
    @Column(name = "abreviatura", nullable = false)
    private String abreviatura;
    
    //Getters y Setters de la clase

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAbreviatura() {
        return abreviatura;
    }

    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }
    
}
