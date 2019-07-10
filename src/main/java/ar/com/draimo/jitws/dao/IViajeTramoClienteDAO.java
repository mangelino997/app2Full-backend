//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.ViajeTramo;
import ar.com.draimo.jitws.model.ViajeTramoCliente;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO Viaje Tramo Cliente
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IViajeTramoClienteDAO extends JpaRepository<ViajeTramoCliente, Integer> {
    
    //Obtiene el siguiente id
    public ViajeTramoCliente findTopByOrderByIdDesc();
    
    //Obtiene una lista por viaje propio tramo
    public List<ViajeTramoCliente> findByViajeTramo(Optional<ViajeTramo> viajeTramo);
    
}
