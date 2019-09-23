package ar.com.draimo.jitws.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Clase depositoinsumoproducto 
 * Define el modelo (columnas) de la base de datos.
 *
 * @author blas
 */
@Entity
@Table(name = "depositoinsumoproducto")
public class DepositoInsumoProducto extends ObjetoGenerico {

    //Define el nombre
    @Column(name = "nombre", length = 45, nullable = false)
    private String nombre;

    //Define si es propio
    @Column(name = "esPropio")
    private boolean esPropio;

    //Getters y Setters de la clase
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isEsPropio() {
        return esPropio;
    }

    public void setEsPropio(boolean esPropio) {
        this.esPropio = esPropio;
    }

}
