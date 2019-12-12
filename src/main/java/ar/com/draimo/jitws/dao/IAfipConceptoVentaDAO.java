package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.AfipConceptoVenta;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interfaz afipConcepto DAO
 * Metodos contra la base de datos
 * @author blas
 */

@Repository
public interface IAfipConceptoVentaDAO extends JpaRepository<AfipConceptoVenta, Integer> {
    
    //Obtiene el ultimo registro
    public AfipConceptoVenta findTopByOrderByIdDesc();
    
    //Obtiene un listado por nombre
    public List<AfipConceptoVenta> findByNombreContaining(String nombre);
    
}
