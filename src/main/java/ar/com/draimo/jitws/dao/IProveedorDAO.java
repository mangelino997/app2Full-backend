//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.Proveedor;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO Proveedor
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IProveedorDAO extends JpaRepository<Proveedor, Integer> {
    
    //Obtiene el siguiente id
    public Proveedor findTopByOrderByIdDesc();
    
    //Obtiene una lista por alias
    public List<Proveedor> findByAliasContaining(String alias);
    
}
