//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.CobranzaRecibo;
import ar.com.draimo.jitws.model.Cobranza;
import ar.com.draimo.jitws.model.TalonarioRecibo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO CobranzaRecibo
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface ICobranzaReciboDAO extends JpaRepository<CobranzaRecibo, Integer> {
    
    //Obtiene el siguiente id
    public CobranzaRecibo findTopByOrderByIdDesc();
    
    //Obtiene una lista por cobranza
    public List<CobranzaRecibo> findByCobranza(Cobranza cobranza);
    
    //Obtiene una lista por TalonarioRecibo
    public List<CobranzaRecibo> findByTalonarioRecibo(TalonarioRecibo talonarioRecibo);
    
}
