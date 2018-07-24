//Paquete al que pertenece la clase
package ar.com.wecode.jitws.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Clase AfipActividad
 * Define el modelo (columnas) de la base de datos.
 * @author blas
 */

@Entity
@Table(name = "afipactividad")
public class AfipActividad extends ObjetoGenerico {
    
    //Define el codigo de afip
    @Column(name = "codigoAfip", nullable = false)
    private String codigoAfip;
    
    //Define el nombre
    @Column(name = "nombre", nullable = false)
    private String nombre;
    
    //Getters y Setters de la clase

    public String getCodigoAfip() {
        return codigoAfip;
    }

    public void setCodigoAfip(String codigoAfip) {
        this.codigoAfip = codigoAfip;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
}
