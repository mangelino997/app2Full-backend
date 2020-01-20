////Paquete al que pertenece la interfaz

package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.ConceptoSueldo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *Interfaz DAO ConceptoSueldo
 * Define los metodos particulares contra la base de datos
 * @author marina
 */
@Repository
public interface IConceptoSueldoDAO extends JpaRepository<ConceptoSueldo, Integer> {

    //Obtiene el siguiente id
    public ConceptoSueldo findTopByOrderByIdDesc();
    
}
