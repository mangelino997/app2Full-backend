//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.ViajePropio;
import ar.com.draimo.jitws.model.ViajePropioCombustible;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO Viaje Propio Combustible
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IViajePropioCombustibleDAO extends JpaRepository<ViajePropioCombustible, Integer> {
    
    //Obtiene el siguiente id
    public ViajePropioCombustible findTopByOrderByIdDesc();
    
    //Obtiene una lista por viaje propio
    public List<ViajePropioCombustible> findByViajePropio(Optional<ViajePropio> viajePropio);
    
}
