package ar.com.wecode.jitws.dao;

import ar.com.wecode.jitws.model.Barrio;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Interfaz Barrio DAO
 * Metodos contra la base de datos
 * @author blas
 */

@Repository
public interface IBarrioDAO extends JpaRepository<Barrio, Integer> {
    
    //Obtiene un listado por nombre
    @Query(value = "SELECT * FROM barrio b WHERE b.nombre like %:nom%", nativeQuery = true)
    public List<Barrio> listarPorNombre(@Param("nom") String nom);
    
}
