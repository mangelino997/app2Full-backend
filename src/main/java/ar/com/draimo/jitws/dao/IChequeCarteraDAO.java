//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.ChequeCartera;
import ar.com.draimo.jitws.model.Empresa;
import ar.com.draimo.jitws.model.Banco;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO ChequeCartera
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IChequeCarteraDAO extends JpaRepository<ChequeCartera, Integer> {
    
    //Obtiene el ultimo registro
    public ChequeCartera findTopByOrderByIdDesc();
    
    //Obtiene una lista por Empresa
    public List<ChequeCartera> findByEmpresa(Empresa empresa);
    
    //Obtiene una lista por Banco
    public List<ChequeCartera> findByBanco(Banco banco);
    
}