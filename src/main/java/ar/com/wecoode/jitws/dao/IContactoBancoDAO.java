//Paquete al que pertenece la interfaz
package ar.com.wecoode.jitws.dao;

import ar.com.wecoode.jitws.model.ContactoBanco;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO ContactoBanco
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IContactoBancoDAO extends JpaRepository<ContactoBanco, Integer> {
    
    //Obtiene el siguiente id
    public ContactoBanco findTopByOrderByIdDesc();
    
    //Obtiene una lista por nombre
    public List<ContactoBanco> findByNombreContaining(String nombre);
    
}
