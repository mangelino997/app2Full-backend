//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.RepartoPropioPersonal;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO RepartoPropioPersonal
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IRepartoPropioPersonalDAO extends JpaRepository<RepartoPropioPersonal, Integer> {
    
    //Obtiene el siguiente id
    public RepartoPropioPersonal findTopByOrderByIdDesc();
    
}
