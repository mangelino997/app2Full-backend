//Paquete al que pertenece la clase
package ar.com.draimo.jitws.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Clase Rubro del producto.
 * Define el modelo (columnas) de la base de datos.
 * @author blas
 */

@Entity
@Table(name = "rubroproducto")
public class RubroProducto extends ObjetoGenerico {
    
    //Define el nombre
    @Column(name = "nombre", nullable = false, unique = true)
    private String nombre;
    
    //Define si es insumo
    @Column(name = "esInsumo", nullable = false)
    private boolean esInsumo;
    
    //Define si es combustible
    @Column(name = "esCombustible", nullable = false)
    private boolean esCombustible;
    
    //Getters y Setters de la clase

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isEsInsumo() {
        return esInsumo;
    }

    public void setEsInsumo(boolean esInsumo) {
        this.esInsumo = esInsumo;
    }

    public boolean isEsCombustible() {
        return esCombustible;
    }

    public void setEsCombustible(boolean esCombustible) {
        this.esCombustible = esCombustible;
    }
    
}
