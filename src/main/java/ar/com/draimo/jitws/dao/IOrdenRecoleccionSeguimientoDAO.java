//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.OrdenRecoleccion;
import ar.com.draimo.jitws.model.OrdenRecoleccionSeguimiento;
import ar.com.draimo.jitws.model.SeguimientoEstado;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO OrdenRecoleccionSeguimiento
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IOrdenRecoleccionSeguimientoDAO extends JpaRepository<OrdenRecoleccionSeguimiento, Integer> {
    
    //Obtiene el siguiente id
    public OrdenRecoleccionSeguimiento findTopByOrderByIdDesc();
    
    //Obtiene una lista ordenada por fecha
    public List<OrdenRecoleccionSeguimiento> findByOrderByFechaDesc();
    
    //Obtiene una lista por seguimientoEstado
    public List<OrdenRecoleccionSeguimiento> findBySeguimientoEstado(SeguimientoEstado seguimientoEstado);
    
    //Obtiene una lista ordenada por ordenRecoleccion
    public List<OrdenRecoleccionSeguimiento> findByOrdenRecoleccion(OrdenRecoleccion ordenRecoleccion);
    
}
