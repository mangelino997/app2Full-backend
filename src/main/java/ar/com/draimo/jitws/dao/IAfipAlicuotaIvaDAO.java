//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.AfipAlicuotaIva;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO AfipAlicuotaIva
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IAfipAlicuotaIvaDAO extends JpaRepository<AfipAlicuotaIva, Integer> {
    
    //Obtiene el siguiente id
    public AfipAlicuotaIva findTopByOrderByIdDesc();
    
    //Obtiene una lista de activas
    public List<AfipAlicuotaIva> findByEstaActivaTrue();
    
}
