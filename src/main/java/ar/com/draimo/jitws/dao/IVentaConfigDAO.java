//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.VentaConfig;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO VentaConfig
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IVentaConfigDAO extends JpaRepository<VentaConfig, Integer> {
    
    //Obtiene el ultimo registro
    public VentaConfig findTopByOrderByIdDesc();
    
}