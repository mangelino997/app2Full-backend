//Paquete al que pertenece la clase
package ar.com.draimo.jitws.model;

import java.math.BigDecimal;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

/**
 * Clase CobranzaMedioPago
 * Define el modelo (columnas) de la base de datos.
 *
 * @author blas
 */
@Entity
@Table(name = "cobranzamediopago")
public class CobranzaMedioPago extends ObjetoGenerico {

    //Define la referencia a la clase cobranza
    @ManyToOne(cascade = CascadeType.REFRESH)
    @MapsId("idCobranza")
    @JoinColumn(name = "idCobranza", nullable = false)
    private Cobranza cobranza;

//    Define la referencia a la clase CobranzaAnticipo
    @ManyToOne(cascade = CascadeType.REFRESH)
    @MapsId("idCobranza")
    @JoinColumn(name = "idCobranzaAnticipo", nullable = true)
    private CobranzaAnticipo cobranzaAnticipo;

    //Define el importe
    @Column(name = "importeCobranzaAnticipo", nullable = true)
    private BigDecimal importeCobranzaAnticipo;

    //Define la referencia a la clase Efectivo
    @ManyToOne(cascade = CascadeType.REFRESH)
    @MapsId("idEfectivo")
    @JoinColumn(name = "idEfectivo", nullable = true)
    private Efectivo efectivo;

    //Define la referencia a la clase ChequeCartera
    @ManyToOne(cascade = CascadeType.REFRESH)
    @MapsId("idChequeCartera")
    @JoinColumn(name = "idChequeCartera", nullable = true)
    private ChequeCartera chequeCartera;

    //Define la referencia a la clase DocumentoCartera
    @ManyToOne(cascade = CascadeType.REFRESH)
    @MapsId("idDocumentoCartera")
    @JoinColumn(name = "idDocumentoCartera", nullable = true)
    private DocumentoCartera documentoCartera;

    //Define la referencia a la clase MonedaCartera
    @ManyToOne(cascade = CascadeType.REFRESH)
    @MapsId("idMonedaCartera")
    @JoinColumn(name = "idMonedaCartera", nullable = true)
    private MonedaCartera monedaCartera;

    //Define la referencia a la clase LibroBanco
    @ManyToOne(cascade = CascadeType.REFRESH)
//    @MapsId("idLibroBancoTransferencia")
    @JoinColumn(name = "idLibroBanco", nullable = true)
    private LibroBanco libroBanco;

    //Getters y Setters de la clase

    public Cobranza getCobranza() {
        return cobranza;
    }

    public void setCobranza(Cobranza cobranza) {
        this.cobranza = cobranza;
    }

    public CobranzaAnticipo getCobranzaAnticipo() {
        return cobranzaAnticipo;
    }

    public void setCobranzaAnticipo(CobranzaAnticipo cobranzaAnticipo) {
        this.cobranzaAnticipo = cobranzaAnticipo;
    }

    public BigDecimal getImporteCobranzaAnticipo() {
        return importeCobranzaAnticipo;
    }

    public void setImporteCobranzaAnticipo(BigDecimal importeCobranzaAnticipo) {
        this.importeCobranzaAnticipo = importeCobranzaAnticipo;
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

    public LibroBanco getLibroBanco() {
        return libroBanco;
    }

    public void setLibroBanco(LibroBanco libroBanco) {
        this.libroBanco = libroBanco;
    }
    
}
