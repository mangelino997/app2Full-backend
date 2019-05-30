package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.TalonarioRecibo;
import org.springframework.data.jpa.repository.JpaRepository;
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
    
}
