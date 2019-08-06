package ar.com.draimo.jitws.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.sql.Timestamp;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Clase soporte
 * Mapea con la tabla en la base de datos
 * @author blas
 */

@Entity
@Table(name = "soporte")
public class Soporte extends ObjetoGenerico {
    
    //Define fecha
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone ="UTC-3")
    @Column(name = "fecha", nullable = false)  
    private Timestamp fecha;
    
    //Referencia a la clase Empresa
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idEmpresa", nullable = false)
    private Empresa empresa;
    
    //Referencia a la clase Subopcion
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idSubopcion", nullable = false)
    private Subopcion subopcion;
    
    //Referencia a la clase Usuario
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idUsuario", nullable = false)
    private Usuario usuario;
    
    //Define el mensaje
    @Column(name = "mensaje", length = 400, nullable = false)
    private String mensaje;
    
    //Define el alias
    @Column(name = "alias", length = 100, nullable = true)
    private String alias;
    
    //Referencia a la clase BugImagen
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idBugImagen", nullable = true)
    private BugImagen bugImagen;
    
    //Referencia a la clase SoporteEstado
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idSoporteEstado", nullable = false)
    private SoporteEstado soporteEstado;
    
    //Getters y Setters de la clase

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Subopcion getSubopcion() {
        return subopcion;
    }

    public void setSubopcion(Subopcion subopcion) {
        this.subopcion = subopcion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public BugImagen getBugImagen() {
        return bugImagen;
    }

    public void setBugImagen(BugImagen bugImagen) {
        this.bugImagen = bugImagen;
    }

    public SoporteEstado getSoporteEstado() {
        return soporteEstado;
    }

    public void setSoporteEstado(SoporteEstado soporteEstado) {
        this.soporteEstado = soporteEstado;
    }
    
}
