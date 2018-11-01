//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.ViajeTercero;
import ar.com.draimo.jitws.model.ViajeTerceroEfectivo;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO Viaje Tercero Efectivo
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IViajeTerceroEfectivoDAO extends JpaRepository<ViajeTerceroEfectivo, Integer> {
    
    //Obtiene el siguiente id
    public ViajeTerceroEfectivo findTopByOrderByIdDesc();
    
    //Obtiene un listado por viaje tercero
    public List<ViajeTerceroEfectivo> findByViajeTercero(Optional<ViajeTercero> viajeTercero);
    
}
