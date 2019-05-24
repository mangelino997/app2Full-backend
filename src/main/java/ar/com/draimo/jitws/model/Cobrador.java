//Paquete al que pertenece la clase
package ar.com.draimo.jitws.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.sql.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Clase Cobrador
 * Define el modelo (columnas) de la base de datos.
 * @author blas
 */

@Entity
@Table(name = "cobrador")
public class Cobrador extends ObjetoGenerico {
    
    //Define el nombre
    @Column(name = "nombre", length = 45, nullable = false, unique = true)
    private String nombre;
    
    //Define la fecha de alta
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC-3")
    @Column(name = "fechaAlta", nullable = false)
    private Date fechaAlta;
    
    //Define la fecha de baja
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC-3")
    @Column(name = "fechaBaja", nullable = true)
    private Date fechaBaja;
    
    //Define si esta activo
    @Column(name = "estaActivo", nullable = false)
    private boolean estaActivo;
    
    //Define el usuario
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idUsuarioAlta", nullable = true)
    private Usuario usuarioAlta;
    
    //Define si por defecto es un cliente eventual
    @Column(name = "porDefectoClienteEventual", nullable = false)
    private boolean porDefectoClienteEventual;
    
    //Define correo electronico
    @Column(name = "correoElectronico", length = 60, nullable = true)
    private String correoElectronico;
    
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

    public boolean isPorDefectoClienteEventual() {
        return porDefectoClienteEventual;
    }

    public void setPorDefectoClienteEventual(boolean porDefectoClienteEventual) {
        this.porDefectoClienteEventual = porDefectoClienteEventual;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }
    
}
