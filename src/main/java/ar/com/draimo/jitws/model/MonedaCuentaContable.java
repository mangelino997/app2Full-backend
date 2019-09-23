//Paquete al que pertenece la clase
package ar.com.draimo.jitws.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Clase  moneda cuenta contable
 * Define el modelo (columnas) de la base de datos.
 *
 * @author blas
 */
@Entity
@Table(name = "monedacuentacontable")
public class MonedaCuentaContable extends ObjetoGenerico {

    //Referencia a la clase moneda
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idMoneda", nullable = false)
    private Moneda moneda;

    //Referencia a la clase empresa
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idEmpresa", nullable = false)
    private Empresa empresa;

    //Referencia a la clase Plan de cuenta
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idPlanCuenta", nullable = false)
    private PlanCuenta planCuenta;

    //Getters y Setters de la clase
    public Moneda getMoneda() {
        return moneda;
    }

    public void setMoneda(Moneda moneda) {
        this.moneda = moneda;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public PlanCuenta getPlanCuenta() {
        return planCuenta;
    }

    public void setPlanCuenta(PlanCuenta planCuenta) {
        this.planCuenta = planCuenta;
    }

}
