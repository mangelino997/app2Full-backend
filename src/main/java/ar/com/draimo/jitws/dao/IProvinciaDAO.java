//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.Pais;
import ar.com.draimo.jitws.model.Provincia;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO Provincia
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IProvinciaDAO extends JpaRepository<Provincia, Integer> {
    
    //Obtiene el ultimo registro
    public Provincia findTopByOrderByIdDesc();
    
    //Obtiene una lista por nombre
    public List<Provincia> findByNombreContaining(String nombre);
    
    //Obtiene una lista de provincias por pais
    public List<Provincia> findByPaisOrderByNombreAsc(Pais elemento);
    
}