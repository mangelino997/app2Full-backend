//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.RepartoPropio;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Interfaz DAO Reparto Propio
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IRepartoPropioDAO extends JpaRepository<RepartoPropio, Integer> {
    
    //Obtiene el siguiente id
    public RepartoPropio findTopByOrderByIdDesc();
    
    //Obtiene u listado por estaCerrada falso
    @Query(value = "SELECT * FROM repartopropio WHERE estaCerrada=:estaCerrada", nativeQuery = true )
    public List<RepartoPropio> listarPorEstaCerrada(@Param("estaCerrada") boolean estaCerrada);
    
}
