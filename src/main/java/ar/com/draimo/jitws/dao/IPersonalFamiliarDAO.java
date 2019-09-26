//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.Personal;
import ar.com.draimo.jitws.model.PersonalFamiliar;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interfaz PersonalFamiliar DAO
 * Metodos contra la base de datos
 * @author blas
 */

@Repository
public interface IPersonalFamiliarDAO extends JpaRepository<PersonalFamiliar, Integer> {
    
    //Obtiene el ultimo registro
    public PersonalFamiliar findTopByOrderByIdDesc();
    
    //Obtiene un listado por nombre
    public List<PersonalFamiliar> findByAliasContaining(String alias);
    
    //Obtiene un listado por personal
    public List<PersonalFamiliar> findByPersonal(Personal personal);
    
}