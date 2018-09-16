//Paquete al que pertenece la interfaz
package ar.com.wecoode.jitws.dao;

import ar.com.wecoode.jitws.model.TipoDocumento;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO Tipo de documento
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface ITipoDocumentoDAO extends JpaRepository<TipoDocumento, Integer> {
    
    //Obtiene el siguiente id
    public TipoDocumento findTopByOrderByIdDesc();
    
    //Obtiene una lista por nombre
    public List<TipoDocumento> findByNombreContaining(String nombre);
    
}
