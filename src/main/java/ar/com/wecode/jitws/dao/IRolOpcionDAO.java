//Paquete al que pertenece la interfaz
package ar.com.wecode.jitws.dao;

import ar.com.wecode.jitws.model.RolOpcion;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO RolOpcion
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IRolOpcionDAO extends JpaRepository<RolOpcion, Integer> {
    
    public final String NOMBRE_TABLA = "rolopcion";
    
    //Obtiene una lista de opciones por idRol
    //public List<RolOpcion> listarPorIdRol(int idRol);
    
    //Obtiene una lista por idRol y idSubopcion
    //public List<RolOpcion> listarPorIdRolIdSubopcion(int idRol, int idSubopcion);
    
    //Obtiene por idRol + idOpcion
    //public RolOpcion obtenerPorIdRolIdOpcion(int idRol, int idOpcion);
    
    //Elimina todos los datos de la tabla
    //public void eliminarTodo();
    
    //Reestablece autoincremental
    //public void reestablecerAutoincremental();
    
}
