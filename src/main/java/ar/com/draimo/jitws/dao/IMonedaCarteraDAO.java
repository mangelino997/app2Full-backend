//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.MonedaCartera;
import ar.com.draimo.jitws.model.Empresa;
import ar.com.draimo.jitws.model.Moneda;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO MonedaCartera
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IMonedaCarteraDAO extends JpaRepository<MonedaCartera, Integer> {
    
    //Obtiene el ultimo registro
    public MonedaCartera findTopByOrderByIdDesc();
    
    //Obtiene una lista por Empresa
    public List<MonedaCartera> findByEmpresa(Empresa empresa);
    
    //Obtiene una lista por Moneda
    public List<MonedaCartera> findByMoneda(Moneda moneda);
    
}