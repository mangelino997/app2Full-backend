//Paquete al que pertenece la clase
package ar.com.wecode.jitws.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Clase OrigenDestino
 * Define el modelo (columnas) de la base de datos.
 * @author blas
 */

@Entity
@Table(name = "origendestino")
public class OrigenDestino extends ObjetoGenerico {
    
    //Define el nombre
    @Column(name = "nombre", nullable = false)
    private String nombre;

    //Referencia a la clase Provincia
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idProvincia", nullable = false)
    private Provincia provincia;
    
    //Getters y Setters de la clase

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Provincia getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }
    
}
