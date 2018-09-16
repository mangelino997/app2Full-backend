//Paquete al que pertenece la interfaz
package ar.com.wecoode.jitws.dao;

import ar.com.wecoode.jitws.model.ViajePropio;
import ar.com.wecoode.jitws.model.ViajePropioGasto;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO Viaje Propio Gasto
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IViajePropioGastoDAO extends JpaRepository<ViajePropioGasto, Integer> {
    
    //Obtiene el siguiente id
    public ViajePropioGasto findTopByOrderByIdDesc();
    
    //Obtiene una lista por viaje propio
    public List<ViajePropioGasto> findByViajePropio(Optional<ViajePropio> viajePropio);
    
}
