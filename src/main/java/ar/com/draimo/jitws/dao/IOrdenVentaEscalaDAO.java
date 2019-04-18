//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.OrdenVenta;
import ar.com.draimo.jitws.model.OrdenVentaEscala;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Interfaz DAO OrdenVentaEscala
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IOrdenVentaEscalaDAO extends JpaRepository<OrdenVentaEscala, Integer> {
    
    //Obtiene el siguiente id
    public OrdenVentaEscala findTopByOrderByIdDesc();
    
    //Obtiene un listado por ordenVenta
    public List<OrdenVentaEscala> findByOrdenVenta(Optional<OrdenVenta> ordenVenta);
    
    //Obtiene una lista por ordenventa ordenada por escala tarifa
    @Query(value = "SELECT * FROM ordenventaescala o inner join escalatarifa e "
            + "on o.idEscalaTarifa=e.id where o.idOrdenVenta=:idOrdenVenta order by e.valor", nativeQuery = true)
    public List<OrdenVentaEscala> listarPorOrdenVentaOrdenadaPorEscalaTarifa(@Param("idOrdenVenta") int idOrdenVenta);
    
    //Obtiene una lista por orden de venta y precios desde
    public List<OrdenVentaEscala> findByOrdenVentaAndPreciosDesde(OrdenVenta ordenVenta, Date preciosDesde);
    
    //Obtiene la lista de fecha por orden de venta
    @Query(value = "SELECT distinct(preciosDesde) FROM ordenventaescala where idOrdenVenta=:idOrdenVenta", nativeQuery = true)
    public List<Date> listarFechasPorOrdenVenta(@Param("idOrdenVenta") int idOrdenVenta);
    
    //Obtiene un registro por un valor y un idOrdenVenta
    @Query(value = "SELECT e.* FROM ordenventaescala e inner join escalatarifa t"
            + " on t.id=e.idEscalaTarifa where e.idOrdenVenta=:idOrdenVenta and t.valor between"
            + "  :valor and :valorHasta order by t.valor desc limit 1", nativeQuery = true)
    public OrdenVentaEscala obtenerPorOrdenVentaYValorProximo(
            @Param("idOrdenVenta") int idOrdenVenta, @Param("valor") BigDecimal valor,
            @Param("valorHasta") BigDecimal valorHasta);
    
}
