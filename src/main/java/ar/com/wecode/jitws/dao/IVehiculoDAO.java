//Paquete al que pertenece la interfaz
package ar.com.wecode.jitws.dao;

import ar.com.wecode.jitws.constant.NombreConstant;
import ar.com.wecode.jitws.model.Vehiculo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Interfaz DAO Vehiculo
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IVehiculoDAO extends JpaRepository<Vehiculo, Integer> {
    
    public final String NOMBRE_TABLA = "vehiculo";
    
    //Obtiene el siguiente id
    @Query(value = "SELECT Auto_increment FROM information_schema.tables "
            + "WHERE table_name='" + NOMBRE_TABLA +"'" + " AND table_schema='" 
            + NombreConstant.NOMBRE_BASE_DATOS + "'", nativeQuery = true)
    public int obtenerSiguienteId();
    
    //Obtiene un listado por dominio
    @Query(value = "SELECT * FROM vehiculo b WHERE b.dominio like %:dom%", nativeQuery = true)
    public List<Vehiculo> listarPorDominio(@Param("dom") String dom);
    
    //Obtiene un listado por numero interno
    @Query(value = "SELECT * FROM pestania b WHERE b.numeroInterno like %:numero%", nativeQuery = true)
    public List<Vehiculo> listarPorNumeroInterno(@Param("numero") String numero);
    
    //Obtiene un listado por dominio filtrado por tipo de vehiculo remolque
    //public List<Vehiculo> listarPorDominioFiltroRemolque(String dominio);
    
}
