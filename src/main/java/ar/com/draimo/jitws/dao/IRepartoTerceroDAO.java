//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.RepartoTercero;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Interfaz DAO Reparto Tercero
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IRepartoTerceroDAO extends JpaRepository<RepartoTercero, Integer> {
    
    //Obtiene el siguiente id
    public RepartoTercero findTopByOrderByIdDesc();
    
    //Obtiene u listado por estaCerrada 
    @Query(value = "SELECT * FROM repartotercero WHERE estaCerrada=:estaCerrada", nativeQuery = true)
    public List<RepartoTercero> listarPorEstaCerrada(@Param("estaCerrada") boolean estaCerrada);
    
}
