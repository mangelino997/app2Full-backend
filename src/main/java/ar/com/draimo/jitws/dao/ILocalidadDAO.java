//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.Localidad;
import ar.com.draimo.jitws.model.Provincia;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * Interfaz DAO Localidad
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface ILocalidadDAO extends JpaRepository<Localidad, Integer> {
    
    //Obtiene el ultimo registro
    public Localidad findTopByOrderByIdDesc();
    
    //Obtiene una lista por nombre
    public List<Localidad> findByNombreContaining(String nombre);
    
    //Obtiene una lista por provincia
    public List<Localidad> findByProvincia(Provincia elemento);
    
}