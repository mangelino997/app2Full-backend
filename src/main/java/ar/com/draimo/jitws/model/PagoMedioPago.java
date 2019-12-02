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
 * Clase PagoMedioPago
 * Define el modelo (columnas) de la base de datos.
 *
 * @author blas
 */
@Entity
@Table(name = "pagomediopago")
public class PagoMedioPago extends ObjetoGenerico {

    //Define la referencia a la clase Pago
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idPago", nullable = false)
    private Pago pago;

    //Define la referencia a la clase PagoAnticipo
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idPagoAnticipo", nullable = true)
    private PagoAnticipo pagoAnticipo;

    //Define el importe de Pago anticipo
    @Column(name = "importePagoAnticipo", nullable = true)
    private BigDecimal importePagoAnticipo;

    //Define la referencia a la clase Efectivo
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idEfectivo", nullable = true)
    private Efectivo efectivo;

    //Define la referencia a la clase ChequeCartera
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idChequeCartera", nullable = true)
    private ChequeCartera chequeCartera;

    //Define la referencia a la clase DocumentoCartera
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idDocumentoCartera", nullable = true)
    private DocumentoCartera documentoCartera;

    //Define la referencia a la clase MonedaCartera
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idMonedaCartera", nullable = true)
    private MonedaCartera monedaCartera;

    //Define la referencia a la clase ChequeraItem
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idChequeraItem", nullable = true)
    private ChequeraItem chequeraItem;

    //Define la referencia a la clase LibroBanco
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idLibroBanco", nullable = true)
    private LibroBanco libroBanco;

    //Getters y Setters de la clase

    public Pago getPago() {
        return pago;
    }

    public void setPago(Pago pago) {
        this.pago = pago;
    }

    public PagoAnticipo getPagoAnticipo() {
        return pagoAnticipo;
    }

    public void setPagoAnticipo(PagoAnticipo pagoAnticipo) {
        this.pagoAnticipo = pagoAnticipo;
    }

    public BigDecimal getImportePagoAnticipo() {
        return importePagoAnticipo;
    }

    public void setImportePagoAnticipo(BigDecimal importePagoAnticipo) {
        this.importePagoAnticipo = importePagoAnticipo;
    }
    
    public Efectivo getEfectivo() {
        return efectivo;
    }

    public void setEfectivo(Efectivo efectivo) {
        this.efectivo = efectivo;
    }

    public ChequeCartera getChequeCartera() {
        return chequeCartera;
    }

    public void setChequeCartera(ChequeCartera chequeCartera) {
        this.chequeCartera = chequeCartera;
    }

    public DocumentoCartera getDocumentoCartera() {
        return documentoCartera;
    }

    public void setDocumentoCartera(DocumentoCartera documentoCartera) {
        this.documentoCartera = documentoCartera;
    }

    public MonedaCartera getMonedaCartera() {
        return monedaCartera;
    }

    public void setMonedaCartera(MonedaCartera monedaCartera) {
        this.monedaCartera = monedaCartera;
    }

    public ChequeraItem getChequeraItem() {
        return chequeraItem;
    }

    public void setChequeraItem(ChequeraItem chequeraItem) {
        this.chequeraItem = chequeraItem;
    }

    public LibroBanco getLibroBanco() {
        return libroBanco;
    }

    public void setLibroBanco(LibroBanco libroBanco) {
        this.libroBanco = libroBanco;
    }
    
}
