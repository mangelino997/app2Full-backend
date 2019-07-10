//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.Viaje;
import ar.com.draimo.jitws.model.ViajeEfectivo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO Viaje Efectivo
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IViajeEfectivoDAO extends JpaRepository<ViajeEfectivo, Integer> {
    
    //Obtiene el siguiente id
    public ViajeEfectivo findTopByOrderByIdDesc();
    
    //Obtiene una lista por viaje propio
    public List<ViajeEfectivo> findByViaje(Viaje viaje);
    
}
