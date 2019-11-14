//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.TipoDocumentoCartera;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO TipoDocumentoCartera
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface ITipoDocumentoCarteraDAO extends JpaRepository<TipoDocumentoCartera, Integer> {
    
    //Obtiene el ultimo registro
    public TipoDocumentoCartera findTopByOrderByIdDesc();
    
    //Obtiene una lista por nombre
    public List<TipoDocumentoCartera> findByNombreContaining(String nombre);
    
}