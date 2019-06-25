//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.OrdenVentaEscala;
import java.sql.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import ar.com.draimo.jitws.model.OrdenVenta;
import java.math.BigDecimal;
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
    
    public List<OrdenVentaEscala> findByOrdenVentaTarifa_OrdenVentaAndOrdenVentaTarifa_PreciosDesde(
            OrdenVenta ordenVenta, Date preciosDesde);
    
    public List<OrdenVentaEscala> findByOrdenVentaTarifa_OrdenVenta(OrdenVenta ordenVenta);
    
    //Obtiene un registro por un valor y un idOrdenVenta
    @Query(value = "SELECT e.* FROM ordenventaescala e inner join escalatarifa t"
            + " on t.id=e.idEscalaTarifa inner join ordenventatarifa v on e.idOrdenVentaTarifa ="
            + "v.id where v.idOrdenVenta=:idOrdenVenta and t.valor between"
            + "  :valor and :valorHasta order by t.valor desc limit 1", nativeQuery = true)
    public OrdenVentaEscala obtenerPorOrdenVentaYValorProximo(
            @Param("idOrdenVenta") int idOrdenVenta, @Param("valor") BigDecimal valor,
            @Param("valorHasta") BigDecimal valorHasta);
    
}
