//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.OrdenVenta;
import ar.com.draimo.jitws.model.OrdenVentaTarifa;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

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
    
    //Obtiene un listado por ordenVenta y escala
    public List<OrdenVentaTarifa> findByOrdenVentaAndTipoTarifa_PorEscala(OrdenVenta ordenVenta, boolean porEscala);
    
    //Obtiene un listado por ordenVenta y escala
    @Query(value = "select * from ordenventatarifa where idOrdenVenta =:idOrdenVenta"
            + " and idTipoTarifa=:idTipoTarifa", nativeQuery = true)
    public OrdenVentaTarifa obtenerPorOrdenVentaYTipoTarifa(int idOrdenVenta, int idTipoTarifa);
    
}