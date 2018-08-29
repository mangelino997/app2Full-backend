//Paquete al que pertenece la clase
package ar.com.wecoode.jitws.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Clase Moneda
 * Define el modelo (columnas) de la base de datos.
 * @author blas
 */

@Entity
@Table(name = "moneda")
public class Moneda extends ObjetoGenerico {
    
    //Define el nombre
    @Column(name = "nombre", nullable = false, unique = true)
    private String nombre;
    
    //Define si esta activo o no
    @Column(name = "estaActivo", nullable = false)
    private boolean estaActivo;
    
    //Referencia a la clase Plan de Cuenta
    /*@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPlanCuenta", nullable = false)
    private PlanCuenta planCuenta;*/
    
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
