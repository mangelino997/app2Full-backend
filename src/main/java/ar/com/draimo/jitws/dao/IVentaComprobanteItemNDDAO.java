//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.VentaComprobanteItemND;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO VentaComprobanteItem ND
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IVentaComprobanteItemNDDAO extends JpaRepository<VentaComprobanteItemND, Integer> {
    
    //Obtiene el ultimo registro
    public VentaComprobanteItemND findTopByOrderByIdDesc();
    
}