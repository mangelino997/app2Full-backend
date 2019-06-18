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
    
    //Obtiene el siguiente id
    public CuentaBancaria findTopByOrderByIdDesc();
    
    //Obtiene un listado por nombre
    @Query(value = "SELECT c.* FROM cuentabancaria c INNER JOIN sucursalbanco s on"
            + " c.idSucursalBanco=s.id INNER JOIN banco b on s.idBanco=b.id WHERE "
            + "idEmpresa=:idEmpresa ORDER BY b.nombre", nativeQuery = true)
    public List<CuentaBancaria> listarPorEmpresa(@Param("idEmpresa") int idEmpresa);
    
}
