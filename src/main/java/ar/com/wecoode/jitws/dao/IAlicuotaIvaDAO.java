//Paquete al que pertenece la interfaz
package ar.com.wecoode.jitws.dao;

import ar.com.wecoode.jitws.model.AlicuotaIva;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO AlicuotaIva
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IAlicuotaIvaDAO extends JpaRepository<AlicuotaIva, Integer> {
    
    //Obtiene el siguiente id
    public AlicuotaIva findTopByOrderByIdDesc();
    
}
