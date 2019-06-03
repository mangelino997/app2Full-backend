//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.Chequera;
import ar.com.draimo.jitws.model.CuentaBancaria;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO Chequera
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IChequeraDAO extends JpaRepository<Chequera, Integer> {
    
    //Obtiene el siguiente id
    public Chequera findTopByOrderByIdDesc();
    
    //Obtiene una lista por nombre
    public List<Chequera> findByCuentaBancaria(CuentaBancaria cuentaBancaria);
    
}