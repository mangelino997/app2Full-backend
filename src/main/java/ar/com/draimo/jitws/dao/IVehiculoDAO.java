//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;
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
    
    //Obtiene el siguiente id
    public Vehiculo findTopByOrderByIdDesc();
    
    //Obtiene un listado por alias
    public List<Vehiculo> findByAliasContaining(String alias);
    
    //Obtiene un listado por alias filtrado por tipo de vehiculo no remolque
    public List<Vehiculo> findByAliasContainingAndConfiguracionVehiculo_TipoVehiculo_EsRemolqueFalse(String alias);
    
    //Obtiene un listado por alias filtrado por tipo de vehiculo remolque
    public List<Vehiculo> findByAliasContainingAndConfiguracionVehiculo_TipoVehiculo_EsRemolqueTrue(String alias);
    
    //Obtiene una lista por tipo de vehiculo, marca de vehiculo y empresa
    @Query(value = "SELECT v.* FROM vehiculo v left join configuracionvehiculo c "
            + "on v.idConfiguracionVehiculo=c.id WHERE "
            + "(:idEmpresa=0 OR v.idEmpresa=:idEmpresa) AND "
            + "(:idTipoVehiculo=0 OR c.idTipoVehiculo=:idTipoVehiculo) AND "
            + "(:idMarcaVehiculo=0 OR c.idMarcaVehiculo=:idMarcaVehiculo)", nativeQuery = true)
    public List<Vehiculo> listarPorConfig(@Param("idEmpresa") int idEmpresa, 
            @Param("idTipoVehiculo") int idTipoVehiculo, @Param("idMarcaVehiculo") int idMarcaVehiculo);
    
}
