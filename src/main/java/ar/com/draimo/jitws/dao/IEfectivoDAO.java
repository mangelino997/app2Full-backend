//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.Efectivo;
import ar.com.draimo.jitws.model.Empresa;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO Efectivo
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IEfectivoDAO extends JpaRepository<Efectivo, Integer> {
    
    //Obtiene el ultimo registro
    public Efectivo findTopByOrderByIdDesc();
    
    //Obtiene una lista por Empresa
    public List<Efectivo> findByEmpresa(Empresa empresa);
    
}