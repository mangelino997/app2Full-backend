//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.VentaComprobanteItemNC;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO VentaComprobanteItem NC
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IVentaComprobanteItemNCDAO extends JpaRepository<VentaComprobanteItemNC, Integer> {
    
    //Obtiene el siguiente id
    public VentaComprobanteItemNC findTopByOrderByIdDesc();
    
}
