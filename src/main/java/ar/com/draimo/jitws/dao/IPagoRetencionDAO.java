//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.PagoRetencion;
import ar.com.draimo.jitws.model.Pago;
import ar.com.draimo.jitws.model.TipoRetencion;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO PagoRetencion
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IPagoRetencionDAO extends JpaRepository<PagoRetencion, Integer> {
    
    //Obtiene el siguiente id
    public PagoRetencion findTopByOrderByIdDesc();
    
    //Obtiene una lista por Pago
    public List<PagoRetencion> findByPago(Pago pago);
    
    //Obtiene una lista por tipoRetencion
    public List<PagoRetencion> findByTipoRetencion(TipoRetencion tipoRetencion);
    
    //Obtiene una lista por punto de venta, letra y numero
    public List<PagoRetencion> findByPuntoVentaAndLetraAndNumero(int puntoVenta,
            String letra, int numero);
    
}
