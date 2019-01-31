//Paquete al que pertenece la clase
package ar.com.draimo.jitws.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Clase Area
 * Define el modelo (columnas) de la base de datos.
 * @author blas
 */

@Entity
@Table(name = "plandecuenta")
public class PlandeCuenta extends ObjetoGenerico {

    //Referencia a la clase Empresa
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idEmpresa")
    private Empresa empresa;
    
    //Referencia a la clase grupo cuenta contable
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idGrupoCuentaContable")
    private GrupoCuentaContable grupoCuentaContable;
    
    //Referencia a la clase PlanDeCuenta
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idPadre")
    private PlandeCuenta padre;
    
    //Define el nombre
    @Column(name = "nombre", length = 45, nullable = false)
    private String nombre;
    
    //Define si es imputable
    @Column(name = "esImputable", nullable = false)
    private boolean esImputable;
    
    //Define si esta activo
    @Column(name = "estaActivo", nullable = false)
    private boolean estaActivo;

    //Referencia a la clase Usuario alta
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idUsuarioAlta")
    private Usuario usuarioAlta;
    
    //Referencia a la clase Usuario mod
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idUsuarioMod")
    private Usuario usuarioMod;
    
    //Getters y setters de la clase

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public GrupoCuentaContable getGrupoCuentaContable() {
        return grupoCuentaContable;
    }

    public void setGrupoCuentaContable(GrupoCuentaContable grupoCuentaContable) {
        this.grupoCuentaContable = grupoCuentaContable;
    }

    public PlandeCuenta getPadre() {
        return padre;
    }

    public void setPadre(PlandeCuenta padre) {
        this.padre = padre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isEsImputable() {
        return esImputable;
    }

    public void setEsImputable(boolean esImputable) {
        this.esImputable = esImputable;
    }

    public boolean isEstaActivo() {
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

    public Usuario getUsuarioMod() {
        return usuarioMod;
    }

    public void setUsuarioMod(Usuario usuarioMod) {
        this.usuarioMod = usuarioMod;
    }
    
}