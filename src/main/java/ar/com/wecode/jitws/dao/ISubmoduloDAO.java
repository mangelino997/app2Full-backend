//Paquete al que pertenece la interfaz
package ar.com.wecode.jitws.dao;

import ar.com.wecode.jitws.constant.NombreConstant;
import ar.com.wecode.jitws.model.Submodulo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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
    
    //Obtiene un lista por idModulo
    //public List<Submodulo> listarPorIdModulo(int idModulo);
    
    //Obtiene un listado por nombre
    @Query(value = "SELECT * FROM submodulo b WHERE b.nombre like %:nom%", nativeQuery = true)
    public List<Submodulo> listarPorNombre(@Param("nom") String nom);
    
}
