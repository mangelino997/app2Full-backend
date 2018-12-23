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
    
    //Obtiene el siguiente id
    public InsumoProducto findTopByOrderByIdDesc();
    
    //Obtiene una lista por nombre
    public List<InsumoProducto> findByNombreContaining(String nombre);
    
}
