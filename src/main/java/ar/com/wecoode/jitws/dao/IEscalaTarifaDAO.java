//Paquete al que pertenece la interfaz
package ar.com.wecoode.jitws.dao;

import ar.com.wecoode.jitws.model.EscalaTarifa;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO EscalaTarifa
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IEscalaTarifaDAO extends JpaRepository<EscalaTarifa, Integer> {
    
    //Obtiene el siguiente id
    public EscalaTarifa findTopByOrderByIdDesc();
    
}
