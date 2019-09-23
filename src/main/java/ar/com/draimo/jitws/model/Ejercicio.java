//Paquete al que pertenece la clase
package ar.com.draimo.jitws.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Clase Ejercicio. 
 * Define el modelo (columnas) de la base de datos.
 *
 * @author blas
 */
@Entity
@Table(name = "ejercicio")
public class Ejercicio extends ObjetoGenerico {

    //Referencia a la clase Empresa
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idEmpresa", nullable = false)
    private Empresa empresa;

    //Define el nombre
    @Column(name = "nombre", length = 45, nullable = false)
    private String nombre;

    //Define el anio de inicio
    @Column(name = "anioInicio", length = 4, nullable = false)
    private short anioInicio;

    //Referencia a la clase mes
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idMesInicio", nullable = false)
    private Mes mesInicio;

    //Define cantidad meses
    @Column(name = "cantidadMeses", length = 2, nullable = false)
    private short cantidadMeses;

    //Define porDefecto
    @Column(name = "porDefecto", nullable = true)
    private boolean porDefecto;

    //Referencia a la clase usuario (alta)
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idUsuarioAlta", nullable = false)
    private Usuario usuarioAlta;

    //Referencia a la clase usuario(modificacion)
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idUsuarioMod", nullable = true)
    private Usuario usuarioMod;

    //Getters y Setters de la clase
    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public short getAnioInicio() {
        return anioInicio;
    }

    public void setAnioInicio(short anioInicio) {
        this.anioInicio = anioInicio;
    }

    public Mes getMesInicio() {
        return mesInicio;
    }

    public void setMesInicio(Mes mesInicio) {
        this.mesInicio = mesInicio;
    }

    public short getCantidadMeses() {
        return cantidadMeses;
    }

    public void setCantidadMeses(short cantidadMeses) {
        this.cantidadMeses = cantidadMeses;
    }

    public boolean isPorDefecto() {
        return porDefecto;
    }

    public void setPorDefecto(boolean porDefecto) {
        this.porDefecto = porDefecto;
    }

    public Usuario getUsuarioAlta() {
        return usuarioAlta;
    }

    public void setUsuarioAlta(Usuario usuarioAlta) {
        this.usuarioAlta = usuarioAlta;
    }

    public Usuario getUsuarioMod() {
        return usuarioMod;
    }

    public void setUsuarioMod(Usuario usuarioMod) {
        this.usuarioMod = usuarioMod;
    }

}
