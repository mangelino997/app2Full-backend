//Paquete al que pertenece la clase
package ar.com.draimo.jitws.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;
import java.sql.Date;
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
@Table(name = "monedacotizacion")
public class MonedaCotizacion extends ObjetoGenerico {
    
    //Instancia de la clase moneda
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idMoneda", nullable = false)
    private Moneda moneda;
    
    //Define la fecha
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "ART")
    @Column(name = "fecha", nullable = false)
    private Date fecha;
    
    //Define el valor
    @Column(name = "valor", nullable = false)
    private BigDecimal valor;
     
    //Getters and setters de la clase
    
    public Moneda getMoneda() {
        return moneda;    
    }
   
    public void setMoneda(Moneda moneda) {
        this.moneda = moneda;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
    
}
