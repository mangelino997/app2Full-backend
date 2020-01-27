////Paquete al que pertenece la interfaz

package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.ConceptoSueldo;
import ar.com.draimo.jitws.model.TipoConceptoSueldo;
import java.util.List;
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
    
    //Obtiene la lista por el Nombre (Descripción)
    public List<ConceptoSueldo> findByNombreContaining(String nombre);
    
    //Obtiene Lista por TipoConcepto
    //public List<ConceptoSueldo> findByTipoConceptoSueldoOrderByNombreAsc (TipoConceptoSueldo elemento);
    
    //Obtiene una lista
    //public List<ConceptoSueldo> findAllByOrderByNombreAsc();
}
