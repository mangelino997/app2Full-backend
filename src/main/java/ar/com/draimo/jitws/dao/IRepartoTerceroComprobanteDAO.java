//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.RepartoTercero;
import ar.com.draimo.jitws.model.RepartoTerceroComprobante;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO RepartoTerceroComprobante
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IRepartoTerceroComprobanteDAO extends JpaRepository<RepartoTerceroComprobante, Integer> {
    
    //Obtiene el siguiente id
    public RepartoTerceroComprobante findTopByOrderByIdDesc();
    
    //Obtiene un listado de RepartoTerceroComprobante por idRepartoTercero
    public List<RepartoTerceroComprobante> findByRepartoTercero(RepartoTercero repartoTercero);
    
}
