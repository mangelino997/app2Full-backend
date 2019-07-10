//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.Reparto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Interfaz DAO Reparto
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IRepartoDAO extends JpaRepository<Reparto, Integer> {
    
    //Obtiene el siguiente id
    public Reparto findTopByOrderByIdDesc();
    
    //Obtiene u listado por estaCerrada falso
    @Query(value = "SELECT * FROM reparto WHERE estaCerrada=:estaCerrada", nativeQuery = true )
    public List<Reparto> listarPorEstaCerrada(@Param("estaCerrada") boolean estaCerrada);
    
}
