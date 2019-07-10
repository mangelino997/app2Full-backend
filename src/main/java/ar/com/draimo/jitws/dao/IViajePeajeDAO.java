//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.Viaje;
import ar.com.draimo.jitws.model.ViajePeaje;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO Viaje Peaje
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IViajePeajeDAO extends JpaRepository<ViajePeaje, Integer> {
    
    //Obtiene el siguiente id
    public ViajePeaje findTopByOrderByIdDesc();
    
    //Obtiene una lista por viaje
    public List<ViajePeaje> findByViaje(Viaje viaje);
    
}
