//Paquete al que pertenece la interfaz
package ar.com.wecoode.jitws.dao;

import ar.com.wecoode.jitws.model.CompaniaSeguroPoliza;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO Compañia de seguro poliza
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface ICompaniaSeguroPolizaDAO extends JpaRepository<CompaniaSeguroPoliza, Integer> {
    
    //Obtiene el siguiente id
    public CompaniaSeguroPoliza findTopByOrderByIdDesc();
    
}
