//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.PersonalAdelanto;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO PersonalAdelanto
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IPersonalAdelantoDAO extends JpaRepository<PersonalAdelanto, Integer> {
    
    //Obtiene el siguiente id
    public PersonalAdelanto findTopByOrderByIdDesc();
    
    
}
