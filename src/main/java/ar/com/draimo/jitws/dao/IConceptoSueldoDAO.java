////Paquete al que pertenece la interfaz

package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.ConceptoSueldo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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
    @Query(value = "SELECT * FROM jitdb.conceptosueldo cs "
            + "INNER JOIN jitdb.afipconceptosueldo acs ON cs.idAfipConceptoSueldo=acs.id " 
            + "INNER JOIN jitdb.afipconceptosueldogrupo acsg ON acs.idAfipConceptoSueldoGrupo=acsg.id "
            + "WHERE acsg.idTipoConceptoSueldo=:idTipoConceptoSueldo ORDER BY cs.nombre ASC", 
            nativeQuery = true)
    public List<ConceptoSueldo> listarPorTipoConceptoSueldo(@Param("idTipoConceptoSueldo") int idTipoConceptoSueldo);
    
    //Obtiene una lista
    public List<ConceptoSueldo> findAllByOrderByNombreAsc();
}
