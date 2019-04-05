//Paquete al que pertenece la clase
package ar.com.draimo.jitws.model;

import com.fasterxml.jackson.annotation.JsonFilter;
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
 * Clase OrdenVentaEscala
 * Define el modelo (columnas) de la base de datos.
 * @author blas
 */

@Entity
@Table(name = "ordenventaescala")
@JsonFilter(value = "filtroOrdenVentaEscala")
public class OrdenVentaEscala extends ObjetoGenerico {

    //Referencia a la clase OrdenVenta
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idOrdenVenta", nullable = false)
    private OrdenVenta ordenVenta;
    
    //Referencia a la clase EscalaTarifa
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idEscalaTarifa", nullable = false)
    private EscalaTarifa escalaTarifa;
    
    //Define el importe fijo
    @Column(name = "importeFijo")
    private BigDecimal importeFijo;
    
    //Define el precio unitario
    @Column(name = "precioUnitario")
    private BigDecimal precioUnitario;
    
    //Define el porcentaje
    @Column(name = "porcentaje")
    private BigDecimal porcentaje;
    
    //Define el minimo
    @Column(name = "minimo")
    private BigDecimal minimo;
    
    //Define la fecha desde que estan los precios
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "America/Argentina/Cordoba")
    @Column(name = "preciosDesde", nullable = false)
    private Date preciosDesde;
    
    //Getters y Setters de la clase

    public OrdenVenta getOrdenVenta() {
        return ordenVenta;
    }

    public void setOrdenVenta(OrdenVenta ordenVenta) {
        this.ordenVenta = ordenVenta;
    }

    public EscalaTarifa getEscalaTarifa() {
        return escalaTarifa;
    }

    public void setEscalaTarifa(EscalaTarifa escalaTarifa) {
        this.escalaTarifa = escalaTarifa;
    }

    public BigDecimal getImporteFijo() {
        return importeFijo;
    }

    public void setImporteFijo(BigDecimal importeFijo) {
        this.importeFijo = importeFijo;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public BigDecimal getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(BigDecimal porcentaje) {
        this.porcentaje = porcentaje;
    }

    public BigDecimal getMinimo() {
        return minimo;
    }

    public void setMinimo(BigDecimal minimo) {
        this.minimo = minimo;
    }

    public Date getPreciosDesde() {
        return preciosDesde;
    }

    public void setPreciosDesde(Date preciosDesde) {
        this.preciosDesde = preciosDesde;
    }
    
}