//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.ViajeTercero;
import ar.com.draimo.jitws.model.ViajeTerceroTramo;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO Viaje Tercero Tramo
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IViajeTerceroTramoDAO extends JpaRepository<ViajeTerceroTramo, Integer> {
    
    //Obtiene el siguiente id
    public ViajeTerceroTramo findTopByOrderByIdDesc();
    
    //Obtiene un listado por viaje tercero
    public List<ViajeTerceroTramo> findByViajeTercero(Optional<ViajeTercero> viajeTercero);
    
}
