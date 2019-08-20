//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.AfipAlicuotaGanancia;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * Interfaz DAO AfipAlicuotaGanancia
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IAfipAlicuotaGananciaDAO extends JpaRepository<AfipAlicuotaGanancia, Integer> {
    
    //Obtiene el siguiente id
    public AfipAlicuotaGanancia findTopByOrderByIdDesc();
    
    //Obtiene todos los registros ordenados de mayor a menor
    public List<AfipAlicuotaGanancia> findAllByOrderByAlicuotaAsc();
    
}
