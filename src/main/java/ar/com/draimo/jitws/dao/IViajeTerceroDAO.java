//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.ViajeTercero;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO Viaje Tercero
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IViajeTerceroDAO extends JpaRepository<ViajeTercero, Integer> {
    
    //Obtiene el siguiente id
    public ViajeTercero findTopByOrderByIdDesc();
    
}