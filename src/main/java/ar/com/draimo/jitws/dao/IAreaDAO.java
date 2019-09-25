//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.Area;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO Area
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IAreaDAO extends JpaRepository<Area, Integer> {
    
    //Obtiene el ultimo registro
    public Area findTopByOrderByIdDesc();
    
    //Obtiene una lista por nombre
    public List<Area> findByNombreContaining(String nombre);
    
}