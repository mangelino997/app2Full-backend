//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.AfipAlicuotaGanancia;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO AfipAlicuotaGanancia
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IAfipAlicuotaGananciaDAO extends JpaRepository<AfipAlicuotaGanancia, Integer> {
    
    //Obtiene el siguiente id
    public AfipAlicuotaGanancia findTopByOrderByIdDesc();
    
}
