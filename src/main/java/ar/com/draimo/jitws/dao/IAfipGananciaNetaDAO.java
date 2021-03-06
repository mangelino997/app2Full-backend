//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;
import ar.com.draimo.jitws.model.AfipAlicuotaGanancia;
import ar.com.draimo.jitws.model.AfipGananciaNeta;
import java.math.BigDecimal;
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
    
    //Obtiene el ultimo registro
    public AfipGananciaNeta findTopByOrderByIdDesc();
    
    //Obtiene una lista por AfipTipoBeneficio ordenada por importe de - a +
    public List<AfipGananciaNeta> findByAfipAlicuotaGananciaOrderByImporte(AfipAlicuotaGanancia afipAlicuotaGanancia);
    
    //Obtiene una lista por anio ordenada por importe de - a +
    public List<AfipGananciaNeta> findByAnioOrderByImporte(short anio);
    
    //Obtiene una lista por anio e importe
    public List<AfipGananciaNeta> findByAnioAndImporte(short anio, BigDecimal importe);
    
    //Obtiene la lista completa ordenada por importe de - a +
    public List<AfipGananciaNeta> findAllByOrderByImporte();
    
    /*Obtiene una lista por anio
    * Si el mes pasado por parametro es igual a cero muestra el importe
    * Si el mes tiene un valor se saca el porcentaje del importe que corresponde a ese mes.
    */
    @Query(value = "SELECT id, version, anio, CASE(:idMes) when 0 then importe else " 
            + "ROUND(importe/12*:idMes, 2) end as importe, importeFijo,"
            + "idAfipAlicuotaGanancia FROM afipganancianeta "
            + "WHERE anio=:anio order by importe", nativeQuery = true)
    public List<AfipGananciaNeta> listarPorFiltros(@Param("anio") short anio,@Param("idMes") int idMes);
    
}