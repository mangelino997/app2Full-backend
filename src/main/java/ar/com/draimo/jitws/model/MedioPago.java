//Paquete al que pertenece la clase
package ar.com.draimo.jitws.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Clase MedioPago Define el modelo (columnas) de la base de datos.
 *
 * @author blas
 */
@Entity
@Table(name = "mediopago")
public class MedioPago extends ObjetoGenerico {
    
    //Define el nombre
    @Column(name = "nombre", length = 45, nullable = false, unique = true)
    private String nombre;

    //Define si esta activo para ingreso
    @Column(name = "estaActivoIngreso", nullable = false)
    private boolean estaActivoIngreso;

    //Define si esta activo para egreso
    @Column(name = "estaActivoEgreso", nullable = false)
    private boolean estaActivoEgreso;

    //Getters y Setters de la clase

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isEstaActivoIngreso() {
        return estaActivoIngreso;
    }

    public void setEstaActivoIngreso(boolean estaActivoIngreso) {
        this.estaActivoIngreso = estaActivoIngreso;
    }

    public boolean isEstaActivoEgreso() {
        return estaActivoEgreso;
    }

    public void setEstaActivoEgreso(boolean estaActivoEgreso) {
        this.estaActivoEgreso = estaActivoEgreso;
    }
    
}
