//Paquete al que pertenece la clase
package ar.com.draimo.jitws.model;

import java.math.BigDecimal;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Clase Banco
 * Define el modelo (columnas) de la base de datos.
 * @author blas
 */

@Entity
@Table(name = "basicocategoria")
public class BasicoCategoria extends ObjetoGenerico {

    //Define el anio
    @Column(name = "anio", nullable = false)
    private short anio;
    
    //Define el mes
    @Column(name = "mes", nullable = false)
    private short nombre;
    
    //Define el basico
    @Column(name = "basico", nullable = false)
    private BigDecimal basico;
    
    //Referencia a categoria
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idCategoria", nullable = false)
    private Categoria categoria;
    
    //Getters y Setters de la clase

    public short getAnio() {
        return anio;
    }

    public void setAnio(short anio) {
        this.anio = anio;
    }

    public short getNombre() {
        return nombre;
    }

    public void setNombre(short nombre) {
        this.nombre = nombre;
    }

    public BigDecimal getBasico() {
        return basico;
    }

    public void setBasico(BigDecimal basico) {
        this.basico = basico;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    
}
