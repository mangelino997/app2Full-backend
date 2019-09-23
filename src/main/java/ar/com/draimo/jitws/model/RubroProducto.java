//Paquete al que pertenece la clase
package ar.com.draimo.jitws.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Clase Rubro producto.
 * Define el modelo (columnas) de la base de datos.
 *
 * @author blas
 */

@Entity
@Table(name = "rubroproducto")
public class RubroProducto extends ObjetoGenerico {
    
    //Define el nombre
    @Column(name = "nombre", length = 45, nullable = false, unique = true)
    private String nombre;
    
    //Define si es insumo
    @Column(name = "esInsumo", nullable = false)
    private boolean esInsumo;
    
    //Define si es combustible
    @Column(name = "esCombustible", nullable = false)
    private boolean esCombustible;
    
    //Referencia a la clase rubro producto cuenta contable
    @JsonIgnoreProperties("rubroProducto")
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "rubroProducto")
    private List<RubroProductoCuentaContable> rubrosProductosCuentasContables;
    
    //Getters y Setters de la clase
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean getEsInsumo() {
        return esInsumo;
    }

    public void setEsInsumo(boolean esInsumo) {
        this.esInsumo = esInsumo;
    }

    public boolean getEsCombustible() {
        return esCombustible;
    }

    public void setEsCombustible(boolean esCombustible) {
        this.esCombustible = esCombustible;
    }

    public List<RubroProductoCuentaContable> getRubrosProductosCuentasContables() {
        return rubrosProductosCuentasContables;
    }

    public void setRubrosProductosCuentasContables(List<RubroProductoCuentaContable> rubrosProductosCuentasContables) {
        this.rubrosProductosCuentasContables = rubrosProductosCuentasContables;
    }
    
}