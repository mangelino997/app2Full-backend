//Paquete al que pertenece la clase
package ar.com.draimo.jitws.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Clase RubroProductoCuentaContable
 * Define el modelo (columnas) de la base de datos.
 * @author blas
 */

@Entity
@Table(name = "rubroproductocuentacontable")
public class RubroProductoCuentaContable extends ObjetoGenerico {

    //Referencia a la clase cliente
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idEmpresa", nullable = false)
    private Empresa empresa;
    
    //Referencia a la clase PlanCuenta
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idPlanCuentaCompra", nullable = true)
    private PlanCuenta planCuentaCompra;
    
    //Referencia a la clase RubroProducto
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idRubroProducto", nullable = false)
    private RubroProducto rubroProducto;
    
    //Getters y Setters de la clase
    
    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public PlanCuenta getPlanCuentaCompra() {
        return planCuentaCompra;
    }

    public void setPlanCuentaCompra(PlanCuenta planCuentaCompra) {
        this.planCuentaCompra = planCuentaCompra;
    }

    public RubroProducto getRubroProducto() {
        return rubroProducto;
    }

    public void setRubroProducto(RubroProducto rubroProducto) {
        this.rubroProducto = rubroProducto;
    }
    
}