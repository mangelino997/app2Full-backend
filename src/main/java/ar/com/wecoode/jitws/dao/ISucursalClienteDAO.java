//Paquete al que pertenece la interfaz
package ar.com.wecoode.jitws.dao;

import ar.com.wecoode.jitws.model.Cliente;
import ar.com.wecoode.jitws.model.SucursalCliente;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO Area
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface ISucursalClienteDAO extends JpaRepository<SucursalCliente, Integer> {
    
    //Obtiene el siguiente id
    public SucursalCliente findTopByOrderByIdDesc();
    
    //Obtiene una lista por nombre
    public List<SucursalCliente> findByNombreContaining(String nombre);
    
    //Obtiene una lista por cliente
    public List<SucursalCliente> findByCliente(Optional<Cliente> cliente);
    
}
