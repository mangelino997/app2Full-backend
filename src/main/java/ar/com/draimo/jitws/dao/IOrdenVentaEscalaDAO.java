//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.OrdenVentaEscala;
import java.sql.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import ar.com.draimo.jitws.model.OrdenVenta;
import ar.com.draimo.jitws.model.OrdenVentaTarifa;
import java.math.BigDecimal;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


/**
 * Interfaz DAO OrdenVentaEscala
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IOrdenVentaEscalaDAO extends JpaRepository<OrdenVentaEscala, Integer> {
    
    //Obtiene el ultimo registro
    public OrdenVentaEscala findTopByOrderByIdDesc();
    
    //Obtiene un listado por los precios desde y orden venta de orden venta tarifa
    public List<OrdenVentaEscala> findByOrdenVentaTarifa_OrdenVentaAndPreciosDesde(
            OrdenVenta ordenVenta, Date preciosDesde);
    
    //Obtiene un listado por orden venta de orvenVentaTarifa
    public List<OrdenVentaEscala> findByOrdenVentaTarifa_OrdenVenta(OrdenVenta ordenVenta);
    
    //Obtiene un listado por orvenVentaTarifa ordenado por el valor de escalaTarifa
    @Query(value = "SELECT e.* FROM ordenventaescala e INNER JOIN ordenventatarifa t  ON "
            + "e.idOrdenVentaTarifa=t.id  INNER JOIN escalatarifa s ON e.idEscalaTarifa = "
            + "s.id WHERE t.idOrdenVenta=:idOrdenVenta AND t.idTipoTarifa="
            + ":idTipoTarifa ORDER BY s.valor ASC", nativeQuery = true)
    public List<OrdenVentaEscala> listarPorOrdenVentaTarifaOrdenadoPorValor(
            @Param("idOrdenVenta") int idOrdenVenta, @Param("idTipoTarifa") int idTipoTarifa);
    
    //elimina un listado por ordenVentaTarifa
    public List<OrdenVentaEscala> deleteByOrdenVentaTarifa(OrdenVentaTarifa ordenVentaTarifa);
    
    //Obtiene un registro por un valor y una idOrdenVenta
    @Query(value = "SELECT e.* FROM ordenventaescala e inner join escalatarifa t"
            + " on t.id=e.idEscalaTarifa inner join ordenventatarifa v on e.idOrdenVentaTarifa ="
            + "v.id where v.idOrdenVenta=:idOrdenVenta and t.valor between"
            + "  :valor and :valorHasta order by t.valor desc limit 1", nativeQuery = true)
    public OrdenVentaEscala  obtenerPorOrdenVentaYValorProximo(
            @Param("idOrdenVenta") int idOrdenVenta, @Param("valor") BigDecimal valor,
            @Param("valorHasta") BigDecimal valorHasta);
    
    //Obtiene un listado por ordenVentaTarifa y precios desde
    @Query(value = "SELECT * FROM ordenventaescala where idOrdenVentaTarifa=:idOrdenVentaTarifa "
            + "and preciosDesde=:preciosDesde", nativeQuery = true)
    public List<OrdenVentaEscala> listarPorOrdenVentaTarifaYPreciosDesde(@Param("idOrdenVentaTarifa") int idOrdenVentaTarifa,
            @Param("preciosDesde") Date preciosDesde);
    
    //Obtiene un listado de fechas por ordenVentaTarifa
    @Query(value = "SELECT preciosDesde FROM ordenventaescala where idOrdenVentaTarifa=:idOrdenVentaTarifa "
            + "group by preciosDesde", nativeQuery = true)
    public List<Date> listarPreciosDesdePorOrdenVentaTarifa(@Param("idOrdenVentaTarifa") int idOrdenVentaTarifa);
    
}