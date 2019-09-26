//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.Cliente;
import ar.com.draimo.jitws.model.SucursalCliente;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO sucursal cliente
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface ISucursalClienteDAO extends JpaRepository<SucursalCliente, Integer> {
    
    //Obtiene el ultimo registro
    public SucursalCliente findTopByOrderByIdDesc();
    
    //Obtiene una lista por nombre
    public List<SucursalCliente> findByNombreContaining(String nombre);
    
    //Obtiene una lista por cliente
    public List<SucursalCliente> findByCliente(Optional<Cliente> cliente);
    
    //Obtiene una lista por alias del cliente
    public List<SucursalCliente> findByCliente_AliasContaining(String alias);
    
}