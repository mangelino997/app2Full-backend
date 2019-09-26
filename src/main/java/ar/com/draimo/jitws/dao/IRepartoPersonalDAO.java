//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.RepartoPersonal;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO RepartoPersonal
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IRepartoPersonalDAO extends JpaRepository<RepartoPersonal, Integer> {
    
    //Obtiene el ultimo registro
    public RepartoPersonal findTopByOrderByIdDesc();
    
}