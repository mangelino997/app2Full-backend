//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.OrdenRecoleccion;
import ar.com.draimo.jitws.model.SeguimientoOrdenRecoleccion;
import ar.com.draimo.jitws.model.SeguimientoEstado;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO Seguimiento OrdenRecoleccion
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface ISeguimientoOrdenRecoleccionDAO extends JpaRepository<SeguimientoOrdenRecoleccion, Integer> {
    
    //Obtiene el ultimo registro
    public SeguimientoOrdenRecoleccion findTopByOrderByIdDesc();
    
    //Obtiene una lista ordenada por fecha
    public List<SeguimientoOrdenRecoleccion> findByOrderByFechaDesc();
    
    //Obtiene una lista por seguimientoEstado
    public List<SeguimientoOrdenRecoleccion> findBySeguimientoEstado(SeguimientoEstado seguimientoEstado);
    
    //Obtiene una lista ordenada por ordenRecoleccion
    public List<SeguimientoOrdenRecoleccion> findByOrdenRecoleccion(OrdenRecoleccion ordenRecoleccion);
    
}