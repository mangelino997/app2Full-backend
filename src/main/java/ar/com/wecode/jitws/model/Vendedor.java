//Paquete al que pertenece la clase
package ar.com.wecode.jitws.model;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Clase Vendedor
 * Define el modelo (columnas) de la base de datos.
 * @author blas
 */

@Entity
@Table(name = "vendedor")
public class Vendedor extends ObjetoGenerico {
    
    //Define el nombre
    @Column(name = "nombre", nullable = false)
    private String nombre;
    
    //Define la fecha de alta
    @Column(name = "fechaAlta", nullable = false)
    private Date fechaAlta;
    
    //Define la fecha de baja
    @Column(name = "fechaBaja", nullable = true)
    private Date fechaBaja;
    
    //Define la baja
    @Column(name = "estaActivo", nullable = false)
    private boolean estaActivo;
    
    //Define el usuario
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idUsuarioAlta", nullable = true)
    private Usuario usuarioAlta;
    
    //Getters y Setters de la clase

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public Date getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(Date fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    public boolean getEstaActivo() {
        return estaActivo;
    }

    public void setEstaActivo(boolean estaActivo) {
        this.estaActivo = estaActivo;
    }
    
    public Usuario getUsuarioAlta() {
        return usuarioAlta;
    }

    public void setUsuarioAlta(Usuario usuarioAlta) {
        this.usuarioAlta = usuarioAlta;
    }
    
}
