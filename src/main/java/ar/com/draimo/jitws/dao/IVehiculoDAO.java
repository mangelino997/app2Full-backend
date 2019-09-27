//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;
import ar.com.draimo.jitws.model.Empresa;
import ar.com.draimo.jitws.model.Vehiculo;
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
    
    //Obtiene el ultimo registro
    public Vehiculo findTopByOrderByIdDesc();
    
    //Obtiene un listado por alias ordenado
    public List<Vehiculo> findByAliasContainingOrderByAlias(String alias);
    
    //Obtiene un listado por empresa ordenado por alias
    public List<Vehiculo> findByEmpresaOrderByAlias(Empresa empresa);
    
    //Obtiene un listado por alias y empresa ordenado por alias
    public List<Vehiculo> findByAliasContainingAndEmpresaOrderByAlias(String alias, Empresa empresa);
    
    //Obtiene un listado por alias filtrado por tipo de vehiculo no remolque
    public List<Vehiculo> findByAliasContainingAndConfiguracionVehiculo_TipoVehiculo_EsRemolqueFalse(String alias);
    
    //Obtiene un listado por alias filtrado por tipo de vehiculo remolque
    public List<Vehiculo> findByAliasContainingAndConfiguracionVehiculo_TipoVehiculo_EsRemolqueTrue(String alias);
    
    //Obtiene un listado por alias filtrado por empresa y tipo de vehiculo remolque
    public List<Vehiculo> findByAliasContainingAndEmpresaAndConfiguracionVehiculo_TipoVehiculo_EsRemolqueTrue(String alias, Empresa empresa);
   
    //Obtiene un listado por empresa filtrado por tipo de vehiculo remolque
    public List<Vehiculo> findByEmpresaAndConfiguracionVehiculo_TipoVehiculo_EsRemolqueTrue(Empresa empresa);
    
    //Obtiene una lista por tipo de vehiculo y/o marca de vehiculo y/o empresa
    @Query(value = "SELECT v.* FROM vehiculo v left join configuracionvehiculo c "
            + "on v.idConfiguracionVehiculo=c.id WHERE "
            + "(:idEmpresa=0 OR v.idEmpresa=:idEmpresa) AND "
            + "(:idTipoVehiculo=0 OR c.idTipoVehiculo=:idTipoVehiculo) AND "
            + "(:idMarcaVehiculo=0 OR c.idMarcaVehiculo=:idMarcaVehiculo)", nativeQuery = true)
    public List<Vehiculo> listarPorConfig(@Param("idEmpresa") int idEmpresa, 
            @Param("idTipoVehiculo") int idTipoVehiculo, @Param("idMarcaVehiculo") int idMarcaVehiculo);
    
}