//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.OrdenVenta;
import ar.com.draimo.jitws.model.OrdenVentaTarifa;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO OrdenVentaTarifa
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IOrdenVentaTarifaDAO extends JpaRepository<OrdenVentaTarifa, Integer> {
    
    //Obtiene el ultimo registro
    public OrdenVentaTarifa findTopByOrderByIdDesc();
    
    //Obtiene un listado por ordenVenta
    public List<OrdenVentaTarifa> findByOrdenVenta(OrdenVenta ordenVenta);
    
}