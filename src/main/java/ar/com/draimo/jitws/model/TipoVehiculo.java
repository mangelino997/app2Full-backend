//Paquete al que pertenece la clase
package ar.com.draimo.jitws.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Clase Tipo de vehiculo.
 * Define el modelo (columnas) de la base de datos.
 * @author blas
 */

@Entity
@Table(name = "tipovehiculo")
public class TipoVehiculo extends ObjetoGenerico {
    
    //Define el nombre
    @Column(name = "nombre",length = 45, nullable = false, unique = true)
    private String nombre;
    
    //Defiene si el tipo de vehiculo es remolque
    //0 = es remolque  ;  1 = no es remolque
    @Column(name = "esRemolque", nullable = false)
    private boolean esRemolque;
    
    //Define vtoSanidadAlimenticia
    @Column(name = "vtoSanidadAlimenticia", nullable = false)
    private boolean vtoSanidadAlimenticia;
    
    //Getters y Setters de la clase

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean getEsRemolque() {
        return esRemolque;
    }

    public void setEsRemolque(boolean esRemolque) {
        this.esRemolque = esRemolque;
    }

    public boolean isVtoSanidadAlimenticia() {
        return vtoSanidadAlimenticia;
    }

    public void setVtoSanidadAlimenticia(boolean vtoSanidadAlimenticia) {
        this.vtoSanidadAlimenticia = vtoSanidadAlimenticia;
    }
    
}
