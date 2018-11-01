//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.Submodulo;
import ar.com.draimo.jitws.model.Subopcion;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO Subopcion
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface ISubopcionDAO extends JpaRepository<Subopcion, Integer> {
    
    //Obtiene el siguiente id
    public Subopcion findTopByOrderByIdDesc();
    
    //Obtiene una lista por submodulo
    public List<Subopcion> findBySubmodulo(Optional<Submodulo> submodulo);
    
    //Obtiene un listado por nombre
    public List<Subopcion> findByNombreContaining(String nombre);
    
}
