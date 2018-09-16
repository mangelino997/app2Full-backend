//Paquete al que pertenece la interfaz
package ar.com.wecoode.jitws.dao;

import ar.com.wecoode.jitws.model.Personal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO Personal
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IPersonalDAO extends JpaRepository<Personal, Integer> {
    
    //Obtiene el siguiente id
    public Personal findTopByOrderByIdDesc();
    
    //Obtiene una lista por nombre completo
    public List<Personal> findByNombreCompletoContaining(String nombreCompleto);
    
    //Obtiene una lista por nombre completo y por esChofer
    public List<Personal> findByNombreCompletoContainingAndEsChofer(String nombreCompleto, int esChofer);
    
}
