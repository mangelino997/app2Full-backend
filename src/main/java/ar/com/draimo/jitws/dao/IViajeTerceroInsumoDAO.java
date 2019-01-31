//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.ViajeTercero;
import ar.com.draimo.jitws.model.ViajeTerceroInsumo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO Viaje Tercero Insumo
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IViajeTerceroInsumoDAO extends JpaRepository<ViajeTerceroInsumo, Integer> {
    
    //Obtiene el siguiente id
    public ViajeTerceroInsumo findTopByOrderByIdDesc();
    
    //Obtiene un listado por viaje tercero
    public List<ViajeTerceroInsumo> findByViajeTercero(ViajeTercero viajeTercero);
    
}
