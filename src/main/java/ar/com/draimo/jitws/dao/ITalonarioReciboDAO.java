package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.TalonarioRecibo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Interfaz TalonarioRecibo
 * Metodos contra la base de datos
 * @author blas
 */

@Repository
public interface ITalonarioReciboDAO extends JpaRepository<TalonarioRecibo, Integer> {
    
    //Obtiene el siguiente id
    public TalonarioRecibo findTopByOrderByIdDesc();
    
    @Query(value = "SELECT * FROM talonariorecibo c WHERE :desdeHasta BETWEEN "
            + "c.desde AND c.hasta", nativeQuery = true)
    public List<TalonarioRecibo> listarPorDesdeHasta(@Param("desdeHasta") int desdeHasta);
    
}
