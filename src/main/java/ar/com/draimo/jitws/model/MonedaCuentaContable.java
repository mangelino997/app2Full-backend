//Paquete al que pertenece la clase
package ar.com.draimo.jitws.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Clase MonedaCotizacion
 * Define el modelo (columnas) de la base de datos.
 * @author blas
 */

@Entity
@Table(name = "monedacuentacontable")
public class MonedaCuentaContable extends ObjetoGenerico {
    
    //Instancia de la clase moneda
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idMoneda", nullable = false)
    private Moneda moneda;
    
    //Instancia de la clase empresa
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idEmpresa", nullable = false)
    private Empresa empresa;
    
    //Instancia de la clase cuentaContable(todavia no existe)
    //@ManyToOne(cascade = CascadeType.REFRESH)
    @Column(name = "idCuentaContable", nullable = false)
    private int cuentaContable;
    
}
