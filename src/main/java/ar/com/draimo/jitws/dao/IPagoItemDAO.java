//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.PagoItem;
import ar.com.draimo.jitws.model.Pago;
import ar.com.draimo.jitws.model.CompraComprobante;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO PagoItem
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IPagoItemDAO extends JpaRepository<PagoItem, Integer> {
    
    //Obtiene el siguiente id
    public PagoItem findTopByOrderByIdDesc();
    
    //Obtiene una lista por Pago
    public List<PagoItem> findByPago(Pago pago);
    
    //Obtiene una lista por CompraComprobante
    public List<PagoItem> findByCompraComprobante(CompraComprobante compraComprobante);
    
}
