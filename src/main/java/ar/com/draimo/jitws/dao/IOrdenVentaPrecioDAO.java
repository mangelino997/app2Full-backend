//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.OrdenVenta;
import ar.com.draimo.jitws.model.OrdenVentaPrecio;
import java.sql.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO OrdenVentaEscala
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IOrdenVentaPrecioDAO extends JpaRepository<OrdenVentaPrecio, Integer> {
    
    //Obtiene el siguiente id
    public OrdenVentaPrecio findTopByOrderByIdDesc();
    
    //Obtiene un listado por ordenVenta
    public List<OrdenVentaPrecio> findByOrdenVenta(OrdenVenta ordenVenta);
    
    //Obtiene una lista por orden de venta y precios desde
    public List<OrdenVentaPrecio> findByOrdenVentaAndPreciosDesde(OrdenVenta ordenVenta, Date preciosDesde);
    
}
