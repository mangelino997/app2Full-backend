//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.Cobrador;
import ar.com.draimo.jitws.model.Empresa;
import ar.com.draimo.jitws.model.TalonarioRecibo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Interfaz TalonarioRecibo
 * Metodos contra la base de datos
 * @author blas
 */

@Repository
public interface ITalonarioReciboDAO extends JpaRepository<TalonarioRecibo, Integer> {
    
    //Obtiene el ultimo registro
    public TalonarioRecibo findTopByOrderByIdDesc();
    
    //Obtiene una lista de 
    public List<TalonarioRecibo> findByCobradorAndTalonarioReciboLote_empresa(Cobrador cobrador,Empresa empresa);
    
    @Query(value = "SELECT * FROM talonariorecibo c WHERE :desdeHasta BETWEEN "
            + "c.desde AND c.hasta", nativeQuery = true)
    public List<TalonarioRecibo> listarPorDesdeHasta(@Param("desdeHasta") int desdeHasta);
    
}