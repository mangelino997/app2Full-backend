//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.LibroBanco;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Interfaz LibroBanco DAO
 * Metodos contra la base de datos
 * @author blas
 */

@Repository
public interface ILibroBancoDAO extends JpaRepository<LibroBanco, Integer> {
    
    //Obtiene el ultimo registro
    public LibroBanco findTopByOrderByIdDesc();
    
    //Obtiene un listado por empresa ordenado por el nombre del banco
    @Query(value = "SELECT l.* FROM librobanco l INNER JOIN cuentabancaria c on "
            + "l.idCuentaBancaria=c.id INNER JOIN sucursalbanco s on"
            + " c.idSucursalBanco=s.id INNER JOIN banco b on s.idBanco=b.id WHERE "
            + "idEmpresa=:idEmpresa ORDER BY b.nombre", nativeQuery = true)
    public List<LibroBanco> listarPorEmpresa(@Param("idEmpresa") int idEmpresa);
    
}