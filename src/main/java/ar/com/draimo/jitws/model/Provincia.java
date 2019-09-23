//Paquete al que pertenece la clase
package ar.com.draimo.jitws.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Clase Provincia.
 * Define el modelo (columnas) de la base de datos.
 * 
 * @author blas
 */

@Entity
@Table(name = "provincia")
public class Provincia extends ObjetoGenerico {
    
    //Define el nombre
    @Column(name = "nombre",length = 45, nullable = false, unique = true)
    private String nombre;
    
    //Define el codigo de ingresos brutos
    @Column(name = "codigoIIBB", length = 3)
    private String codigoIIBB;
    
    //Define el codigo de afip
    @Column(name = "codigoAfip",length = 3, nullable = true)
    private String codigoAfip;
    
    //Referencia a la clase Pais
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idPais", nullable = false)
    private Pais pais;
    
    //Getters y Setters de la clase
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigoIIBB() {
        return codigoIIBB;
    }

    public void setCodigoIIBB(String codigoIIBB) {
        this.codigoIIBB = codigoIIBB;
    }

    public String getCodigoAfip() {
        return codigoAfip;
    }

    public void setCodigoAfip(String codigoAfip) {
        this.codigoAfip = codigoAfip;
    }
    
    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }
    
}