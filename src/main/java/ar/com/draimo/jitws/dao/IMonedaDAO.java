//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.Moneda;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO Moneda
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IMonedaDAO extends JpaRepository<Moneda, Integer> {
    
    //Obtiene el ultimo registro
    public Moneda findTopByOrderByIdDesc();
    
    //Obtiene una lista por nombre
    public List<Moneda> findByNombreContaining(String nombre);
    
    //Obtiene la moneda por defecto
    public Moneda findByPorDefectoTrue();
    
    //Obtiene lista de monedas activas
    public List<Moneda> findByEstaActivoTrue();
    
}