//Paquete al que pertenece la interfaz
package ar.com.wecoode.jitws.dao;

import ar.com.wecoode.jitws.model.Rol;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO Rol
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IRolDAO extends JpaRepository<Rol, Integer> {
    
    //Obtiene el siguiente id
    public Rol findTopByOrderByIdDesc();
    
    //Obtiene una lista por nombre
    public List<Rol> findByNombreContaining(String nombre);
    
}
