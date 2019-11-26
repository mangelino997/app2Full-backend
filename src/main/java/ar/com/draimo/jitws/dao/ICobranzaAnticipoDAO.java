//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.CobranzaAnticipo;
import ar.com.draimo.jitws.model.Cobranza;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Interfaz DAO CobranzaAnticipo
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface ICobranzaAnticipoDAO extends JpaRepository<CobranzaAnticipo, Integer> {
    
    //Obtiene el siguiente id
    public CobranzaAnticipo findTopByOrderByIdDesc();
    
    //Obtiene una lista por cobranza
    public List<CobranzaAnticipo> findByCobranza(Cobranza cobranza);
    
    //Obtiene una lista por cliente y saldo > 0
    @Query(value = "select * from cobranzaanticipo a inner join cobranza c on c.id=a.idCobranza"
            + " where c.idCliente=:idCliente and a.saldo> 0", nativeQuery = true)
    public List<CobranzaAnticipo> listarPorCliente(@Param("idCliente") int idCliente);
    
}
