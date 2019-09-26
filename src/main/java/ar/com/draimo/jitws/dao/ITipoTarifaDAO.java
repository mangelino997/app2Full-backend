//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.TipoTarifa;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO TipoTarifa
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface ITipoTarifaDAO extends JpaRepository<TipoTarifa, Integer> {
    
    //Obtiene el ultimo registro
    public TipoTarifa findTopByOrderByIdDesc();
    
    //Obtiene una lista por nombre
    public List<TipoTarifa> findByNombreContaining(String nombre);
    
    //Obtiene una lista por escala true
    public List<TipoTarifa> findByPorEscalaTrue();
    
    //Obtiene una lista por escala false
    public List<TipoTarifa> findByPorEscalaFalse();
    
}