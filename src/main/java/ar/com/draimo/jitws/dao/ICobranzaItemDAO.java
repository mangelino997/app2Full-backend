//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.CobranzaItem;
import ar.com.draimo.jitws.model.Cobranza;
import ar.com.draimo.jitws.model.VentaComprobante;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO CobranzaItem
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface ICobranzaItemDAO extends JpaRepository<CobranzaItem, Integer> {
    
    //Obtiene el siguiente id
    public CobranzaItem findTopByOrderByIdDesc();
    
    //Obtiene una lista por cobranza
    public List<CobranzaItem> findByCobranza(Cobranza cobranza);
    
    //Obtiene una lista por tipoRetencion
    public List<CobranzaItem> findByVentaComprobante(VentaComprobante ventaComprobante);
    
}
