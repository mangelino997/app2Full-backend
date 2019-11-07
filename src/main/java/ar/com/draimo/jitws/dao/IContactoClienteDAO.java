//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.Cliente;
import ar.com.draimo.jitws.model.ContactoCliente;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO ContactoCliente
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IContactoClienteDAO extends JpaRepository<ContactoCliente, Integer> {
    
    //Obtiene el ultimo registro
    public ContactoCliente findTopByOrderByIdDesc();
    
    //Obtiene una lista por nombre
    public List<ContactoCliente> findByNombreContaining(String nombre);
    
    //Obtiene por cliente
    public List<ContactoCliente> findByCliente(Optional<Cliente> cliente);
    
    //Elimina los contactos por cliente
    public void deleteByCliente(Cliente cliente);
    
}