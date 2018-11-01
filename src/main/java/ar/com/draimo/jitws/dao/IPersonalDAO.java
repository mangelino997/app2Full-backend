//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.Personal;
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
    
    //Obtiene una lista por alias
    public List<Personal> findByAliasContaining(String alias);
    
    //Obtiene una lista por alias y por esChofer
    public List<Personal> findByAliasContainingAndEsChofer(String alias, int esChofer);
    
}
