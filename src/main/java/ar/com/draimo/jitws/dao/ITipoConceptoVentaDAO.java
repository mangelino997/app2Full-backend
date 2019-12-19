//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.TipoComprobante;
import ar.com.draimo.jitws.model.TipoConceptoVenta;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO Tipo Concepto Venta
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface ITipoConceptoVentaDAO extends JpaRepository<TipoConceptoVenta, Integer> {
    
    //Obtiene el ultimo registro
    public TipoConceptoVenta findTopByOrderByIdDesc();
    
    //Obtiene una lista por nombre
    public List<TipoConceptoVenta> findByNombreContaining(String nombre);
    
    //Obtiene una lista por tipo de comprobante y registro habilitado
    public List<TipoConceptoVenta> findByTipoComprobanteAndEstaHabilitadoTrue(TipoComprobante tipoComprobante);
    
}