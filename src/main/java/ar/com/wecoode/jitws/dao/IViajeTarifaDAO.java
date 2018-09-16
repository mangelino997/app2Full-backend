//Paquete al que pertenece la interfaz
package ar.com.wecoode.jitws.dao;

import ar.com.wecoode.jitws.model.ViajeTarifa;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO ViajeTarifa
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IViajeTarifaDAO extends JpaRepository<ViajeTarifa, Integer> {
    
    //Obtiene el siguiente id
    public ViajeTarifa findTopByOrderByIdDesc();
    
    //Obtiene una lista por nombre
    public List<ViajeTarifa> findByNombreContaining(String nombre);
    
}
