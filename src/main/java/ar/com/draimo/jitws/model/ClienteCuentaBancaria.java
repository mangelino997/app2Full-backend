//Paquete al que pertenece la clase
package ar.com.draimo.jitws.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Clase ClienteCuentaBancaria
 * Define el modelo (columnas) de la base de datos.
 * @author blas
 */

@Entity
@Table(name = "clientecuentabancaria")
public class ClienteCuentaBancaria extends ObjetoGenerico {

    //Referencia a la clase cliente
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idCliente", nullable = false)
    private Cliente cliente;
    
    //Referencia a la clase cuentaBancaria
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idCuentaBancaria", nullable = false)
    private CuentaBancaria cuentaBancaria;
    
    //Getters y Setters de la clase

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public CuentaBancaria getCuentaBancaria() {
        return cuentaBancaria;
    }

    public void setCuentaBancaria(CuentaBancaria cuentaBancaria) {
        this.cuentaBancaria = cuentaBancaria;
    }

}