//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.Quincena;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO Quincena
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IQuincenaDAO extends JpaRepository<Quincena, Integer> {
    
    //Obtiene el siguiente id
    public Quincena findTopByOrderByIdDesc();
    
    //Obtiene una lista por nombre
    public List<Quincena> findByNombreContaining(String nombre);
    
}
