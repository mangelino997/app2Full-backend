//Paquete al que pertenece la interfaz
package ar.com.wecoode.jitws.dao;

import ar.com.wecoode.jitws.model.CondicionVenta;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO CondicionVenta
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface ICondicionVentaDAO extends JpaRepository<CondicionVenta, Integer> {
    
    //Obtiene el siguiente id
    public CondicionVenta findTopByOrderByIdDesc();
    
    //Obtiene una lista por nombre
    public List<CondicionVenta> findByNombreContaining(String nombre);
    
}
