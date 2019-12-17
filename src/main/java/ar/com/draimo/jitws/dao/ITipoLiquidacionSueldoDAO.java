//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.TipoLiquidacionSueldo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO Tipo de Liquidacion de Sueldo
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface ITipoLiquidacionSueldoDAO extends JpaRepository<TipoLiquidacionSueldo, Integer> {
    
    //Obtiene el ultimo registro
    public TipoLiquidacionSueldo findTopByOrderByIdDesc();
    
    //Obtiene una lista por nombre
    public List<TipoLiquidacionSueldo> findByNombreContaining(String nombre);
    
}