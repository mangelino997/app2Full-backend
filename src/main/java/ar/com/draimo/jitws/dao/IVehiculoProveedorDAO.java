//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.Proveedor;
import ar.com.draimo.jitws.model.VehiculoProveedor;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO Vehiculo Proveedor
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IVehiculoProveedorDAO extends JpaRepository<VehiculoProveedor, Integer> {
    
    //Obtiene el ultimo registro
    public VehiculoProveedor findTopByOrderByIdDesc();
    
    //Obtiene un listado por alias ordenado
    public List<VehiculoProveedor> findByAliasContainingOrderByAlias(String alias);
    
    //Obtiene un listado por filtro remolque ordenado por alias
    public List<VehiculoProveedor> findByAliasContainingAndTipoVehiculo_EsRemolqueTrueOrderByAlias(String alias);
    
    //Obtiene una lista por proveedor
    public List<VehiculoProveedor> findByProveedor(Proveedor proveedor);
    
}