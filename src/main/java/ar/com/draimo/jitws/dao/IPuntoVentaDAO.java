//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.Empresa;
import ar.com.draimo.jitws.model.PuntoVenta;
import ar.com.draimo.jitws.model.Sucursal;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO PuntoVenta
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IPuntoVentaDAO extends JpaRepository<PuntoVenta, Integer> {
    
    //Obtiene el siguiente id
    public PuntoVenta findTopByOrderByIdDesc();
    
    //Obtiene una lista por sucursal
    public List<PuntoVenta> findBySucursal(Optional<Sucursal> sucursal);
    
    //Obtiene una lista por sucursal y empresa
    public List<PuntoVenta> findBySucursalAndEmpresa(Optional<Sucursal> sucursal, Optional<Empresa> empresa);
     
    //Obtiene un registro por puntoVenta y codigoAfip
    public PuntoVenta findByPuntoVentaAndCodigoAfip(int puntoVenta, String codigoAfip);
     
}
