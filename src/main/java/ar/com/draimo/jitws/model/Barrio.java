package ar.com.draimo.jitws.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Clase Barrio 
 * Define el modelo (columnas) de la base de datos.
 *
 * @author blas
 */
@Entity
@Table(name = "barrio")
public class Barrio extends ObjetoGenerico {

    //Define el nombre
    @Column(name = "nombre", length = 45, nullable = false, unique = true)
    private String nombre;
    
    //Referencia a la clase Zona
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idZona", nullable = true)
    private Zona zona;

    //Getters y Setters de la clase
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Zona getZona() {
        return zona;
    }

    public void setZona(Zona zona) {
        this.zona = zona;
    }

}
