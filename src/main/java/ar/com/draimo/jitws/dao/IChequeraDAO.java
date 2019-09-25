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
    
    //Obtiene el ultimo registro
    public Chequera findTopByOrderByIdDesc();
    
    //Obtiene una lista por cuenta bancaria
    public List<Chequera> findByCuentaBancaria(CuentaBancaria cuentaBancaria);
    
    //Obtiene un listado por idEmpresa de la cuenta bancaria de cada registro
    @Query(value = "SELECT * FROM chequera c INNER JOIN cuentabancaria b ON "
            + "c.idCuentaBancaria=b.id WHERE b.idEmpresa=:idEmpresa", nativeQuery = true)
    public List<Chequera> listarPorEmpresa(@Param("idEmpresa") int idEmpresa);
    
    //Obtiene un listado por idCuentaBancaria, idTipoChequera por un numero dentro de esa chequera
    @Query(value = "SELECT * FROM chequera c WHERE c.idCuentaBancaria=:idCuentaBancaria"
            + " AND c.idTipoChequera=:idTipoChequera and :desdeHasta BETWEEN "
            + "c.desde AND c.hasta", nativeQuery = true)
    public List<Chequera> listarPorCtaBancariaTipoChequeraDesdeHasta(@Param("idCuentaBancaria")
            int idCuentaBancaria,@Param("idTipoChequera") int idTipoChequera,
            @Param("desdeHasta") int desdeHasta);
    
}