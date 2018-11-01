//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.Cobrador;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO Cobrador
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface ICobradorDAO extends JpaRepository<Cobrador, Integer> {
    
    //Obtiene el siguiente id
    public Cobrador findTopByOrderByIdDesc();
    
    //Obtiene una lista por nombre
    public List<Cobrador> findByNombreContaining(String nombre);
    
}
