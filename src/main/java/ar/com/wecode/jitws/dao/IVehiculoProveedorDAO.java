//Paquete al que pertenece la interfaz
package ar.com.wecode.jitws.dao;

import ar.com.wecode.jitws.constant.NombreConstant;
import ar.com.wecode.jitws.model.VehiculoProveedor;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Interfaz DAO Vehiculo Proveedor
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IVehiculoProveedorDAO extends JpaRepository<VehiculoProveedor, Integer> {
    
    public final String NOMBRE_TABLA = "vehiculoproveedor";
    
    //Obtiene el siguiente id
    @Query(value = "SELECT Auto_increment FROM information_schema.tables "
            + "WHERE table_name='" + NOMBRE_TABLA +"'" + " AND table_schema='" 
            + NombreConstant.NOMBRE_BASE_DATOS + "'", nativeQuery = true)
    public int obtenerSiguienteId();
    
    //Obtiene un listado por dominio
    @Query(value = "SELECT * FROM vehiculoproveedor b WHERE b.nombre like %:dom%", nativeQuery = true)
    public List<VehiculoProveedor> listarPorDominio(@Param("dom") String dom);
    
    //Obtiene un listado por dominio filtrado por tipo de vehiculo remolque
    //public List<VehiculoProveedor> listarPorDominioFiltroRemolque(String dominio);
    
}
