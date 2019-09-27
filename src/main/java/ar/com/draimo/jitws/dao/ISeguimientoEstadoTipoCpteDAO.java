//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.SeguimientoEstado;
import ar.com.draimo.jitws.model.SeguimientoEstadoTipoCpte;
import ar.com.draimo.jitws.model.TipoComprobante;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO SeguimientoEstadoTipoCte
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface ISeguimientoEstadoTipoCpteDAO extends JpaRepository<SeguimientoEstadoTipoCpte, Integer> {
    
    //Obtiene el ultimo registro
    public SeguimientoEstadoTipoCpte findTopByOrderByIdDesc();
    
    //Obtiene una lista por seguimientoEstado
    public List<SeguimientoEstadoTipoCpte> findBySeguimientoEstado(SeguimientoEstado seguimientoEstado);
    
    //Obtiene una lista ordenada por tipoComprobante
    public List<SeguimientoEstadoTipoCpte> findByTipoComprobante(TipoComprobante tipoComprobante);
    
}