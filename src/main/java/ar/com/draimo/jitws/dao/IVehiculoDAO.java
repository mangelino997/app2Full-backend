//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.Empresa;
import ar.com.draimo.jitws.model.MarcaVehiculo;
import ar.com.draimo.jitws.model.TipoVehiculo;
import ar.com.draimo.jitws.model.Vehiculo;
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
    
    //Obtiene una lista por tipo de vehiculo, marca de vehiculo y empresa
    public List<Vehiculo> findByEmpresaAndConfiguracionVehiculo_TipoVehiculoAndConfiguracionVehiculo_MarcaVehiculo(
            Empresa empresa, TipoVehiculo tipoVehiculo, MarcaVehiculo marcaVehiculo);
    
}
