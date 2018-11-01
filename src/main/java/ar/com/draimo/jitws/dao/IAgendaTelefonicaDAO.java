//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.AgendaTelefonica;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO Agenda telefonica
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IAgendaTelefonicaDAO extends JpaRepository<AgendaTelefonica, Integer> {
    
    //Obtiene el siguiente id
    public AgendaTelefonica findTopByOrderByIdDesc();
    
    //Obtiene una lista por nombre
    public List<AgendaTelefonica> findByNombreContaining(String nombre);
    
}
