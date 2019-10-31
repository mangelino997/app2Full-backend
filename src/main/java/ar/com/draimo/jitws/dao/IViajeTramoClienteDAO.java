//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.ViajeTramo;
import ar.com.draimo.jitws.model.ViajeTramoCliente;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO Viaje Tramo Cliente
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IViajeTramoClienteDAO extends JpaRepository<ViajeTramoCliente, Integer> {
    
    //Obtiene el ultimo registro
    public ViajeTramoCliente findTopByOrderByIdDesc();
    
    //Obtiene una lista por viaje tramo
    public List<ViajeTramoCliente> findByViajeTramo(ViajeTramo viajeTramo);
    
}