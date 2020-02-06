//Paquete al que pertenece la clase
package ar.com.draimo.jitws.model;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Clase VentaConfig
 * Define el modelo (columnas) de la base de datos.
 * @author blas
 */

@Entity
@Table(name = "ventaconfig")
public class VentaConfig extends ObjetoGenerico {
    
    //Define aforo
    @Column(name = "aforo", nullable = false)
    private short aforo;
    
    //Define notaImpresionGeneral1
    @Column(name = "notaImpresionGeneral1", length = 200, nullable = true)
    private String notaImpresionGeneral1;
    
    //Define notaImpresionGeneral2
    @Column(name = "notaImpresionGeneral2",length = 200, nullable = true)
    private String notaImpresionGeneral2;
    
    //Define Comision contrareembolso
    @Column(name = "comisionCR", nullable = true)
    private BigDecimal comisionCR;
    
    //Define Seguro
    @Column(name = "seguro", nullable = true)
    private BigDecimal seguro;

    public short getAforo() {
        return aforo;
    }

    public void setAforo(short aforo) {
        this.aforo = aforo;
    }

    public String getNotaImpresionGeneral1() {
        return notaImpresionGeneral1;
    }

    public void setNotaImpresionGeneral1(String notaImpresionGeneral1) {
        this.notaImpresionGeneral1 = notaImpresionGeneral1;
    }

    public String getNotaImpresionGeneral2() {
        return notaImpresionGeneral2;
    }

    public void setNotaImpresionGeneral2(String notaImpresionGeneral2) {
        this.notaImpresionGeneral2 = notaImpresionGeneral2;
    }

    public BigDecimal getComisionCR() {
        return comisionCR;
    }

    public void setComisionCR(BigDecimal comisionCR) {
        this.comisionCR = comisionCR;
    }

    public BigDecimal getSeguro() {
        return seguro;
    }

    public void setSeguro(BigDecimal seguro) {
        this.seguro = seguro;
    }
    
}