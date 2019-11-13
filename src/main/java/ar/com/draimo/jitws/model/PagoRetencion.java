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
 * Clase pagoretencion
 * Define el modelo (columnas) de la base de datos.
 *
 * @author blas
 */
@Entity
@Table(name = "pagoretencion")
public class PagoRetencion extends ObjetoGenerico {

    //Define la referencia a la clase Pago
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idPago", nullable = false)
    private Pago pago;

    //Define la referencia a la clase tipoRetencion
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idTipoRetencion", nullable = false)
    private TipoRetencion tipoRetencion;

    //Define el anio
    @Column(name = "anio", length = 4, nullable = false)
    private short anio;

    //Define la referencia a la clase mes
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idMes", nullable = false)
    private Mes mes;

    //Define el importe
    @Column(name = "importe", nullable = false)
    private BigDecimal importe;

    //Define el puntoVenta
    @Column(name = "puntoVenta",length = 5, nullable = false)
    private int puntoVenta;

    //Define letra
    @Column(name = "letra",length = 1, nullable = true)
    private String letra;

    //Define el numero
    @Column(name = "numero",length = 8, nullable = false)
    private int numero;

    //Define la referencia a la clase provincia
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idProvincia", nullable = true)
    private Provincia provincia;

    //Getters y Setters de la clase

    public Pago getPago() {
        return pago;
    }

    public void setPago(Pago pago) {
        this.pago = pago;
    }


    public TipoRetencion getTipoRetencion() {
        return tipoRetencion;
    }

    public void setTipoRetencion(TipoRetencion tipoRetencion) {
        this.tipoRetencion = tipoRetencion;
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

    public Provincia getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }

}
