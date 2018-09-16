//Paquete al que pertenece la interfaz
package ar.com.wecoode.jitws.dao;

import ar.com.wecoode.jitws.model.ViajePropio;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO Viaje Propio
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IViajePropioDAO extends JpaRepository<ViajePropio, Integer> {
    
    //Obtiene el siguiente id
    public ViajePropio findTopByOrderByIdDesc();
    
}
