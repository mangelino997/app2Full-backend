//Paquete al que pertenece la interfaz
package ar.com.wecoode.jitws.dao;

import ar.com.wecoode.jitws.model.RubroProducto;
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
    
    //Obtiene una lista por nombre
    public List<RubroProducto> findByNombreContaining(String nombre);
    
}
