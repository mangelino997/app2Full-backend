//Paquete al que pertenece la interfaz
package ar.com.wecoode.jitws.dao;

import ar.com.wecoode.jitws.model.Sexo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO Sexo
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface ISexoDAO extends JpaRepository<Sexo, Integer> {
    
    //Obtiene el siguiente id
    public Sexo findTopByOrderByIdDesc();
    
    //Obtiene una lista por nombre
    public List<Sexo> findByNombreContaining(String nombre);
    
}
