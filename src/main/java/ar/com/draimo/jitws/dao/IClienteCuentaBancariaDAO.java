//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.Cliente;
import ar.com.draimo.jitws.model.ClienteCuentaBancaria;
import ar.com.draimo.jitws.model.CuentaBancaria;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO ClientecuentaBancaria
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IClienteCuentaBancariaDAO extends JpaRepository<ClienteCuentaBancaria, Integer> {
    
    //Obtiene el siguiente id
    public ClienteCuentaBancaria findTopByOrderByIdDesc();
    
    //Obtiene una lista por ordenVenta
    public List<ClienteCuentaBancaria> findByCuentaBancaria(CuentaBancaria cuentaBancaria);
    
    //Obtiene una lista por cliente
    public List<ClienteCuentaBancaria> findByCliente(Cliente cliente);
    
}
