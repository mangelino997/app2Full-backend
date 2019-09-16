//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.SeguimientoEstado;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO SeguimientoEstado
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface ISeguimientoEstadoDAO extends JpaRepository<SeguimientoEstado, Integer> {
    
    //Obtiene el siguiente id
    public SeguimientoEstado findTopByOrderByIdDesc();
    
    //Obtiene una lista por nombre
    public List<SeguimientoEstado> findByNombreContaining(String nombre);
    
    //Obtiene una lista por repartoMostrar = true
    public List<SeguimientoEstado> findByRepartoEntranteTrue();
    
}
