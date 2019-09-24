//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.AfipComprobante;
import ar.com.draimo.jitws.model.TipoComprobante;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Interfaz DAO AfipComprobante
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IAfipComprobanteDAO extends JpaRepository<AfipComprobante, Integer> {
    
    //Obtiene el ultimo registro
    public AfipComprobante findTopByOrderByIdDesc();
    
    //Obtiene un registro por codigo de afip
    public AfipComprobante findByCodigoAfip(String codigoAfip);
    
    //Obtiene un registro por tipocomprobante y letra
    public AfipComprobante findByTipoComprobanteAndLetra(Optional<TipoComprobante> tipoComprobante, String letra);
    
    //Obtiene una lista por tipo de comprobante
    public List<AfipComprobante> findByTipoComprobante(TipoComprobante tipoComprobante);
    
    //Obtiene un listado de letras por Tipo Comprobante
    @Query(value = "SELECT letra FROM afipcomprobante where (:idTipoComprobante=0 OR idTipoComprobante=:idTipoComprobante) group by letra", nativeQuery = true)
    public List<String> listarLetras(@Param("idTipoComprobante") int idTipoComprobante);
    
    
}
