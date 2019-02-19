//Paquete al que pertenece la clase
package ar.com.draimo.jitws.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Clase CondicionVenta
 * Define el modelo (columnas) de la base de datos.
 * @author blas
 */

@Entity
@Table(name = "condicionventa")
public class CondicionVenta extends ObjetoGenerico {
    
    //Define el nombre
    @Column(name = "nombre", nullable = false)
    private String nombre;
    
    //Define si es contado
    @Column(name = "esContado", nullable = false)
    private boolean esContado;
    
    //Getters y Setters de la clase

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isEsContado() {
        return esContado;
    }

    public void setEsContado(boolean esContado) {
        this.esContado = esContado;
    }
    
}
