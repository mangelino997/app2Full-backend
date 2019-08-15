//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;
import ar.com.draimo.jitws.model.AfipAlicuotaGanancia;
import ar.com.draimo.jitws.model.AfipGananciaNeta;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO AfipGananciaNeta
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IAfipGananciaNetaDAO extends JpaRepository<AfipGananciaNeta, Integer> {
    
    //Obtiene el siguiente id
    public AfipGananciaNeta findTopByOrderByIdDesc();
    
    //Obtiene una lista por AfipTipoBeneficio
    public List<AfipGananciaNeta> findByAfipAlicuotaGananciaOrderByImporte(AfipAlicuotaGanancia afipAlicuotaGanancia);
    
    //Obtiene una lista por anio
    public List<AfipGananciaNeta> findByAnioOrderByImporte(short anio);
    
}
