package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.SoporteEstado;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interfaz SoporteEstado DAO
 * Metodos contra la base de datos
 * @author blas
 */

@Repository
public interface ISoporteEstadoDAO extends JpaRepository<SoporteEstado, Integer> {
    
    //Obtiene el siguiente id
    public SoporteEstado findTopByOrderByIdDesc();
    
    //Obtiene un listado por nombre
    public List<SoporteEstado> findByNombreContaining(String nombre);
    
}
