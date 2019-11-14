//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.Cobranza;
import ar.com.draimo.jitws.model.CobranzaMedioPago;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Interfaz DAO CobranzaMedioPago
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface ICobranzaMedioPagoDAO extends JpaRepository<CobranzaMedioPago, Integer> {
    
    //Obtiene el siguiente id
    public CobranzaMedioPago findTopByOrderByIdDesc();
    
    //Obtiene una lista por Cobranza
    public List<CobranzaMedioPago> findByCobranza(Cobranza cobranza);
    
    //Obtiene una lista ordenada por codigoAfip
    @Query(value = "SELECT * FROM cobranzamediopago WHERE idCobranza=:idCobranza", nativeQuery = true)
    public List<CobranzaMedioPago> obtenerImporteTotalPorCobranza(@Param("idCobranza") int idCobranza);
    
}
