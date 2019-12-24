//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.ProveedorCuentaBancaria;
import ar.com.draimo.jitws.model.Proveedor;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO ProveedorCuentaBancaria
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IProveedorCuentaBancariaDAO extends JpaRepository<ProveedorCuentaBancaria, Integer> {
    
    //Obtiene el ultimo registro
    public ProveedorCuentaBancaria findTopByOrderByIdDesc();
    
    //Obtiene una lista por cuentaBancaria
    public List<ProveedorCuentaBancaria> findByProveedor(Proveedor proveedor);
   
    
}