//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.Pestania;
import ar.com.draimo.jitws.model.Rol;
import ar.com.draimo.jitws.model.Subopcion;
import ar.com.draimo.jitws.model.SubopcionPestania;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * Interfaz DAO SubopcionPestania
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface ISubopcionPestaniaDAO extends JpaRepository<SubopcionPestania, Integer> {
    
    //Obtiene una lista por rol
    public List<SubopcionPestania> findByRol(Rol rol);
    
    //Obtiene una lista de pestañas por subopcion y rol
    public List<SubopcionPestania> findByRolAndSubopcion(Optional<Rol> rol, Optional<Subopcion> subopcion);
    
    //Obtiene por subopcion y pestania
    public SubopcionPestania findBySubopcionAndPestania(Optional<Subopcion> subopcion, Optional<Pestania> pestania);
    
    //Obtiene por rol, subopcion y pestania
    public SubopcionPestania findByRolAndSubopcionAndPestania(Optional<Rol> rol, Optional<Subopcion> subopcion, Optional<Pestania> pestania);
    
    //Elimina todos los datos de la tabla
    @Modifying
    @Query(value = "DELETE FROM subopcionpestania", nativeQuery = true)
    public void eliminarTodo();
    
    //Reestablece autoincremental
    @Modifying
    @Query(value = "ALTER TABLE subopcionpestania AUTO_INCREMENT=1", nativeQuery = true)
    public void reestablecerAutoincremental();
    
}