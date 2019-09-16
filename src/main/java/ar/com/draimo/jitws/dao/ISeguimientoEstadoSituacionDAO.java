//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.SeguimientoEstado;
import ar.com.draimo.jitws.model.SeguimientoEstadoSituacion;
import ar.com.draimo.jitws.model.SeguimientoSituacion;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO SeguimientoEstadoSituacion
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface ISeguimientoEstadoSituacionDAO extends JpaRepository<SeguimientoEstadoSituacion, Integer> {
    
    //Obtiene el siguiente id
    public SeguimientoEstadoSituacion findTopByOrderByIdDesc();
    
    //Obtiene una lista por seguimientoEstado
    public List<SeguimientoEstadoSituacion> findBySeguimientoEstado(SeguimientoEstado seguimientoEstado);
    
    //Obtiene una lista ordenada por seguimientoSituacion
    public List<SeguimientoEstadoSituacion> findBySeguimientoSituacion(SeguimientoSituacion seguimientoSituacion);
    
}
