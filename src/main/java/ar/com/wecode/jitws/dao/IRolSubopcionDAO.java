//Paquete al que pertenece la interfaz
package ar.com.wecode.jitws.dao;

import ar.com.wecode.jitws.model.Modulo;
import ar.com.wecode.jitws.model.Rol;
import ar.com.wecode.jitws.model.RolSubopcion;
import ar.com.wecode.jitws.model.Submodulo;
import ar.com.wecode.jitws.model.Subopcion;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Interfaz DAO RolSubopcion
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IRolSubopcionDAO extends JpaRepository<RolSubopcion, Integer> {
    
    public final String NOMBRE_TABLA = "rolsubopcion";
    
    //Obtiene una lista de subopciones por rol
    public List<RolSubopcion> findByRol(Optional<Rol> rol);
    
    //Obtiene una lista de RolSubopcion por rol y modulo
    public List<RolSubopcion> findByRolAndSubopcion_Submodulo_Modulo(Optional<Rol> rol, Optional<Modulo> modulo);
    
    //Obtiene una lista de RolSubopcion por rol y submodulo
    public List<RolSubopcion> findByRolAndSubopcion_Submodulo(Optional<Rol> rol, Optional<Submodulo> submodulo);
    
    //Obtiene un RolSubmodulo por rol y subopcion
    public RolSubopcion findByRolAndSubopcion(Optional<Rol> rol, Optional<Subopcion> subopcion);
    
    //Elimina todos los datos de la tabla
    //@Query(value = "DELETE FROM rolsubopcion", nativeQuery = true)
    //public void eliminarTodo();
    
    //Reestablece autoincremental
    @Query(value = "ALTER TABLE rolsubopcion AUTO_INCREMENT=1", nativeQuery = true)
    public void reestablecerAutoincremental();
    
}
