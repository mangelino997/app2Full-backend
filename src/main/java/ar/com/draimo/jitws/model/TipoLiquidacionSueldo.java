//Paquete al que pertenece la clase
package ar.com.draimo.jitws.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Clase tipo liquidacion sueldo
 * Define el modelo (columnas) de la base de datos.
 * @author blas
 */

@Entity
@Table(name = "tipoliquidacionsueldo")
public class TipoLiquidacionSueldo extends ObjetoGenerico {
    
    //Define el nombre
    @Column(name = "nombre",length = 45, nullable = false, unique = true)
    private String nombre;
    
    //Define el numeroAfip
    @Column(name = "codigoAfip",length = 3, nullable = false)
    private String codigoAfip;
    
    //Define el estaHabilitado
    @Column(name = "estaHabilitado", nullable = false)
    private boolean estaHabilitado;
    
    //Getters y Setters de la clase
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigoAfip() {
        return codigoAfip;
    }

    public void setCodigoAfip(String codigoAfip) {
        this.codigoAfip = codigoAfip;
    }

    public boolean isEstaHabilitado() {
        return estaHabilitado;
    }

    public void setEstaHabilitado(boolean estaHabilitado) {
        this.estaHabilitado = estaHabilitado;
    }
    
}
