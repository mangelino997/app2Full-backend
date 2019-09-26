//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.Reparto;
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
    
    //Obtiene el ultimo registro
    public ViajeEfectivo findTopByOrderByIdDesc();
    
    //Obtiene una lista por viaje 
    public List<ViajeEfectivo> findByViaje(Viaje viaje);
    
    //Obtiene una lista por reparto
    public List<ViajeEfectivo> findByReparto(Reparto reparto);
    
}