//Paquete al que pertence la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.TipoPercepcion;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interfaz TipoPercepcion DAO
 * Metodos contra la base de datos
 * @author blas
 */

@Repository
public interface ITipoPercepcionDAO extends JpaRepository<TipoPercepcion, Integer> {
    
    //Obtiene el ultimo registro
    public TipoPercepcion findTopByOrderByIdDesc();
    
    //Obtiene un listado por nombre
    public List<TipoPercepcion> findByNombreContaining(String nombre);
    
}