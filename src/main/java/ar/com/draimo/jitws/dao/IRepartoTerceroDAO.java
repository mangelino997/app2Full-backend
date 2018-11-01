//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.RepartoTercero;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO Reparto Tercero
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IRepartoTerceroDAO extends JpaRepository<RepartoTercero, Integer> {
    
    //Obtiene el siguiente id
    public RepartoTercero findTopByOrderByIdDesc();
    
}
