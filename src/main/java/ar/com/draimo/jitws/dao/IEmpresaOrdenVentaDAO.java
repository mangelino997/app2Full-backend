//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.Empresa;
import ar.com.draimo.jitws.model.EmpresaOrdenVenta;
import ar.com.draimo.jitws.model.OrdenVenta;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO Empresa ordenventa
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IEmpresaOrdenVentaDAO extends JpaRepository<EmpresaOrdenVenta, Integer> {
    
    //Obtiene el siguiente id
    public EmpresaOrdenVenta findTopByOrderByIdDesc();
    
    //Obtiene una lista por ordenVenta
    public List<EmpresaOrdenVenta> findByOrdenVenta(OrdenVenta ordenVenta);
    
    //Obtiene una lista por empresa 
    public List<EmpresaOrdenVenta> findByEmpresa(Empresa empresa);
    
    //Obtiene por compania de empresa y ordenVenta
    public List<EmpresaOrdenVenta> findByEmpresaAndOrdenVenta(Empresa empresa, OrdenVenta ordenVenta);
    
    //Elimina por orden de venta
    public void deleteByOrdenVenta(OrdenVenta ordenVenta);
    
}
