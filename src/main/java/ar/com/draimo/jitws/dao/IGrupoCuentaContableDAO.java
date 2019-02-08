//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;
import ar.com.draimo.jitws.model.GrupoCuentaContable;
import ar.com.draimo.jitws.model.TipoCuentaContable;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO Provincia
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IGrupoCuentaContableDAO extends JpaRepository<GrupoCuentaContable, Integer> {
    
    //Obtiene el siguiente id
    public GrupoCuentaContable findTopByOrderByIdDesc();
    
    //Obtiene una lista por nombre
    public List<GrupoCuentaContable> findByNombreContaining(String nombre);
    
    //Obtiene una provincia por pais
    public List<GrupoCuentaContable> findByTipoCuentaContable(TipoCuentaContable elemento);
    
}
