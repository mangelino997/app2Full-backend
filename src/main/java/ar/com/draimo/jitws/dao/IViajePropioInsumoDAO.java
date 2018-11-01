//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.ViajePropio;
import ar.com.draimo.jitws.model.ViajePropioInsumo;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO Viaje Propio Insumo
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IViajePropioInsumoDAO extends JpaRepository<ViajePropioInsumo, Integer> {
    
    //Obtiene el siguiente id
    public ViajePropioInsumo findTopByOrderByIdDesc();
    
    //Obtiene una lista por viaje propio
    public List<ViajePropioInsumo> findByViajePropio(Optional<ViajePropio> viajePropio);
    
}
