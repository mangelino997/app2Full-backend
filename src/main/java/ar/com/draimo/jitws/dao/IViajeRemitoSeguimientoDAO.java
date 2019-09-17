//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.ViajeRemito;
import ar.com.draimo.jitws.model.ViajeRemitoSeguimiento;
import ar.com.draimo.jitws.model.SeguimientoEstado;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO ViajeRemitoSeguimiento
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IViajeRemitoSeguimientoDAO extends JpaRepository<ViajeRemitoSeguimiento, Integer> {
    
    //Obtiene el siguiente id
    public ViajeRemitoSeguimiento findTopByOrderByIdDesc();
    
    //Obtiene una lista ordenada por fecha
    public List<ViajeRemitoSeguimiento> findByOrderByFechaDesc();
    
    //Obtiene una lista por seguimientoEstado
    public List<ViajeRemitoSeguimiento> findBySeguimientoEstado(SeguimientoEstado seguimientoEstado);
    
    //Obtiene una lista ordenada por ViajeRemito
    public List<ViajeRemitoSeguimiento> findByViajeRemito(ViajeRemito viajeRemito);
    
}
