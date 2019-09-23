package ar.com.draimo.jitws.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Clase Seguimiento Estado
 * Define el modelo (columnas) de la base de datos.
 * 
 * @author blas
 */

@Entity
@Table(name = "seguimientoestado")
public class SeguimientoEstado extends ObjetoGenerico {
    
    //Define el nombre
    @Column(name = "nombre",length = 45, nullable = false, unique = true)
    private String nombre;
    
    //Define si es entregado
    @Column(name = "esEntregado", nullable = false)
    private boolean esEntregado;
    
    //Define si el reparto es saliente
    @Column(name = "repartoSaliente", nullable = false)
    private boolean repartoSaliente;
    
    //Define si el reparto es entrante
    @Column(name = "repartoEntrante", nullable = false)
    private boolean repartoEntrante;
    
    //Define los getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean getEsEntregado() {
        return esEntregado;
    }

    public void setEsEntregado(boolean esEntregado) {
        this.esEntregado = esEntregado;
    }

    public boolean getRepartoSaliente() {
        return repartoSaliente;
    }

    public void setRepartoSaliente(boolean repartoSaliente) {
        this.repartoSaliente = repartoSaliente;
    }

    public boolean getRepartoEntrante() {
        return repartoEntrante;
    }

    public void setRepartoEntrante(boolean repartoEntrante) {
        this.repartoEntrante = repartoEntrante;
    }

}
