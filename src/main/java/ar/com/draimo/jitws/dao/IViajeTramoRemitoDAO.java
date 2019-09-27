//Paquete al que pertence la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.ViajeRemito;
import ar.com.draimo.jitws.model.ViajeTramo;
import ar.com.draimo.jitws.model.ViajeTramoRemito;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interfaz ViajeTramoRemito DAO
 * Metodos contra la base de datos
 * @author blas
 */

@Repository
public interface IViajeTramoRemitoDAO extends JpaRepository<ViajeTramoRemito, Integer> {
    
    //Obtiene el ultimo registro
    public ViajeTramoRemito findTopByOrderByIdDesc();
    
    //Obtiene un listado por viajeRemito
    public List<ViajeTramoRemito> findByViajeRemito(ViajeRemito viajeRemito);
    
    //Obtiene un listado por viajeRemito y viajeTramo
    public ViajeTramoRemito findByViajeRemitoAndViajeTramo(ViajeRemito viajeRemito, ViajeTramo viajeTramo);
    
    //Elimina por viajeRemito y viajeTramo
    public void deleteByViajeRemitoAndViajeTramo(ViajeRemito viajeRemito, ViajeTramo viajeTramo);
    
    //Obtiene un registro por remitente, destinatario , puntoVenta, letra, numero y tipoComprobante
    public ViajeRemito findByClienteRemitenteAndClienteDestinatarioAndPuntoVentaAndNumeroAndLetraAndTipoComprobante();
}