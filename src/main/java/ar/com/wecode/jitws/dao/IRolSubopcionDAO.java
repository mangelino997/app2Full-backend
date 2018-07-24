//Paquete al que pertenece la interfaz
package ar.com.wecode.jitws.dao;

import ar.com.wecode.jitws.model.RolSubopcion;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO RolSubopcion
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IRolSubopcionDAO extends JpaRepository<RolSubopcion, Integer> {
    
    public final String NOMBRE_TABLA = "rolsubopcion";
    
    //Obtiene una lista de subopciones por idRol
    //public List<RolSubopcion> listarPorIdRol(int idRol);
    
    //Obtiene una lista de RolSubopcion por idRol y idModulo
    //public List<RolSubopcion> listarPorIdRolIdModulo(int idRol, int idModulo);
    
    //Obtiene una lista de RolSubopcion por idRol y idSubmodulo
    //public List<RolSubopcion> listarPorIdRolIdSubmodulo(int idRol, int idSubmodulo);
    
    //Obtiene un RolSubmodulo por idRol + idSubopcion
    //public RolSubopcion obtenerPorIdRolIdSubopcion(int idRol, int idSubopcion);
    
    //Elimina todos los datos de la tabla
    //public void eliminarTodo();
    
    //Reestablece autoincremental
    //public void reestablecerAutoincremental();
    
}
