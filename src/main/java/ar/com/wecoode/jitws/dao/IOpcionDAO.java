//Paquete al que pertenece la interfaz
package ar.com.wecoode.jitws.dao;

import ar.com.wecoode.jitws.constant.NombreConstant;
import ar.com.wecoode.jitws.model.Opcion;
import ar.com.wecoode.jitws.model.Subopcion;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Interfaz DAO Opcion
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IOpcionDAO extends JpaRepository<Opcion, Integer> {
    
    public final String NOMBRE_TABLA = "opcion";
    
    //Obtiene el siguiente id
    @Query(value = "SELECT Auto_increment FROM information_schema.tables "
            + "WHERE table_name='" + NOMBRE_TABLA +"'" + " AND table_schema='" 
            + NombreConstant.NOMBRE_BASE_DATOS + "'", nativeQuery = true)
    public int obtenerSiguienteId();
    
    //Obtiene la lista de opciones de una subopcion
    public List<Opcion> findBySubopcion(Optional<Subopcion> subopcion);
    
    //Obtiene un listado por nombre
    public List<Opcion> findByNombreContaining(String nombre);
    
}
