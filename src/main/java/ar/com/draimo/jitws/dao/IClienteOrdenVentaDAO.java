//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.Cliente;
import ar.com.draimo.jitws.model.ClienteOrdenVenta;
import ar.com.draimo.jitws.model.OrdenVenta;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO Compañia de seguro poliza
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IClienteOrdenVentaDAO extends JpaRepository<ClienteOrdenVenta, Integer> {
    
    //Obtiene el siguiente id
    public ClienteOrdenVenta findTopByOrderByIdDesc();
    
    //Obtiene una lista por ordenVenta
    public List<ClienteOrdenVenta> findByOrdenVenta(OrdenVenta ordenVenta);
    
    //Obtiene una lista por cliente
    public List<ClienteOrdenVenta> findByCliente(Cliente cliente);
    
    //Obtiene por compania de cliente y ordenVenta
    public List<ClienteOrdenVenta> findByClienteAndOrdenVenta(Cliente cliente, OrdenVenta ordenVenta);
    
}
