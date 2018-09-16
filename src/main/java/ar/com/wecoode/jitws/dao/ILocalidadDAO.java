//Paquete al que pertenece la interfaz
package ar.com.wecoode.jitws.dao;

import ar.com.wecoode.jitws.model.Localidad;
import ar.com.wecoode.jitws.model.Provincia;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * Interfaz DAO Localidad
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface ILocalidadDAO extends JpaRepository<Localidad, Integer> {
    
    //Obtiene el siguiente id
    public Localidad findTopByOrderByIdDesc();
    
    //Obtiene una lista por nombre
    public List<Localidad> findByNombreContaining(String nombre);
    
    //Obtiene una lista por id provincia
    public List<Localidad> findByProvincia(Optional<Provincia> elemento);
    
}
