//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.Modulo;
import ar.com.draimo.jitws.model.Rol;
import ar.com.draimo.jitws.model.RolSubopcion;
import ar.com.draimo.jitws.model.Submodulo;
import ar.com.draimo.jitws.model.Subopcion;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Interfaz DAO RolSubopcion
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IRolSubopcionDAO extends JpaRepository<RolSubopcion, Integer> {
    
    //Obtiene una lista de subopciones por rol
    public List<RolSubopcion> findByRol(Rol rol);
    
    //Obtiene una lista de RolSubopcion por rol y modulo
    public List<RolSubopcion> findByRolAndSubopcion_Submodulo_Modulo(Optional<Rol> rol, Optional<Modulo> modulo);
    
    //Obtiene una lista de RolSubopcion por rol y submodulo
    public List<RolSubopcion> findByRolAndSubopcion_Submodulo(Optional<Rol> rol, Optional<Submodulo> submodulo);
    
    //Obtiene un RolSubmodulo por rol y subopcion
    public RolSubopcion findByRolAndSubopcion(Optional<Rol> rol, Optional<Subopcion> subopcion);
    
    //Obtiene por rol y subopcion
    @Query(value = "select * from rolsubopcion where idRol=:idRol and idSubopcion=:idSubopcion", nativeQuery = true)
    public RolSubopcion obtenerPorRolYSubopcion(@Param("idRol") int idRol, @Param("idSubopcion") int idSubopcion);
    
    //Elimina todos los datos de la tabla
    @Modifying
    @Query(value = "DELETE FROM rolsubopcion", nativeQuery = true)
    public void eliminarTodo();
    
    //Reestablece autoincremental
    @Modifying
    @Query(value = "ALTER TABLE rolsubopcion AUTO_INCREMENT=1", nativeQuery = true)
    public void reestablecerAutoincremental();
    
}
