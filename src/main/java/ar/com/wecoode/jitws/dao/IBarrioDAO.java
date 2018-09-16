package ar.com.wecoode.jitws.dao;

import ar.com.wecoode.jitws.model.Barrio;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interfaz Barrio DAO
 * Metodos contra la base de datos
 * @author blas
 */

@Repository
public interface IBarrioDAO extends JpaRepository<Barrio, Integer> {
    
    //Obtiene el siguiente id
    public Barrio findTopByOrderByIdDesc();
    
    //Obtiene un listado por nombre
    public List<Barrio> findByNombreContaining(String nombre);
    
}
