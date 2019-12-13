package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.AfipConceptoSueldoGrupo;
import ar.com.draimo.jitws.model.TipoConceptoSueldo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interfaz afipConceptoSueldoGrupo DAO
 * Metodos contra la base de datos
 * @author blas
 */

@Repository
public interface IAfipConceptoSueldoGrupoDAO extends JpaRepository<AfipConceptoSueldoGrupo, Integer> {
    
    //Obtiene el ultimo registro
    public AfipConceptoSueldoGrupo findTopByOrderByIdDesc();
    
    //Obtiene un listado por nombre
    public List<AfipConceptoSueldoGrupo> findByNombreContaining(String nombre);
    
    //Obtiene un listado por tipoConceptoSueldo
    public List<AfipConceptoSueldoGrupo> findByTipoConceptoSueldo(TipoConceptoSueldo tipoConceptoSueldo);
    
}
