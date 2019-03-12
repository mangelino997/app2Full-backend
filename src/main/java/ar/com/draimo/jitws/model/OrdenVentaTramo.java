//Paquete al que pertenece la clase
package ar.com.draimo.jitws.model;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.math.BigDecimal;
import java.sql.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Clase OrdenVentaTramo
 * Define el modelo (columnas) de la base de datos.
 * @author blas
 */

@Entity
@Table(name = "ordenventatramo")
@JsonFilter(value = "filtroOrdenVentaTramo")
public class OrdenVentaTramo extends ObjetoGenerico {

    //Referencia a la clase OrdenVenta
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idOrdenVenta", nullable = false)
    private OrdenVenta ordenVenta;
    
    //Referencia a la clase Tramo
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idTramo", nullable = false)
    private Tramo tramo;
    
    //Define el km pactado
    @Column(name = "kmPactado", nullable = true)
    private short kmPactado;
    
    //Define el importe fijo seco
    @Column(name = "importeFijoSeco", nullable = true)
    private BigDecimal importeFijoSeco;
    
    //Define el importe fijo ref
    @Column(name = "importeFijoRef", nullable = true)
    private BigDecimal importeFijoRef;
    
    //Define el precio unitario seco
    @Column(name = "precioUnitarioSeco", nullable = true)
    private BigDecimal precioUnitarioSeco;
    
    //Define el precio unitario ref
    @Column(name = "precioUnitarioRef", nullable = true)
    private BigDecimal precioUnitarioRef;
    
    //Define la fecha desde que estan los precios
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "ART")
    @Column(name = "preciosDesde", nullable = false)
    private Date preciosDesde;

    //Getters y Setters de la clase

    public OrdenVenta getOrdenVenta() {
        return ordenVenta;
    }

    public void setOrdenVenta(OrdenVenta ordenVenta) {
        this.ordenVenta = ordenVenta;
    }

    public Tramo getTramo() {
        return tramo;
    }

    public void setTramo(Tramo tramo) {
        this.tramo = tramo;
    }

    public short getKmPactado() {
        return kmPactado;
    }

    public void setKmPactado(short kmPactado) {
        this.kmPactado = kmPactado;
    }

    public BigDecimal getImporteFijoSeco() {
        return importeFijoSeco;
    }

    public void setImporteFijoSeco(BigDecimal importeFijoSeco) {
        this.importeFijoSeco = importeFijoSeco;
    }

    public BigDecimal getImporteFijoRef() {
        return importeFijoRef;
    }

    public void setImporteFijoRef(BigDecimal importeFijoRef) {
        this.importeFijoRef = importeFijoRef;
    }

    public BigDecimal getPrecioUnitarioSeco() {
        return precioUnitarioSeco;
    }

    public void setPrecioUnitarioSeco(BigDecimal precioUnitarioSeco) {
        this.precioUnitarioSeco = precioUnitarioSeco;
    }

    public BigDecimal getPrecioUnitarioRef() {
        return precioUnitarioRef;
    }

    public void setPrecioUnitarioRef(BigDecimal precioUnitarioRef) {
        this.precioUnitarioRef = precioUnitarioRef;
    }

    public Date getPreciosDesde() {
        return preciosDesde;
    }

    public void setPreciosDesde(Date preciosDesde) {
        this.preciosDesde = preciosDesde;
    }
    
}