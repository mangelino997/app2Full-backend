//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.ChoferProveedor;
import ar.com.draimo.jitws.model.Proveedor;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO ChoferProveedor
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IChoferProveedorDAO extends JpaRepository<ChoferProveedor, Integer> {
    
    //Obtiene el siguiente id
    public ChoferProveedor findTopByOrderByIdDesc();
    
    //Obtiene una lista por id proveedor
    public List<ChoferProveedor> findByProveedor(Optional<Proveedor> proveedor);
    
    //Obtiene una lista por alias
    public List<ChoferProveedor> findByAliasContaining(String alias);
    
}