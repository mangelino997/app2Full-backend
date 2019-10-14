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
    
    //Define NotaImpresionComprobanteGral1
    @Column(name = "notaImpresionComprobanteGral1", length = 200, nullable = true)
    private String notaImpresionComprobanteGral1;
    
    //Define NotaImpresionComprobanteGral2
    @Column(name = "notaImpresionComprobanteGral2",length = 200, nullable = true)
    private String notaImpresionComprobanteGral2;
    
    //Define Comision contrareembolso
    @Column(name = "comisionCR", nullable = true)
    private BigDecimal comisionCR;
    
    //Getters y Setters de la clase
    public short getAforo() {
        return aforo;
    }

    public void setAforo(short aforo) {
        this.aforo = aforo;
    }

    public String getNotaImpresionComprobanteGral1() {
        return notaImpresionComprobanteGral1;
    }

    public void setNotaImpresionComprobanteGral1(String notaImpresionComprobanteGral1) {
        this.notaImpresionComprobanteGral1 = notaImpresionComprobanteGral1;
    }

    public String getNotaImpresionComprobanteGral2() {
        return notaImpresionComprobanteGral2;
    }

    public void setNotaImpresionComprobanteGral2(String notaImpresionComprobanteGral2) {
        this.notaImpresionComprobanteGral2 = notaImpresionComprobanteGral2;
    }

    public BigDecimal getComisionCR() {
        return comisionCR;
    }

    public void setComisionCR(BigDecimal comisionCR) {
        this.comisionCR = comisionCR;
    }

}