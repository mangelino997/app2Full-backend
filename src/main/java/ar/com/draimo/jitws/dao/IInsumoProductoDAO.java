//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.InsumoProducto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO InsumoProducto
 Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IInsumoProductoDAO extends JpaRepository<InsumoProducto, Integer> {
    
    //Obtiene el ultimo registro
    public InsumoProducto findTopByOrderByIdDesc();
    
    //Obtiene una lista por alias
    public List<InsumoProducto> findByAliasContaining(String alias);
    
    //Obtiene una lista por esCombustible de rubro producto
    public List<InsumoProducto> findByRubroProducto_EsCombustibleTrue();
    
    //Obtiene una lista por esInsumo de rubro producto
    public List<InsumoProducto> findByRubroProducto_EsInsumoTrue();
    
}