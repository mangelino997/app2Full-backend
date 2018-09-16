//Paquete al que pertenece la interfaz
package ar.com.wecoode.jitws.dao;

import ar.com.wecoode.jitws.model.Vehiculo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

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
    
    //Obtiene un listado por alias filtrado por tipo de vehiculo remolque
    public List<Vehiculo> findByAliasContainingAndConfiguracionVehiculo_TipoVehiculo_EsRemolqueTrue(String alias);
    
}
