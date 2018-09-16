//Paquete al que pertenece la interfaz
package ar.com.wecoode.jitws.dao;

import ar.com.wecoode.jitws.model.PuntoVenta;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO PuntoVenta
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IPuntoVentaDAO extends JpaRepository<PuntoVenta, Integer> {
    
    //Obtiene el siguiente id
    public PuntoVenta findTopByOrderByIdDesc();
    
}
