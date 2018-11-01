//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.CompaniaSeguroPoliza;
import ar.com.draimo.jitws.model.Empresa;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO Compañia de seguro poliza
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface ICompaniaSeguroPolizaDAO extends JpaRepository<CompaniaSeguroPoliza, Integer> {
    
    //Obtiene el siguiente id
    public CompaniaSeguroPoliza findTopByOrderByIdDesc();
    
    //Obtiene una lista por empresa
    public List<CompaniaSeguroPoliza> findByEmpresa(Optional<Empresa> empresa);
    
}
