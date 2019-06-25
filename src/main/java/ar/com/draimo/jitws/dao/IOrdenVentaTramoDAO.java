//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.OrdenVenta;
import ar.com.draimo.jitws.model.OrdenVentaTramo;
import java.sql.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO OrdenVentaTramo
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IOrdenVentaTramoDAO extends JpaRepository<OrdenVentaTramo, Integer> {
    
    //Obtiene el siguiente id
    public OrdenVentaTramo findTopByOrderByIdDesc();
    
    public List<OrdenVentaTramo> findByOrdenVentaTarifa_OrdenVentaAndOrdenVentaTarifa_PreciosDesde(
            OrdenVenta ordenVenta, Date preciosDesde);
    
    public List<OrdenVentaTramo> findByOrdenVentaTarifa_OrdenVenta(OrdenVenta ordenVenta);
    
}
