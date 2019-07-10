//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

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
    
    //Obtiene el siguiente id
    public ViajeCombustible findTopByOrderByIdDesc();
    
    //Obtiene una lista por viaje propio
    public List<ViajeCombustible> findByViaje(Viaje viaje);
    
}
