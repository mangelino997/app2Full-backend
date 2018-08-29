//Paquete al que pertenece la clase
package ar.com.wecoode.jitws.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Clase Area
 * Define el modelo (columnas) de la base de datos.
 * @author blas
 */

@Entity
@Table(name = "sucursal")
public class Sucursal extends ObjetoGenerico {
    
    //Define el nombre
    @Column(name = "nombre", nullable = false)
    private String nombre;
    
    //Define el domicilio
    @Column(name = "domicilio", nullable = false)
    private String domicilio;
    
    //Referencia a la clase Barrio
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idBarrio", nullable = true)
    private Barrio barrio;
    
    //Referencia a la clase Localidad
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idLocalidad", nullable = false)
    private Localidad localidad;
    
    //Getters y Setters de la clase

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public Barrio getBarrio() {
        return barrio;
    }

    public void setBarrio(Barrio barrio) {
        this.barrio = barrio;
    }

    public Localidad getLocalidad() {
        return localidad;
    }

    public void setLocalidad(Localidad localidad) {
        this.localidad = localidad;
    }
    
}
