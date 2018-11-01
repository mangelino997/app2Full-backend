//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.ViajeTipo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO ViajeTipo
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IViajeTipoDAO extends JpaRepository<ViajeTipo, Integer> {
    
    //Obtiene el siguiente id
    public ViajeTipo findTopByOrderByIdDesc();
    
    //Obtiene una lista por nombre
    public List<ViajeTipo> findByNombreContaining(String nombre);
    
}
