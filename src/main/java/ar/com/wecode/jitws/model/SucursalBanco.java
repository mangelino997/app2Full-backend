//Paquete al que pertenece la clase
package ar.com.wecode.jitws.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Clase Sucursal Banco
 * Define el modelo (columnas) de la base de datos.
 * @author blas
 */

@Entity
@Table(name = "sucursalbanco")
public class SucursalBanco extends ObjetoGenerico {
    
    //Referencia a la clase Banco
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idBanco", nullable = false)
    private Banco banco;

    //Define el nombre
    @Column(name = "nombre", nullable = false, unique = true)
    private String nombre;

    //Getters y Setters de la clase

    public Banco getBanco() {
        return banco;
    }

    public void setBanco(Banco banco) {
        this.banco = banco;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}