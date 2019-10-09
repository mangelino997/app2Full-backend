//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.Reparto;
import ar.com.draimo.jitws.model.Viaje;
import ar.com.draimo.jitws.model.ViajeCombustible;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO Viaje Combustible
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IViajeCombustibleDAO extends JpaRepository<ViajeCombustible, Integer> {
    
    //Obtiene el ultimo registro
    public ViajeCombustible findTopByOrderByIdDesc();
    
    //Obtiene una lista por viaje
    public List<ViajeCombustible> findByViaje(Viaje viaje);
    
    //Obtiene una lista por Reparto 
    public List<ViajeCombustible> findByReparto(Reparto reparto);
    
    //Elimina una lista por Reparto 
    public void deleteByReparto(Reparto reparto);
    
}