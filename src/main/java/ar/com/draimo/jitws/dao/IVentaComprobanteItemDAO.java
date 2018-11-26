//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.VentaComprobanteItemFA;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO VentaComprobanteItem
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IVentaComprobanteItemDAO extends JpaRepository<VentaComprobanteItemFA, Integer> {
    
    //Obtiene el siguiente id
    public VentaComprobanteItemFA findTopByOrderByIdDesc();
    
}
