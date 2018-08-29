package ar.com.wecoode.jitws.dao;

import ar.com.wecoode.jitws.constant.NombreConstant;
import ar.com.wecoode.jitws.model.Barrio;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Interfaz Barrio DAO
 * Metodos contra la base de datos
 * @author blas
 */

@Repository
public interface IBarrioDAO extends JpaRepository<Barrio, Integer> {
    
    public final String NOMBRE_TABLA = "barrio";
    
    //public List<Barrio> listarPorNombre(String nombre);
    
    //Obtiene el siguiente id
    @Query(value = "SELECT Auto_increment FROM information_schema.tables "
            + "WHERE table_name='" + NOMBRE_TABLA + "'" + " AND table_schema='" 
            + NombreConstant.NOMBRE_BASE_DATOS + "'", nativeQuery = true)
    public int obtenerSiguienteId();
    
    //Obtiene un listado por nombre
    public List<Barrio> findByNombreContaining(String nombre);
    
}
