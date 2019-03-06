//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.VentaComprobanteItemCR;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO VentaComprobanteItemCR
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IVentaComprobanteItemCRDAO extends JpaRepository<VentaComprobanteItemCR, Integer> {
    
    //Obtiene el siguiente id
    public VentaComprobanteItemCR findTopByOrderByIdDesc();
    
}
