//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.ViajeTramoCliente;
import ar.com.draimo.jitws.model.ViajeTramoClienteRemito;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO viajeTramoClienteRemito
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IViajeTramoClienteRemitoDAO extends JpaRepository<ViajeTramoClienteRemito, Integer> {
    
    //Obtiene el siguiente id
    public ViajeTramoClienteRemito findTopByOrderByIdDesc();
    
    //Obtiene una lista por viajeTramoCliente
    public List<ViajeTramoClienteRemito> findByViajeTramoCliente(ViajeTramoCliente viajeTramoCliente);
    
}
