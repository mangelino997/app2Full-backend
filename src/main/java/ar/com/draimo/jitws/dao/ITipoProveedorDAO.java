//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.TipoProveedor;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO Tipo de Proveedor
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface ITipoProveedorDAO extends JpaRepository<TipoProveedor, Integer> {
    
    //Obtiene el ultimo registro
    public TipoProveedor findTopByOrderByIdDesc();
    
    //Obtiene una lista por nombre
    public List<TipoProveedor> findByNombreContaining(String nombre);
    
}