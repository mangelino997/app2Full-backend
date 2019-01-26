//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.ViajeTercero;
import ar.com.draimo.jitws.model.ViajeTerceroCombustible;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO Viaje Tercero Combustible
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IViajeTerceroCombustibleDAO extends JpaRepository<ViajeTerceroCombustible, Integer> {
    
    //Obtiene el siguiente id
    public ViajeTerceroCombustible findTopByOrderByIdDesc();
    
    //Obtiene un listado por viaje tercero
    public List<ViajeTerceroCombustible> findByViajeTercero(ViajeTercero viajeTercero);
    
}
