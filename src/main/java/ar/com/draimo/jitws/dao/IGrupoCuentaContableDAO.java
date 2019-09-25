//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.GrupoCuentaContable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO GrupoCuentaContable
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IGrupoCuentaContableDAO extends JpaRepository<GrupoCuentaContable, Integer> {
    
    //Obtiene el ultimo registro
    public GrupoCuentaContable findTopByOrderByIdDesc();
    
}