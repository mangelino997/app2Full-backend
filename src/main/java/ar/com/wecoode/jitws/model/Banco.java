//Paquete al que pertenece la clase
package ar.com.wecoode.jitws.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Clase Banco
 * Define el modelo (columnas) de la base de datos.
 * @author blas
 */

@Entity
@Table(name = "banco")
public class Banco extends ObjetoGenerico {
    
    //Define el nombre
    @Column(name = "nombre", nullable = false, unique = true)
    private String nombre;
    
    //Define el sitio web
    @Column(name = "sitioWeb", nullable = true)
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
