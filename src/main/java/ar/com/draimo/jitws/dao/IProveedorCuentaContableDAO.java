//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.Empresa;
import ar.com.draimo.jitws.model.PlanCuenta;
import ar.com.draimo.jitws.model.Proveedor;
import ar.com.draimo.jitws.model.ProveedorCuentaContable;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO ProveedorCuentaContable
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IProveedorCuentaContableDAO extends JpaRepository<ProveedorCuentaContable, Integer> {
    
    //Obtiene el siguiente id
    public ProveedorCuentaContable findTopByOrderByIdDesc();
    
    //Obtiene una lista por Empresa
    public List<ProveedorCuentaContable> findByEmpresa(Empresa empresa);
    
    //Obtiene una lista por Proveedor
    public List<ProveedorCuentaContable> findByProveedor(Proveedor proveedor);
    
    //Elimina por Proveedor
    public List<ProveedorCuentaContable> deleteByProveedor(Proveedor proveedor);
    
    //Obtiene una lista por PlanCuenta
    public List<ProveedorCuentaContable> findByPlanCuentaCompra(PlanCuenta planCuenta);
    
}
