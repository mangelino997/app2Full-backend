//Paquete al que pertenece la clase
package ar.com.draimo.jitws.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Clase Localidad
 * Define el modelo (columnas) de la base de datos.
 * @author blas
 */

@Entity
@Table(name = "grupocuentacontable")
public class GrupoCuentaContable extends ObjetoGenerico {
    
    //Define el nombre
    @Column(name = "nombre", nullable = false)
    private String nombre;
    
    //Referencia a la clase tipoCuentaContable
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idTipoCuentaContable", nullable = false)
    private TipoCuentaContable tipoCuentaContable;
    
    //Getters y Setters de la clase

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public TipoCuentaContable getTipoCuentaContable() {
        return tipoCuentaContable;
    }

    public void setTipoCuentaContable(TipoCuentaContable tipoCuentaContable) {
        this.tipoCuentaContable = tipoCuentaContable;
    }
    
}
