//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.RepartoPropio;
import ar.com.draimo.jitws.model.RepartoPropioComprobante;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO RepartoPropioComprobante
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IRepartoPropioComprobanteDAO extends JpaRepository<RepartoPropioComprobante, Integer> {
    
    //Obtiene el siguiente id
    public RepartoPropioComprobante findTopByOrderByIdDesc();
    
    //Obtiene un listado de RepartoPropioComprobante por idRepartoPropio
    public List<RepartoPropioComprobante> findByRepartoPropio(RepartoPropio repartoPropio);
    
}
