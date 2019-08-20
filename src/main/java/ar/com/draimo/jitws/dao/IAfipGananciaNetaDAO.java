//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;
import ar.com.draimo.jitws.model.AfipAlicuotaGanancia;
import ar.com.draimo.jitws.model.AfipGananciaNeta;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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
    
    //Obtiene una lista por anio
    public List<AfipGananciaNeta> findAllByOrderByImporte();
    
    //Obtiene una lista por filtros
    @Query(value = "SELECT id, version, anio, CASE(:idMes) when 0 then importe else " 
            + "ROUND(importe/12*:idMes, 2) end as importe, importeFijo,"
            + "idAfipAlicuotaGanancia FROM afipganancianeta "
            + "WHERE anio=:anio", nativeQuery = true)
    public List<AfipGananciaNeta> listarPorFiltros(@Param("anio") short anio,@Param("idMes") int idMes);
    
}
