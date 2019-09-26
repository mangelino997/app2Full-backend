//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.Reparto;
import ar.com.draimo.jitws.model.RepartoComprobante;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO RepartoComprobante
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IRepartoComprobanteDAO extends JpaRepository<RepartoComprobante, Integer> {
    
    //Obtiene el ultimo registro
    public RepartoComprobante findTopByOrderByIdDesc();
    
    //Obtiene un listado de RepartoComprobante por idReparto
    public List<RepartoComprobante> findByReparto(Reparto reparto);
    
}