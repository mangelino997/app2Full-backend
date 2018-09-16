//Paquete al que pertenece la interfaz
package ar.com.wecoode.jitws.dao;

import ar.com.wecoode.jitws.model.ContactoProveedor;
import ar.com.wecoode.jitws.model.Proveedor;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO ContactoProveedor
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IContactoProveedorDAO extends JpaRepository<ContactoProveedor, Integer> {
    
    //Obtiene el siguiente id
    public ContactoProveedor findTopByOrderByIdDesc();
    
    //Obtiene una lista por nombre
    public List<ContactoProveedor> findByNombreContaining(String nombre);
    
    //Obtiene por proveedor
    public ContactoProveedor findByProveedor(Optional<Proveedor> proveedor);
    
}
