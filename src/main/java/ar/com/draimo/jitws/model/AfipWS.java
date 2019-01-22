//Paquete al que pertenece la clase
package ar.com.draimo.jitws.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Clase Categoria
 * Define el modelo (columnas) de la base de datos.
 * @author blas
 */

@Entity
@Table(name = "afipws")
public class AfipWS extends ObjetoGenerico {
    
    //Define el nombre
    @Column(name = "nombre",length = 45, nullable = false)
    private String nombre;
    
    //Define la descripcion
    @Column(name = "descripcion",length = 100, nullable = false)
    private String descripcion;
    
    //Define el urlReal
    @Column(name = "urlReal",length = 150, nullable = false)
    private String urlReal;
    
    //Define el urlPrueba
    @Column(name = "urlPrueba", nullable = false)
    private String urlPrueba;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUrlReal() {
        return urlReal;
    }

    public void setUrlReal(String urlReal) {
        this.urlReal = urlReal;
    }

    public String getUrlPrueba() {
        return urlPrueba;
    }

    public void setUrlPrueba(String urlPrueba) {
        this.urlPrueba = urlPrueba;
    }
    
}
