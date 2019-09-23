//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.Empresa;
import ar.com.draimo.jitws.model.PlanCuenta;
import ar.com.draimo.jitws.model.RubroProducto;
import ar.com.draimo.jitws.model.RubroProductoCuentaContable;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO RubroProductoCuentaContable
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IRubroProductoCuentaContableDAO extends JpaRepository<RubroProductoCuentaContable, Integer> {
    
    //Obtiene el siguiente id
    public RubroProductoCuentaContable findTopByOrderByIdDesc();
    
    //Obtiene una lista por Empresa
    public List<RubroProductoCuentaContable> findByEmpresa(Empresa empresa);
    
    //Obtiene una lista por RubroProducto
    public List<RubroProductoCuentaContable> findByRubroProducto(RubroProducto rubroProducto);
    
    //elimina una lista por RubroProducto
    public void deleteByRubroProducto(RubroProducto rubroProducto);
    
    //Obtiene una lista por PlanCuenta
    public List<RubroProductoCuentaContable> findByPlanCuentaCompra(PlanCuenta planCuenta);
    
}
