//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.Pestania;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO Pestania
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IPestaniaDAO extends JpaRepository<Pestania, Integer> {
    
    //Obtiene el ultimo registro
    public Pestania findTopByOrderByIdDesc();
    
    //Obtiene una lista por nombre
    public List<Pestania> findByNombreContaining(String nombre);
    
}