//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.Viaje;
import ar.com.draimo.jitws.model.ViajeGasto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO Viaje Gasto
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IViajeGastoDAO extends JpaRepository<ViajeGasto, Integer> {
    
    //Obtiene el ultimo registro
    public ViajeGasto findTopByOrderByIdDesc();
    
    //Obtiene una lista por viaje 
    public List<ViajeGasto> findByViaje(Viaje viaje);
    
}