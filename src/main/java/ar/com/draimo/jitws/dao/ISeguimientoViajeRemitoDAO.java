//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.ViajeRemito;
import ar.com.draimo.jitws.model.SeguimientoViajeRemito;
import ar.com.draimo.jitws.model.SeguimientoEstado;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO SeguimientoViajeRemito
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface ISeguimientoViajeRemitoDAO extends JpaRepository<SeguimientoViajeRemito, Integer> {
    
    //Obtiene el ultimo registro
    public SeguimientoViajeRemito findTopByOrderByIdDesc();
    
    //Obtiene una lista ordenada por fecha
    public List<SeguimientoViajeRemito> findByOrderByFechaDesc();
    
    //Obtiene una lista por seguimientoEstado
    public List<SeguimientoViajeRemito> findBySeguimientoEstado(SeguimientoEstado seguimientoEstado);
    
    //Obtiene una lista ordenada por ViajeRemito
    public List<SeguimientoViajeRemito> findByViajeRemito(ViajeRemito viajeRemito);
    
    //elimina una lista por ViajeRemito
    public void deleteByViajeRemito(ViajeRemito viajeRemito);
    
}