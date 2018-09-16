//Paquete al que pertenece la interfaz
package ar.com.wecoode.jitws.dao;

import ar.com.wecoode.jitws.model.Pestania;
import ar.com.wecoode.jitws.model.Rol;
import ar.com.wecoode.jitws.model.Subopcion;
import ar.com.wecoode.jitws.model.SubopcionPestania;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Interfaz DAO SubopcionPestania
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface ISubopcionPestaniaDAO extends JpaRepository<SubopcionPestania, Integer> {
    
    //Obtiene una lista de pestañas por subopcion
    public List<SubopcionPestania> findByRolAndSubopcion(Optional<Rol> rol, Optional<Subopcion> subopcion);
    
    //Obtiene por subopcion y pestania
    public SubopcionPestania findBySubopcionAndPestania(Optional<Subopcion> subopcion, Optional<Pestania> pestania);
    
    //Elimina todos los datos de la tabla
    @Query(value = "DELETE FROM subopcionpestania", nativeQuery = true)
    public void eliminarTodo();
    
    //Reestablece autoincremental
    @Query(value = "ALTER TABLE subopcionpestania AUTO_INCREMENT=1", nativeQuery = true)
    public void reestablecerAutoincremental();
    
}
