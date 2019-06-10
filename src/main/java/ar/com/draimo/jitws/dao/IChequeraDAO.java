//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.Chequera;
import ar.com.draimo.jitws.model.CuentaBancaria;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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
    
    @Query(value = "SELECT * FROM chequera c INNER JOIN cuentabancaria b ON "
            + "c.idCuentaBancaria=b.id WHERE b.idEmpresa=:idEmpresa", nativeQuery = true)
    public List<Chequera> listarPorEmpresa(@Param("idEmpresa") int idEmpresa);
    
}
