//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.ChoferProveedor;
import ar.com.draimo.jitws.model.Proveedor;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO ChoferProveedor
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IChoferProveedorDAO extends JpaRepository<ChoferProveedor, Integer> {
    
    //Obtiene el ultimo registro
    public ChoferProveedor findTopByOrderByIdDesc();
    
    //Obtiene una lista por id proveedor
    public List<ChoferProveedor> findByProveedor(Proveedor proveedor);
    
    //Obtiene una lista por alias
    public List<ChoferProveedor> findByAliasContaining(String alias);
    
    //Obtiene una lista de activos por alias
    public List<ChoferProveedor> findByAliasContainingAndUsuarioBajaIsNull(String alias);
    
    //Obtiene una lista de activos 
    public List<ChoferProveedor> findByUsuarioBajaIsNull();
    
    //Obtiene una lista por alias y proveedor
    public List<ChoferProveedor> findByAliasContainingAndProveedor(String alias, Proveedor proveedor);
    
}