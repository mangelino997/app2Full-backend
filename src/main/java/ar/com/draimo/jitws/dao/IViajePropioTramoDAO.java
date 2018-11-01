//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.Tramo;
import ar.com.draimo.jitws.model.ViajePropio;
import ar.com.draimo.jitws.model.ViajePropioTramo;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO Viaje Propio Tramo
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IViajePropioTramoDAO extends JpaRepository<ViajePropioTramo, Integer> {
    
    //Obtiene el siguiente id
    public ViajePropioTramo findTopByOrderByIdDesc();
    
    //Obtiene una lista por viaje propio
    public List<ViajePropioTramo> findByViajePropio(Optional<ViajePropio> viajePropio);
    
    //Obtiene por viaje propio y tramo
    public ViajePropioTramo findByViajePropioAndTramo(Optional<ViajePropio> viajePropio, Optional<Tramo> tramo);
    
}
