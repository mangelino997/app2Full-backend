//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.Cliente;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO Cliente
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IClienteDAO extends JpaRepository<Cliente, Integer> {
    
    //Obtiene el siguiente id
    public Cliente findTopByOrderByIdDesc();
    
    //Obtiene una lista por alias
    public List<Cliente> findByAliasContaining(String alias);
    
}
