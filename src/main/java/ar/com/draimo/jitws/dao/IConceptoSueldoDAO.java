////Paquete al que pertenece la interfaz

package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.ConceptoSueldo;
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
    
    //Obtiene la lista filtrada por Tipo Concepto Sueldo
//    public List<ConceptoSueldo> listarPorTipoConcepto(int idTipoConceptoSueldo);
    
    //Obtiene la lista por el Nombre (Descripción)
    public List<ConceptoSueldo> findByNombreContaining(String nombre);
}
