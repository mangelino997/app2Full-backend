package ar.com.draimo.jitws.dto;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * Define ChequeCarteraFiltroDTO
 * @author blas
 */
public class ChequeCarteraFiltroDTO {
    
    //Define fecha pago desde
    private Date fechaPagoDesde;
    
    //Define fecha pago hasta
    private Date fechaPagoHasta;
    
    //Define importe desde
    private BigDecimal importeDesde;
    
    //Define importe hasta
    private BigDecimal importeHasta;
    
    //Define numero de cheque
    private String numero;
    
    //Define si es cheque electronico
    private boolean eCheq;

    public Date getFechaPagoDesde() {
        return fechaPagoDesde;
    }

    public void setFechaPagoDesde(Date fechaPagoDesde) {
        this.fechaPagoDesde = fechaPagoDesde;
    }

    public Date getFechaPagoHasta() {
        return fechaPagoHasta;
    }

    public void setFechaPagoHasta(Date fechaPagoHasta) {
        this.fechaPagoHasta = fechaPagoHasta;
    }

    public BigDecimal getImporteDesde() {
        return importeDesde;
    }

    public void setImporteDesde(BigDecimal importeDesde) {
        this.importeDesde = importeDesde;
    }

    public BigDecimal getImporteHasta() {
        return importeHasta;
    }

    public void setImporteHasta(BigDecimal importeHasta) {
        this.importeHasta = importeHasta;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public boolean iseCheq() {
        return eCheq;
    }

    public void seteCheq(boolean eCheq) {
        this.eCheq = eCheq;
    }
    
}