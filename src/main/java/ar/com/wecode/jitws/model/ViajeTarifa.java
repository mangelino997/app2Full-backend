//Paquete al que pertenece la clase
package ar.com.wecode.jitws.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Clase Viaje Tarifa
 * Define el modelo (columnas) de la base de datos.
 * @author blas
 */

@Entity
@Table(name = "viajetarifa")
public class ViajeTarifa extends ObjetoGenerico {

    //Define el nombre
    @Column(name = "nombre", nullable = false)
    private String nombre;
    
    //Define el costo propio
    @Column(name = "estaActivo", nullable = false)
    private boolean estaActivo;

    //Getters y Setters de la clase

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean getEstaActivo() {
        return estaActivo;
    }

    public void setEstaActivo(boolean estaActivo) {
        this.estaActivo = estaActivo;
    }
    
}