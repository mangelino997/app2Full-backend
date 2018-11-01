//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.Opcion;
import ar.com.draimo.jitws.model.OpcionPestania;
import ar.com.draimo.jitws.model.Pestania;
import ar.com.draimo.jitws.model.Rol;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO OpcionPestania
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IOpcionPestaniaDAO extends JpaRepository<OpcionPestania, Integer> {
    
    //Obtiene una lista de pestañas por rol y opcion
    public List<OpcionPestania> findByRolAndOpcion(Optional<Rol> rol, Optional<Opcion> opcion);
    
    //Obtiene por opcion y pestania
    public OpcionPestania findByOpcionAndPestania(Optional<Opcion> opcion, Optional<Pestania> pestania);
    
}
