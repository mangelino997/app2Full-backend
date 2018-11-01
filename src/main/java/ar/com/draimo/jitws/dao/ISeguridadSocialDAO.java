//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.SeguridadSocial;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO Seguridad social
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface ISeguridadSocialDAO extends JpaRepository<SeguridadSocial, Integer> {
    
    //Obtiene el siguiente id
    public SeguridadSocial findTopByOrderByIdDesc();
    
    //Obtiene una lista por nombre
    public List<SeguridadSocial> findByNombreContaining(String nombre);
    
}
