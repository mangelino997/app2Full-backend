//Paquete al que pertenece la interfaz
package ar.com.wecoode.jitws.dao;

import ar.com.wecoode.jitws.model.Pais;
import ar.com.wecoode.jitws.model.Provincia;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO Provincia
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IProvinciaDAO extends JpaRepository<Provincia, Integer> {
    
    //Obtiene el siguiente id
    public Provincia findTopByOrderByIdDesc();
    
    //Obtiene una lista por nombre
    public List<Provincia> findByNombreContaining(String nombre);
    
    //Obtiene una provincia por pais
    public List<Provincia> findByPais(Optional<Pais> elemento);
    
}
