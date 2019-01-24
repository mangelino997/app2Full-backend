//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.ViajePropio;
import ar.com.draimo.jitws.model.ViajePropioPeaje;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO Viaje Propio Peaje
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IViajePropioPeajeDAO extends JpaRepository<ViajePropioPeaje, Integer> {
    
    //Obtiene el siguiente id
    public ViajePropioPeaje findTopByOrderByIdDesc();
    
    //Obtiene una lista por viaje propio
    public List<ViajePropioPeaje> findByViajePropio(ViajePropio viajePropio);
    
}
