//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.OrigenDestino;
import ar.com.draimo.jitws.model.Tramo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO Tramo
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface ITramoDAO extends JpaRepository<Tramo, Integer> {
    
    //Obtiene el ultimo registro
    public Tramo findTopByOrderByIdDesc();
    
    //Obtiene un listado por nombre de origen
    public List<Tramo> findByOrigen_NombreContaining(String nombre);
    
    //Obtiene un listado por nombre de destino
    public List<Tramo> findByDestino_NombreContaining(String nombre);
    
    //Obtiene una lista por origen
    public List<Tramo> findByOrigen(OrigenDestino origen);
    
    //Obtiene una lista por destino
    public List<Tramo> findByDestino(OrigenDestino destino);
    
    //Obtiene una lista por origen y destino
    public List<Tramo> findByOrigenAndDestino(OrigenDestino origen, OrigenDestino destino);
    
}