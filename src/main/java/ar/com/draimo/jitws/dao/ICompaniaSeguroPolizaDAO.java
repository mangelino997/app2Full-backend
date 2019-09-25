//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.CompaniaSeguro;
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
    
    //Obtiene el ultimo registro
    public CompaniaSeguroPoliza findTopByOrderByIdDesc();
    
    //Obtiene una lista por empresa
    public List<CompaniaSeguroPoliza> findByEmpresa(Optional<Empresa> empresa);
    
    //Obtiene una lista por compania de seguro
    public List<CompaniaSeguroPoliza> findByCompaniaSeguro(Optional<CompaniaSeguro> companiaSeguro);
    
    //Obtiene por compania de seguro y empresa
    public List<CompaniaSeguroPoliza> findByCompaniaSeguroAndEmpresa(Optional<CompaniaSeguro> companiaSeguro, Optional<Empresa> empresa);
    
    //Obtiene una lista por el nombre de compania de seguro
    public List<CompaniaSeguroPoliza> findByCompaniaSeguro_NombreContaining(String nombre);
    
}