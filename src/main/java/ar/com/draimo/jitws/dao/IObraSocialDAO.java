//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.ObraSocial;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO Obra social
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IObraSocialDAO extends JpaRepository<ObraSocial, Integer> {
    
    //Obtiene el siguiente id
    public ObraSocial findTopByOrderByIdDesc();
    
    //Obtiene una lista por nombre
    public List<ObraSocial> findByNombreContaining(String nombre);
    
}
