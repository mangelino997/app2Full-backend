package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.AfipConceptoSueldo;
import ar.com.draimo.jitws.model.AfipConceptoSueldoGrupo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interfaz afipConceptoSueldo DAO
 * Metodos contra la base de datos
 * @author blas
 */

@Repository
public interface IAfipConceptoSueldoDAO extends JpaRepository<AfipConceptoSueldo, Integer> {
    
    //Obtiene el ultimo registro
    public AfipConceptoSueldo findTopByOrderByIdDesc();
    
    //Obtiene un listado por nombre
    public List<AfipConceptoSueldo> findByNombreContaining(String nombre);
    
    //Obtiene un listado por afipConceptoSueldoGrupo
    public List<AfipConceptoSueldo> findByAfipConceptoSueldoGrupo(AfipConceptoSueldoGrupo afipConceptoSueldoGrupo);
    
}
