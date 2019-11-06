//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.CobranzaRetencion;
import ar.com.draimo.jitws.model.Cobranza;
import ar.com.draimo.jitws.model.TipoRetencion;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO CobranzaRetencion
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface ICobranzaRetencionDAO extends JpaRepository<CobranzaRetencion, Integer> {
    
    //Obtiene el siguiente id
    public CobranzaRetencion findTopByOrderByIdDesc();
    
    //Obtiene una lista por cobranza
    public List<CobranzaRetencion> findByCobranza(Cobranza cobranza);
    
    //Obtiene una lista por tipoRetencion
    public List<CobranzaRetencion> findByTipoRetencion(TipoRetencion tipoRetencion);
    
    //Obtiene una lista por punto de venta, letra y numero
    public List<CobranzaRetencion> findByPuntoVentaAndLetraAndNumero(int puntoVenta,
            String letra, int numero);
    
}
