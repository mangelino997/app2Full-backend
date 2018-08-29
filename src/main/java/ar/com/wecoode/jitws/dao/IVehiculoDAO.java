//Paquete al que pertenece la interfaz
package ar.com.wecoode.jitws.dao;

import ar.com.wecoode.jitws.constant.NombreConstant;
import ar.com.wecoode.jitws.model.Vehiculo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

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
    public List<Vehiculo> findByDominioContaining(String dominio);
    
    //Obtiene un listado por numero interno
    public List<Vehiculo> findByNumeroInternoContaining(String numeroInterno);
    
    //Obtiene un listado por dominio filtrado por tipo de vehiculo remolque
    //public List<Vehiculo> listarPorDominioFiltroRemolque(String dominio);
    
}
