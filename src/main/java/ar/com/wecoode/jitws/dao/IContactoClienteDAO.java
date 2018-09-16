//Paquete al que pertenece la interfaz
package ar.com.wecoode.jitws.dao;

import ar.com.wecoode.jitws.model.Cliente;
import ar.com.wecoode.jitws.model.ContactoCliente;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO ContactoBanco
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IContactoClienteDAO extends JpaRepository<ContactoCliente, Integer> {
    
    //Obtiene el siguiente id
    public ContactoCliente findTopByOrderByIdDesc();
    
    //Obtiene una lista por nombre
    public List<ContactoCliente> findByNombreContaining(String nombre);
    
    //Obtiene por cliente
    public ContactoCliente findByCliente(Optional<Cliente> cliente);
    
}
