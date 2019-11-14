//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.Chequera;
import ar.com.draimo.jitws.model.ChequeraItem;
import ar.com.draimo.jitws.model.LibroBanco;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO ChequeraItem
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IChequeraItemDAO extends JpaRepository<ChequeraItem, Integer> {
    
    //Obtiene el ultimo registro
    public ChequeraItem findTopByOrderByIdDesc();
    
    //Obtiene una lista por LibroBanco
    public List<ChequeraItem> findByLibroBanco(LibroBanco libroBanco);
    
    //Obtiene una lista porChequera
    public List<ChequeraItem> findByChequera(Chequera chequera);
    
}