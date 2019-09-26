//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.SeguimientoSituacion;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO SeguimientoSituacion
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface ISeguimientoSituacionDAO extends JpaRepository<SeguimientoSituacion, Integer> {
    
    //Obtiene el ultimo registro
    public SeguimientoSituacion findTopByOrderByIdDesc();
    
    //Obtiene una lista por nombre
    public List<SeguimientoSituacion> findByNombreContaining(String nombre);
    
}