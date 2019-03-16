//Paquete al que pertenece la clase
package ar.com.draimo.jitws.model;

import com.fasterxml.jackson.annotation.JsonFilter;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Clase Plan de Cuenta
 * Define el modelo (columnas) de la base de datos.
 * @author blas
 */

@Entity
@Table(name = "plandecuenta")
@JsonFilter("filtroPlanCuenta")
public class PlanCuenta extends ObjetoGenerico {

    //Referencia a la clase Empresa
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idEmpresa")
    private Empresa empresa;
    
    //Referencia a la clase PlanDeCuenta
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idPadre")
    private PlanCuenta padre;
    
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
    
    //Referencia a la clase Tipo Cuenta Contable
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idTipoCuentaContable")
    private TipoCuentaContable tipoCuentaContable;
    
    //Define el nivel
    @Column(name = "nivel", nullable = false)
    private short nivel;
    
    //Define las cuentas hijas
    @OneToMany(cascade = CascadeType.REFRESH, mappedBy = "padre")
    private List<PlanCuenta> hijos;
    
    //Getters y setters de la clase

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public PlanCuenta getPadre() {
        return padre;
    }

    public void setPadre(PlanCuenta padre) {
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

    public TipoCuentaContable getTipoCuentaContable() {
        return tipoCuentaContable;
    }

    public void setTipoCuentaContable(TipoCuentaContable tipoCuentaContable) {
        this.tipoCuentaContable = tipoCuentaContable;
    }

    public short getNivel() {
        return nivel;
    }

    public void setNivel(short nivel) {
        this.nivel = nivel;
    }
    
    public List<PlanCuenta> getHijos() {
        return hijos;
    }

    public void setHijos(List<PlanCuenta> hijos) {
        this.hijos = hijos;
    }
    
}