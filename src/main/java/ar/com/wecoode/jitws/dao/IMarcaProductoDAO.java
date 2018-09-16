//Paquete al que pertenece la interfaz
package ar.com.wecoode.jitws.dao;

import ar.com.wecoode.jitws.model.MarcaProducto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO Marca de producto
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IMarcaProductoDAO extends JpaRepository<MarcaProducto, Integer> {
    
    //Obtiene el siguiente id
    public MarcaProducto findTopByOrderByIdDesc();
    
    //Obtiene una lista por nombre
    public List<MarcaProducto> findByNombreContaining(String nombre);
    
}
