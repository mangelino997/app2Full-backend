//Paquete al que pertenece la clase
package ar.com.draimo.jitws.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Clase AfipComprobante
 * Define el modelo (columnas) de la base de datos.
 * @author blas
 */

@Entity
@Table(name = "afipcomprobante")
public class AfipComprobante extends ObjetoGenerico {

    //Referencia a la clase TipoComprobante
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idTipoComprobante", nullable = false)
    private TipoComprobante tipoComprobante;
    
    //Define la letra
    @Column(name = "letra",length = 1, nullable = false)
    private String letra;
    
    //Define el codigo afip
    @Column(name = "codigoAfip",length = 3, nullable = false, unique = true)
    private String codigoAfip;

    //Getters y Setters de la clase

    public TipoComprobante getTipoComprobante() {
        return tipoComprobante;
    }

    public void setTipoComprobante(TipoComprobante tipoComprobante) {
        this.tipoComprobante = tipoComprobante;
    }

    public String getLetra() {
        return letra;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }

    public String getCodigoAfip() {
        return codigoAfip;
    }

    public void setCodigoAfip(String codigoAfip) {
        this.codigoAfip = codigoAfip;
    }
    
}