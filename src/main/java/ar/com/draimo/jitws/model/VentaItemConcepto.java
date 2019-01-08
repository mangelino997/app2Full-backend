//Paquete al que pertenece la clase
package ar.com.draimo.jitws.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Clase Venta Item Concepto
 * Define el modelo (columnas) de la base de datos
 * @author blas
 */

@Entity
@Table(name = "ventaitemconcepto")
public class VentaItemConcepto extends ObjetoGenerico {
    
    //Define el nombre
    @Column(name = "nombre", length = 45, nullable = false)
    private String nombre;
    
    //Define si esta habilitado
    @Column(name = "estaHabilitado", nullable = false)
    private boolean estaHabilitado;
    
    //Referencia a la clase Tipo Comprobante
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idTipoComprobante", nullable = false)
    private TipoComprobante tipoComprobante;
    
    //Define si es cheque rechazado
    @Column(name = "esChequeRechazado", nullable = false)
    private boolean esChequeRechazado;
    
    //Getters y Setters de la clase

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean getEstaHabilitado() {
        return estaHabilitado;
    }

    public void setEstaHabilitado(boolean estaHabilitado) {
        this.estaHabilitado = estaHabilitado;
    }

    public TipoComprobante getTipoComprobante() {
        return tipoComprobante;
    }

    public void setTipoComprobante(TipoComprobante tipoComprobante) {
        this.tipoComprobante = tipoComprobante;
    }

    public boolean getEsChequeRechazado() {
        return esChequeRechazado;
    }

    public void setEsChequeRechazado(boolean esChequeRechazado) {
        this.esChequeRechazado = esChequeRechazado;
    }
    
}