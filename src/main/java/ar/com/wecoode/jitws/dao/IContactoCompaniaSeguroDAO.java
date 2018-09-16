//Paquete al que pertenece la interfaz
package ar.com.wecoode.jitws.dao;

import ar.com.wecoode.jitws.model.ContactoCompaniaSeguro;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO ContactoCompaniaSeguro
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IContactoCompaniaSeguroDAO extends JpaRepository<ContactoCompaniaSeguro, Integer> {
    
    //Obtiene el siguiente id
    public ContactoCompaniaSeguro findTopByOrderByIdDesc();
    
    //Obtiene una lista por nombre
    public List<ContactoCompaniaSeguro> findByNombreContaining(String nombre);
    
}
