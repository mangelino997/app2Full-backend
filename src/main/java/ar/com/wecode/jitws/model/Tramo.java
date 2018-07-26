//Paquete al que pertenece la clase
package ar.com.wecode.jitws.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Clase Tramo
 * Define el modelo (columnas) de la base de datos.
 * @author blas
 */

@Entity
@Table(name = "tramo")
public class Tramo extends ObjetoGenerico {
    
    //Referencia a la clase OrigenDestino. Define el Origen
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idOrigen", nullable = false)
    private OrigenDestino origen;
    
    //Referencia a la clase OrigenDestino. Define el Destino
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idDestino", nullable = false)
    private OrigenDestino destino;
    
    //Define el kilometro
    @Column(name = "km", nullable = false)
    private short km;
    
    //Define si esta activo
    @Column(name = "estaActivo", nullable = false)
    private boolean estaActivo;
    
    //Define si se excluye la liquidacion del chofer
    @Column(name = "excluirLiqChofer", nullable = false)
    private boolean excluirLiqChofer;
    
    //Define la ruta alternativa
    @Column(name = "rutaAlternativa", nullable = true)
    private String rutaAlternativa;
    
    //Getters y Setters de la clase

    public OrigenDestino getOrigen() {
        return origen;
    }

    public void setOrigen(OrigenDestino origen) {
        this.origen = origen;
    }

    public OrigenDestino getDestino() {
        return destino;
    }

    public void setDestino(OrigenDestino destino) {
        this.destino = destino;
    }

    public short getKm() {
        return km;
    }

    public void setKm(short km) {
        this.km = km;
    }

    public boolean getEstaActivo() {
        return estaActivo;
    }

    public void setEstaActivo(boolean estaActivo) {
        this.estaActivo = estaActivo;
    }

    public boolean getExcluirLiqChofer() {
        return excluirLiqChofer;
    }

    public void setExcluirLiqChofer(boolean excluirLiqChofer) {
        this.excluirLiqChofer = excluirLiqChofer;
    }
        
    public String getRutaAlternativa() {
        return rutaAlternativa;
    }

    public void setRutaAlternativa(String rutaAlternativa) {
        this.rutaAlternativa = rutaAlternativa;
    }
    
}
