//Paquete al que pertenece la interfaz
package ar.com.wecoode.jitws.dao;

import ar.com.wecoode.jitws.model.ViajePropio;
import ar.com.wecoode.jitws.model.ViajePropioEfectivo;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO Viaje Propio Efectivo
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IViajePropioEfectivoDAO extends JpaRepository<ViajePropioEfectivo, Integer> {
    
    //Obtiene el siguiente id
    public ViajePropioEfectivo findTopByOrderByIdDesc();
    
    //Obtiene una lista por viaje propio
    public List<ViajePropioEfectivo> findByViajePropio(Optional<ViajePropio> viajePropio);
    
}
