//Paquete al que pertenece la clase
package ar.com.draimo.jitws.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Clase Venta Concepto.
 * Define el modelo (columnas) de la base de datos.
 * @author blas
 */

@Entity
@Table(name = "ventaconcepto")
public class VentaConcepto extends ObjetoGenerico {
    
    //Define el nombre
    @Column(name = "nombre", length = 45, nullable = false)
    private String nombre;
    
    //Referencia a la clase Tipo Comprobante
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idTipoComprobante", nullable = false)
    private TipoComprobante tipoComprobante;
    
    //Define es Contra Reembolso
    @Column(name = "esContraReembolso", nullable = false)
    private boolean esContraReembolso;
    
    //Referencia a la clase Afip Concepto
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idAfipConcepto", nullable = false)
    private AfipConcepto afipConcepto;
    
    //Getters y Setters de la clase

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public TipoComprobante getTipoComprobante() {
        return tipoComprobante;
    }

    public void setTipoComprobante(TipoComprobante tipoComprobante) {
        this.tipoComprobante = tipoComprobante;
    }

    public boolean isEsContraReembolso() {
        return esContraReembolso;
    }

    public void setEsContraReembolso(boolean esContraReembolso) {
        this.esContraReembolso = esContraReembolso;
    }

    public AfipConcepto getAfipConcepto() {
        return afipConcepto;
    }

    public void setAfipConcepto(AfipConcepto afipConcepto) {
        this.afipConcepto = afipConcepto;
    }
    
}
