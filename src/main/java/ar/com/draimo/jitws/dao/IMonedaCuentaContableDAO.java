//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.Empresa;
import ar.com.draimo.jitws.model.Moneda;
import ar.com.draimo.jitws.model.MonedaCuentaContable;
import java.util.List;
import java.util.Optional;
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
    public List<MonedaCuentaContable> findByMoneda(Optional<Moneda> elemento);
    
    //Obtiene un listado de moneda cuenta contable por empresa
    public List<MonedaCuentaContable> findByEmpresa(Optional<Empresa> elemento);
    
}
