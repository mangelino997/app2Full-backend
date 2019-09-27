//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.TipoContacto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO Tipo de contacto
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface ITipoContactoDAO extends JpaRepository<TipoContacto, Integer> {
    
    //Obtiene el ultimo registro
    public TipoContacto findTopByOrderByIdDesc();
    
    //Obtiene una lista por nombre
    public List<TipoContacto> findByNombreContaining(String nombre);
    
}