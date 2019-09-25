//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.Categoria;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO Categoria
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface ICategoriaDAO extends JpaRepository<Categoria, Integer> {
    
    //Obtiene el ultimo registro
    public Categoria findTopByOrderByIdDesc();
    
    //Obtiene una lista por nombre ordenado por nombre
    public List<Categoria> findByNombreContainingOrderByNombreAsc(String nombre);
    
    //Obtiene la lista completa ordenadapor nombre
    public List<Categoria> findByOrderByNombreAsc();
    
}