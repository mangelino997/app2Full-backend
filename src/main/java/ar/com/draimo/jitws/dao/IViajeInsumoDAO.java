//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.Viaje;
import ar.com.draimo.jitws.model.ViajeInsumo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO Viaje Insumo
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IViajeInsumoDAO extends JpaRepository<ViajeInsumo, Integer> {
    
    //Obtiene el siguiente id
    public ViajeInsumo findTopByOrderByIdDesc();
    
    //Obtiene una lista por viaje propio
    public List<ViajeInsumo> findByViaje(Viaje viaje);
    
}
