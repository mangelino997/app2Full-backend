//Paquete al que pertenece la interfaz
package ar.com.wecode.jitws.dao;

import ar.com.wecode.jitws.model.SubopcionPestania;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO SubopcionPestania
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface ISubopcionPestaniaDAO extends JpaRepository<SubopcionPestania, Integer> {
    
    public final String NOMBRE_TABLA = "subopcionpestania";
    
    //Obtiene una lista de pestañas por idSubopcion
    //public List<SubopcionPestania> listarPorIdRolIdSubopcion(int idRol, int idSubopcion);
    
    //Obtiene un RolSubmodulo por idSubopcion + idPestania
    //public SubopcionPestania obtenerPorIdSubopcionIdPestania(int idSubopcion, int idPestania);
    
    //Elimina todos los datos de la tabla
    //public void eliminarTodo();
    
    //Reestablece autoincremental
    //public void reestablecerAutoincremental();
    
}
