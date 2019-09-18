//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.AfipComprobante;
import ar.com.draimo.jitws.model.TipoComprobante;
import ar.com.draimo.jitws.model.VentaComprobante;
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
    
    //Obtiene el siguiente id
    public AfipComprobante findTopByOrderByIdDesc();
    
    //Obtiene por codigo de afip
    public AfipComprobante findByCodigoAfip(String codigoAfip);
    
    //Obtiene el siguiente id
    public AfipComprobante findByTipoComprobanteAndLetra(Optional<TipoComprobante> tipoComprobante, String letra);
    
    //Obtiene una lista por tipo de comprobante
    public List<AfipComprobante> findByTipoComprobante(TipoComprobante tipoComprobante);
    
    //Obtiene un listado de letras por Tipo Comprobante
    @Query(value = "SELECT letra FROM afipcomprobante where idTipoComprobante=:idTipoComprobante group by letra", nativeQuery = true)
    public List<String> listarLetras(@Param("idTipoComprobante") int idTipoComprobante);
    
    
}
