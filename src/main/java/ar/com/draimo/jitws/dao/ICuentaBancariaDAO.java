//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.CuentaBancaria;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Interfaz CuentaBancaria DAO
 * Metodos contra la base de datos
 * @author blas
 */

@Repository
public interface ICuentaBancariaDAO extends JpaRepository<CuentaBancaria, Integer> {
    
    //Obtiene el ultimo registro
    public CuentaBancaria findTopByOrderByIdDesc();
    
    //Obtiene un listado por empresa ordenado por el nombre del banco
    @Query(value = "SELECT c.* FROM cuentabancaria c INNER JOIN sucursalbanco s on"
            + " c.idSucursalBanco=s.id INNER JOIN banco b on s.idBanco=b.id WHERE "
            + "idEmpresa=:idEmpresa ORDER BY b.nombre", nativeQuery = true)
    public List<CuentaBancaria> listarPorEmpresa(@Param("idEmpresa") int idEmpresa);
    
    //Obtiene un listado de cuentas bancarias que tengan chequeras por empresa
    @Query(value = "SELECT b.* FROM cuentabancaria b INNER JOIN chequera c on"
            + " b.id=c.idCuentaBancaria WHERE idEmpresa=:idEmpresa group by b.id", nativeQuery = true)
    public List<CuentaBancaria> listarConChequerasPorEmpresa(@Param("idEmpresa") int idEmpresa);
    
}