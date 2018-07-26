//Paquete al que pertenece la interfaz
package ar.com.wecode.jitws.dao;

import ar.com.wecode.jitws.constant.NombreConstant;
import ar.com.wecode.jitws.model.Modulo;
import ar.com.wecode.jitws.model.Submodulo;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Interfaz DAO Submodulo
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface ISubmoduloDAO extends JpaRepository<Submodulo, Integer> {
    
    public final String NOMBRE_TABLA = "submodulo";
    
    //Obtiene el siguiente id
    @Query(value = "SELECT Auto_increment FROM information_schema.tables "
            + "WHERE table_name='" + NOMBRE_TABLA +"'" + " AND table_schema='" 
            + NombreConstant.NOMBRE_BASE_DATOS + "'", nativeQuery = true)
    public int obtenerSiguienteId();
    
    //Obtiene un lista por modulo
    public List<Submodulo> findByModulo(Optional<Modulo> modulo);
    
    //Obtiene un listado por nombre
    public List<Submodulo> findByNombreContaining(String nombre);
    
}
