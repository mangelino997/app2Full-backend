//Paquete al que pertenece la interfaz
package ar.com.wecoode.jitws.dao;

import ar.com.wecoode.jitws.model.ViajePrecio;
import ar.com.wecoode.jitws.model.ViajeTarifa;
import ar.com.wecoode.jitws.model.ViajeTipo;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO ViajePrecio
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IViajePrecioDAO extends JpaRepository<ViajePrecio, Integer> {
    
    //Obtiene el siguiente id
    public ViajePrecio findTopByOrderByIdDesc();
    
    //Obtiene el costo por idViajeTipo y idViajeTarifa
    public ViajePrecio findByViajeTipoAndViajeTarifa(Optional<ViajeTipo> viajeTipo, Optional<ViajeTarifa> viajeTarifa);
    
}
