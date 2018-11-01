//Paquete al que pertenece la clase
package ar.com.draimo.jitws.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Clase Viaje Propio Tramo Cliente
 * Define el modelo (columnas) de la base de datos.
 * @author blas
 */

@Entity
@Table(name = "viajepropiotramocliente")
public class ViajePropioTramoCliente extends ObjetoGenerico {

    //Referencia a la clase Cliente
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idClienteDador", nullable = false)
    private Cliente clienteDador;
    
    //Referencia a la clase Cliente
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idClienteDestinatario", nullable = false)
    private Cliente clienteDestinatario;
    
    //Referencia a la clase Viaje Propio Tramo
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idViajePropioTramo", nullable = false)
    private ViajePropioTramo viajePropioTramo;

    //Getters y Setters de la clase

    public Cliente getClienteDador() {
        return clienteDador;
    }

    public void setClienteDador(Cliente clienteDador) {
        this.clienteDador = clienteDador;
    }

    public Cliente getClienteDestinatario() {
        return clienteDestinatario;
    }

    public void setClienteDestinatario(Cliente clienteDestinatario) {
        this.clienteDestinatario = clienteDestinatario;
    }

    public ViajePropioTramo getViajePropioTramo() {
        return viajePropioTramo;
    }

    public void setViajePropioTramo(ViajePropioTramo viajePropioTramo) {
        this.viajePropioTramo = viajePropioTramo;
    }
    
}