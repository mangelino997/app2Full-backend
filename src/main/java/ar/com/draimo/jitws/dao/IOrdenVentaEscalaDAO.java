//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.OrdenVenta;
import ar.com.draimo.jitws.model.OrdenVentaEscala;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

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
    
}
