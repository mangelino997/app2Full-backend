//Paquete al que pertenece la clase
package ar.com.wecoode.jitws.model;

import java.math.BigDecimal;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Clase Insumo
 * Define el modelo (columnas) de la base de datos.
 * @author blas
 */

@Entity
@Table(name = "insumo")
public class Insumo extends ObjetoGenerico {

    //Define el nombre
    @Column(name = "nombre", nullable = false)
    private String nombre;
    
    //Referenica a la clase Unidad Medida
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idUnidadMedida", nullable = false)
    private UnidadMedida unidadMedida;
    
    //Define el precio unitario
    @Column(name = "precioUnitario", nullable = false)
    private BigDecimal precioUnitario;
    
    //Define el coeficiente ITC
    @Column(name = "coeficienteITC", nullable = true)
    private BigDecimal coeficienteITC;
    
    //Define si es combustible
    @Column(name = "esCombustible", nullable = false)
    private boolean esCombustible;
    
    //Referencia a la clase Usuario
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idUsuario", nullable = false)
    private Usuario usuario;

    //Getters y Setters de la clase

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public UnidadMedida getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(UnidadMedida unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public BigDecimal getCoeficienteITC() {
        return coeficienteITC;
    }

    public void setCoeficienteITC(BigDecimal coeficienteITC) {
        this.coeficienteITC = coeficienteITC;
    }

    public boolean getEsCombustible() {
        return esCombustible;
    }

    public void setEsCombustible(boolean esCombustible) {
        this.esCombustible = esCombustible;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
}