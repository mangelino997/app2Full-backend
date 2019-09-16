//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.SeguimientoEstado;
import ar.com.draimo.jitws.model.SeguimientoEstadoTipoCte;
import ar.com.draimo.jitws.model.TipoComprobante;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO SeguimientoEstadoTipoCte
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface ISeguimientoEstadoTipoCteDAO extends JpaRepository<SeguimientoEstadoTipoCte, Integer> {
    
    //Obtiene el siguiente id
    public SeguimientoEstadoTipoCte findTopByOrderByIdDesc();
    
    //Obtiene una lista por seguimientoEstado
    public List<SeguimientoEstadoTipoCte> findBySeguimientoEstado(SeguimientoEstado seguimientoEstado);
    
    //Obtiene una lista ordenada por tipoComprobante
    public List<SeguimientoEstadoTipoCte> findByTipoComprobante(TipoComprobante tipoComprobante);
    
}
