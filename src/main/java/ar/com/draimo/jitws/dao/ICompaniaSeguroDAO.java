//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.CompaniaSeguro;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Interfaz DAO Compañia de seguro
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface ICompaniaSeguroDAO extends JpaRepository<CompaniaSeguro, Integer> {
    
    //Obtiene el siguiente id
    public CompaniaSeguro findTopByOrderByIdDesc();
    
    //Obtiene una lista por nombre
    public List<CompaniaSeguro> findByNombreContaining(String nombre);
    
    //Obtiene una lista por empresa (Consulta CompaniaSeguroPoliza)
    @Query(value = "SELECT c.* FROM companiaseguropoliza p " + "INNER JOIN companiaseguro c ON p.idCompaniaSeguro=c.id " 
            + "where p.idEmpresa=:idEmpresa group by idCompaniaSeguro order by idCompaniaSeguro asc;", nativeQuery = true)
    public List<CompaniaSeguro> listarPorEmpresa(@Param("idEmpresa") int idEmpresa);
    
}
