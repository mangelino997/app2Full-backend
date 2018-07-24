//Paquete al que pertenece la interfaz
package ar.com.wecode.jitws.dao;

import ar.com.wecode.jitws.model.OpcionPestania;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO OpcionPestania
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IOpcionPestaniaDAO extends JpaRepository<OpcionPestania, Integer> {
    
    public final String NOMBRE_TABLA = "opcionpestania";
    
    //Obtiene una lista de pestañas por idOpcion
    //public List<OpcionPestania> listarPorIdRolIdOpcion(int idRol, int idOpcion);
    
    //Obtiene por idOpcion + idPestania
    //public OpcionPestania obtenerPorIdOpcionIdPestania(int idOpcion, int idPestania);
    
}
