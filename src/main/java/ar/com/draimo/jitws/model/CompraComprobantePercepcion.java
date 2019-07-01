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
 * Clase CompraComprobantePercepcion
 * Define el modelo (columnas) de la base de datos.
 * @author blas
 */

@Entity
@Table(name = "compracomprobantepercepcion")
public class CompraComprobantePercepcion extends ObjetoGenerico {
    
    //Referencia a la clase compraComprobante
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idCompraComprobante", nullable = false)
    private CompraComprobante compraComprobante;
    
    //Referencia a la clase TipoPercepcion
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idTipoPercepcion", nullable = false)
    private TipoPercepcion tipoPercepcion;

    //Define anio
    @Column(name = "anio",length = 4, nullable = false)
    private short anio;
    
    //Referencia a la clase Mes
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idMes", nullable = false)
    private Mes mes;

    //Define el importe
    @Column(name = "importe",nullable = false)
    private BigDecimal importe;
    
    //Define PuntoVenta
    @Column(name = "puntoVenta",length = 5, nullable = true)
    private int puntoVenta;
    
    //Define letra
    @Column(name = "letra",length = 1, nullable = true)
    private String letra;
    
    //Define numero
    @Column(name = "numero",length = 8, nullable = true)
    private int numero;
    
    //Getters y Setters de la clase

    public CompraComprobante getCompraComprobante() {
        return compraComprobante;
    }

    public void setCompraComprobante(CompraComprobante compraComprobante) {
        this.compraComprobante = compraComprobante;
    }

    public TipoPercepcion getTipoPercepcion() {
        return tipoPercepcion;
    }

    public void setTipoPercepcion(TipoPercepcion tipoPercepcion) {
        this.tipoPercepcion = tipoPercepcion;
    }

    public short getAnio() {
        return anio;
    }

    public void setAnio(short anio) {
        this.anio = anio;
    }

    public Mes getMes() {
        return mes;
    }

    public void setMes(Mes mes) {
        this.mes = mes;
    }

    public BigDecimal getImporte() {
        return importe;
    }

    public void setImporte(BigDecimal importe) {
        this.importe = importe;
    }

    public int getPuntoVenta() {
        return puntoVenta;
    }

    public void setPuntoVenta(int puntoVenta) {
        this.puntoVenta = puntoVenta;
    }

    public String getLetra() {
        return letra;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

}
