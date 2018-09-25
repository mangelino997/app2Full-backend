//Paquete al que pertenece la interfaz
package ar.com.wecoode.jitws.dao;

import ar.com.wecoode.jitws.model.VentaComprobanteItem;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO VentaComprobanteItem
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IVentaComprobanteItemDAO extends JpaRepository<VentaComprobanteItem, Integer> {
    
    //Obtiene el siguiente id
    public VentaComprobanteItem findTopByOrderByIdDesc();
    
}
