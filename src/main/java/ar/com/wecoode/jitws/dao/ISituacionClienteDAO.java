//Paquete al que pertenece la interfaz
package ar.com.wecoode.jitws.dao;

import ar.com.wecoode.jitws.model.SituacionCliente;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO SituacionCliente
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface ISituacionClienteDAO extends JpaRepository<SituacionCliente, Integer> {
    
    //Obtiene el siguiente id
    public SituacionCliente findTopByOrderByIdDesc();
    
    //Obtiene una lista por nombre
    public List<SituacionCliente> findByNombreContaining(String nombre);
    
}
