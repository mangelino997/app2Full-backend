//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.ContactoProveedor;
import ar.com.draimo.jitws.model.Proveedor;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO ContactoProveedor
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IContactoProveedorDAO extends JpaRepository<ContactoProveedor, Integer> {
    
    //Obtiene el ultimo registro
    public ContactoProveedor findTopByOrderByIdDesc();
    
    //Obtiene una lista por nombre
    public List<ContactoProveedor> findByNombreContaining(String nombre);
    
    //Obtiene una lista por proveedor
    public List<ContactoProveedor> findByProveedor(Optional<Proveedor> proveedor);
    
}