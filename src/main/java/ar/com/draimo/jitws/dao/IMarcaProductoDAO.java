//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.MarcaProducto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO MarcaProducto
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IMarcaProductoDAO extends JpaRepository<MarcaProducto, Integer> {
    
    //Obtiene el ultimo registro
    public MarcaProducto findTopByOrderByIdDesc();
    
    //Obtiene la lista de registro ordenada por nombre asc
    public List<MarcaProducto> findAllByOrderByNombreAsc();
    
    //Obtiene una lista por nombre
    public List<MarcaProducto> findByNombreContaining(String nombre);
    
}