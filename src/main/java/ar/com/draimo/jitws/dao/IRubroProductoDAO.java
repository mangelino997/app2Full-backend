//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.RubroProducto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO Rubro producto
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IRubroProductoDAO extends JpaRepository<RubroProducto, Integer> {
    
    //Obtiene el siguiente id
    public RubroProducto findTopByOrderByIdDesc();
    
    //Obtiene la lista de registros ordenada por nombre desc
    public List<RubroProducto> findAllByOrderByNombreAsc();
    
    //Obtiene una lista por nombre
    public List<RubroProducto> findByNombreContaining(String nombre);
    
}
