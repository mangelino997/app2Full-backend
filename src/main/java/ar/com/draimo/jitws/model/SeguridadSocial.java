//Paquete al que pertenece la clase
package ar.com.draimo.jitws.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Clase Seguridad social. 
 * Define el modelo (columnas) de la base de datos.
 *
 * @author blas
 */
@Entity
@Table(name = "seguridadsocial")
public class SeguridadSocial extends ObjetoGenerico {

    //Define el nombre
    @Column(name = "nombre", length = 45, nullable = false, unique = true)
    private String nombre;

    //Define el sitio web
    @Column(name = "sitioWeb", length = 60, nullable = true)
    private String sitioWeb;

    //Getters y Setters de la clase
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSitioWeb() {
        return sitioWeb;
    }

    public void setSitioWeb(String sitioWeb) {
        this.sitioWeb = sitioWeb;
    }

}
