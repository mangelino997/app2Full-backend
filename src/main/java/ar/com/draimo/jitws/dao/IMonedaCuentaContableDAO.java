//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.Empresa;
import ar.com.draimo.jitws.model.Moneda;
import ar.com.draimo.jitws.model.MonedaCuentaContable;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO Provincia
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IMonedaCuentaContableDAO extends JpaRepository<MonedaCuentaContable, Integer> {
    
    //Obtiene el siguiente id
    public MonedaCuentaContable findTopByOrderByIdDesc();
    
    
    //Obtiene un listado de moneda cuenta contable por moneda
    public List<MonedaCuentaContable> findByMoneda(Moneda elemento);
    
    //Obtiene un listado de moneda cuenta contable por empresa
    public List<MonedaCuentaContable> findByEmpresa(Empresa elemento);
    
    //Obtiene un registro por moneda y empresa
    public MonedaCuentaContable findByMonedaAndEmpresa(Moneda moneda, Empresa empresa);
    
}
