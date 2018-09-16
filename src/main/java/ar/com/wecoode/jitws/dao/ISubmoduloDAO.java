//Paquete al que pertenece la interfaz
package ar.com.wecoode.jitws.dao;

import ar.com.wecoode.jitws.model.Modulo;
import ar.com.wecoode.jitws.model.Submodulo;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO Submodulo
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface ISubmoduloDAO extends JpaRepository<Submodulo, Integer> {
    
    //Obtiene el siguiente id
    public Submodulo findTopByOrderByIdDesc();
    
    //Obtiene un lista por modulo
    public List<Submodulo> findByModulo(Optional<Modulo> modulo);
    
    //Obtiene un listado por nombre
    public List<Submodulo> findByNombreContaining(String nombre);
    
}
