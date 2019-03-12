//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.OrdenRecoleccion;
import ar.com.draimo.jitws.model.RepartoTercero;
import ar.com.draimo.jitws.model.RepartoTerceroComprobante;
import ar.com.draimo.jitws.model.VentaComprobante;
import ar.com.draimo.jitws.model.ViajeRemito;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO RepartoTerceroComprobante
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IRepartoTerceroComprobanteDAO extends JpaRepository<RepartoTerceroComprobante, Integer> {
    
    //Obtiene el siguiente id
    public RepartoTerceroComprobante findTopByOrderByIdDesc();
    
    //Obtiene un listado de RepartoTerceroComprobante por idRepartoTercero
    public List<RepartoTerceroComprobante> findByRepartoTercero(RepartoTercero repartoTercero);
    
    //Obtiene un registro por viaje remito
    public RepartoTerceroComprobante findByViajeRemitoAndRepartoTercero(ViajeRemito viajeRemito,
            RepartoTercero repartoTercero);
    
    //Obtiene un registro por ordenRecoleccion
    public RepartoTerceroComprobante findByOrdenRecoleccionAndRepartoTercero(OrdenRecoleccion ordenRecoleccion,
            RepartoTercero repartoTercero);
    
    //Obtiene un registro por ventaComprobante
    public RepartoTerceroComprobante findByVentaComprobanteAndRepartoTercero(VentaComprobante ventaComprobante,
            RepartoTercero repartoTercero);
    
}
