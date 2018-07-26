//Paquete al que pertenece la interfaz
package ar.com.wecode.jitws.dao;

import ar.com.wecode.jitws.model.Opcion;
import ar.com.wecode.jitws.model.OpcionPestania;
import ar.com.wecode.jitws.model.Pestania;
import ar.com.wecode.jitws.model.Rol;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO OpcionPestania
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IOpcionPestaniaDAO extends JpaRepository<OpcionPestania, Integer> {
    
    public final String NOMBRE_TABLA = "opcionpestania";
    
    //Obtiene una lista de pestañas por rol y opcion
    public List<OpcionPestania> findByRolAndOpcion(Optional<Rol> rol, Optional<Opcion> opcion);
    
    //Obtiene por opcion y pestania
    public OpcionPestania findByOpcionAndPestania(Optional<Opcion> opcion, Optional<Pestania> pestania);
    
}
