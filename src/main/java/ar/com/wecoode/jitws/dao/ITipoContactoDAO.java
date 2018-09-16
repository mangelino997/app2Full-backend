//Paquete al que pertenece la interfaz
package ar.com.wecoode.jitws.dao;

import ar.com.wecoode.jitws.model.TipoContacto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO Tipo de contacto
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface ITipoContactoDAO extends JpaRepository<TipoContacto, Integer> {
    
    //Obtiene el siguiente id
    public TipoContacto findTopByOrderByIdDesc();
    
    //Obtiene una lista por nombre
    public List<TipoContacto> findByNombreContaining(String nombre);
    
}
