//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.CompraCptePercepcionJurisd;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interfaz CompraCptePercepcionJurisd DAO
 * Metodos contra la base de datos
 * @author blas
 */

@Repository
public interface ICompraCptePercepcionJurisdDAO extends JpaRepository<CompraCptePercepcionJurisd, Integer> {
    
    //Obtiene el ultimo registro
    public CompraCptePercepcionJurisd findTopByOrderByIdDesc();
    
}