//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.OrigenDestino;
import ar.com.draimo.jitws.model.Provincia;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO OrigenDestino
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IOrigenDestinoDAO extends JpaRepository<OrigenDestino, Integer> {
    
    //Obtiene el ultimo registro
    public OrigenDestino findTopByOrderByIdDesc();
    
    //Obtiene una lista por nombre
    public List<OrigenDestino> findByNombreContaining(String nombre);
    
    //Obtiene una lista por provincia
    public List<OrigenDestino> findByProvincia(Optional<Provincia> provincia);
    
}