//Paquete al que pertenece la interfaz
package ar.com.wecoode.jitws.dao;

import ar.com.wecoode.jitws.model.Pais;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO Pais
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IPaisDAO extends JpaRepository<Pais, Integer> {
    
    //Obtiene el siguiente id
    public Pais findTopByOrderByIdDesc();
    
    //Obtiene una lista por nombre
    public List<Pais> findByNombreContaining(String nombre);
    
}
