package ar.com.draimo.jitws.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Clase tipopercepcion
 * Define el modelo (columnas) de la base de datos.
 * @author blas
 */

@Entity
@Table(name = "tipopercepcion")
public class TipoPercepcion extends ObjetoGenerico {
    
    //Define el nombre
    @Column(name = "nombre",length = 45, nullable = false, unique = true)
    private String nombre;
    
    //Define el detallePorJurisdiccion
    @Column(name = "detallePorJurisdiccion", nullable = false)
    private boolean detallePorJurisdiccion;
    
    //Getters y Setters de la clase
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isDetallePorJurisdiccion() {
        return detallePorJurisdiccion;
    }

    public void setDetallePorJurisdiccion(boolean detallePorJurisdiccion) {
        this.detallePorJurisdiccion = detallePorJurisdiccion;
    }
    
}