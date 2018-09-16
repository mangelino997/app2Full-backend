//Paquete al que pertenece la interfaz
package ar.com.wecoode.jitws.dao;

import ar.com.wecoode.jitws.model.Opcion;
import ar.com.wecoode.jitws.model.Rol;
import ar.com.wecoode.jitws.model.RolOpcion;
import ar.com.wecoode.jitws.model.Subopcion;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Interfaz DAO RolOpcion
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IRolOpcionDAO extends JpaRepository<RolOpcion, Integer> {
    
    //Obtiene una lista de opciones por rol
    public List<RolOpcion> findByRol(Optional<Rol> rol);
    
    //Obtiene una lista por rol y subopcion
    public List<RolOpcion> findByRolAndOpcion_Subopcion(Optional<Rol> rol, Optional<Subopcion> subopcion);
    
    //Obtiene por rol y opcion
    public RolOpcion findByRolAndOpcion(Optional<Rol> rol, Optional<Opcion> opcion);
    
    //Elimina todos los datos de la tabla
    @Query(value = "DELETE FROM rolopcion", nativeQuery = true)
    public void eliminarTodo();
    
    //Reestablece autoincremental
    @Query(value = "ALTER TABLE rolopcion AUTO_INCREMENT=1", nativeQuery = true)
    public void reestablecerAutoincremental();
    
}
