//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.CondicionVenta;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO CondicionVenta
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface ICondicionVentaDAO extends JpaRepository<CondicionVenta, Integer> {
    
    //Obtiene el ultimo registro
    public CondicionVenta findTopByOrderByIdDesc();
    
    //Obtiene una lista por nombre
    public List<CondicionVenta> findByNombreContaining(String nombre);
    
}