//Paquete al que pertenece la interfaz
package ar.com.wecoode.jitws.dao;

import ar.com.wecoode.jitws.model.RepartoPropio;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO Reparto Propio
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IRepartoPropioDAO extends JpaRepository<RepartoPropio, Integer> {
    
    //Obtiene el siguiente id
    public RepartoPropio findTopByOrderByIdDesc();
    
}
