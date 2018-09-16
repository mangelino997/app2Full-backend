//Paquete al que pertenece la interfaz
package ar.com.wecoode.jitws.dao;

import ar.com.wecoode.jitws.model.OrdenVenta;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO OrdenVenta
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IOrdenVentaDAO extends JpaRepository<OrdenVenta, Integer> {
    
    //Obtiene el siguiente id
    public OrdenVenta findTopByOrderByIdDesc();
    
    //Obtiene una lista por nombre
    public List<OrdenVenta> findByNombreContaining(String nombre);
    
}
