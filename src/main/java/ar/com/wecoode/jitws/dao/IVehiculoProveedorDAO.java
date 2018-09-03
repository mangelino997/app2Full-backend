//Paquete al que pertenece la interfaz
package ar.com.wecoode.jitws.dao;

import ar.com.wecoode.jitws.constant.NombreConstant;
import ar.com.wecoode.jitws.model.VehiculoProveedor;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

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
    
    //Obtiene un listado por alias
    public List<VehiculoProveedor> findByAliasContaining(String alias);
    
    //Obtiene un listado por filtro remolque
    public List<VehiculoProveedor> findByAliasContainingAndTipoVehiculo_EsRemolqueTrue(String alias);
    
}
