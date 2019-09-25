package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.AfipConcepto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interfaz afipConcepto DAO
 * Metodos contra la base de datos
 * @author blas
 */

@Repository
public interface IAfipConceptoDAO extends JpaRepository<AfipConcepto, Integer> {
    
    //Obtiene el ultimo registro
    public AfipConcepto findTopByOrderByIdDesc();
    
    //Obtiene un listado por nombre
    public List<AfipConcepto> findByNombreContaining(String nombre);
    
}
