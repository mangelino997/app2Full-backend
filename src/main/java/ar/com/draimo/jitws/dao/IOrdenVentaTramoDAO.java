//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.OrdenVenta;
import ar.com.draimo.jitws.model.OrdenVentaTarifa;
import ar.com.draimo.jitws.model.OrdenVentaTramo;
import java.sql.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Interfaz DAO OrdenVentaTramo
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IOrdenVentaTramoDAO extends JpaRepository<OrdenVentaTramo, Integer> {
    
    //Obtiene el ultimo registro
    public OrdenVentaTramo findTopByOrderByIdDesc();
    
    //Obtiene un registro por ordenVenta de ordenVentaTarifa y preciosDesde
    public List<OrdenVentaTramo> findByOrdenVentaTarifa_OrdenVentaAndPreciosDesde(
            OrdenVenta ordenVenta, Date preciosDesde);
    
    //Obtiene un listado de preciosDesde por ordenventa
    @Query(value = "SELECT distinct(t.preciosDesde) FROM ordenventatramo t inner join "
            + "ordenventatarifa o on o.id=t.idOrdenVentaTarifa  where o.idOrdenVenta=1"
            + " order by t.preciosDesde asc", nativeQuery = true)
    public List<OrdenVentaTramo> listarPreciosDesdePorOrdenVenta(@Param("idOrdenVenta") int idOrdenVenta);
    
    //Obtiene un listado por la ordenVenta de ordenVentaTarifa
    public List<OrdenVentaTramo> findByOrdenVentaTarifa_OrdenVenta(OrdenVenta ordenVenta);
    
    //Obtiene un listado por ordenVentaTarifa
    public List<OrdenVentaTramo> findByOrdenVentaTarifa(OrdenVentaTarifa ordenVentaTarifa);
    
    //Elimina los registro por ordenVentaTarifa
    public List<OrdenVentaTramo> deleteByOrdenVentaTarifa(OrdenVentaTarifa ordenVentaTarifa);
    
}