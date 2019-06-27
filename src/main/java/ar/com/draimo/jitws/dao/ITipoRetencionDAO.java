package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.TipoRetencion;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interfaz TipoRetencion DAO
 * Metodos contra la base de datos
 * @author blas
 */

@Repository
public interface ITipoRetencionDAO extends JpaRepository<TipoRetencion, Integer> {
    
    //Obtiene el siguiente id
    public TipoRetencion findTopByOrderByIdDesc();
    
    //Obtiene un listado por nombre
    public List<TipoRetencion> findByNombreContaining(String nombre);
    
}
