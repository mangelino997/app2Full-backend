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
    
    //Obtiene el ultimo registro
    public Cliente findTopByOrderByIdDesc();
    
    //Obtiene una lista por alias
    public List<Cliente> findByAliasContaining(String alias);
    
    //Obtiene una lista de clientes exceptuando el id que se pasa para parametro
    public List<Cliente> findByIdNot(int id);
    
    //Obtiene una lista por alias exceptuando el id que se pasa para parametro
    public List<Cliente> findByAliasContainingAndIdNot(String alias, int id);
    
}