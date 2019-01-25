//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.ViajeTercero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Interfaz DAO Viaje Tercero
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IViajeTerceroDAO extends JpaRepository<ViajeTercero, Integer> {
    
    //Obtiene el siguiente id
    public ViajeTercero findTopByOrderByIdDesc();
    
    //Obtiene un registro por id
    @Query(value = "SELECT * FROM viajetercero WHERE id=:id", nativeQuery = true)
    public ViajeTercero obtenerPorId(@Param("id") int id);
    
}
