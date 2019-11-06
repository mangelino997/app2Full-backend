//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.Cobranza;
import ar.com.draimo.jitws.model.Empresa;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO Cobranza
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface ICobranzaDAO extends JpaRepository<Cobranza, Integer> {
    
    //Obtiene el siguiente id
    public Cobranza findTopByOrderByIdDesc();
    
    //Obtiene una lista por Empresa
    public List<Cobranza> findByEmpresa(Empresa empresa);
    
}
