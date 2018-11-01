//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.Empresa;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO Empresa
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IEmpresaDAO extends JpaRepository<Empresa, Integer> {
    
    //Obtiene el siguiente id
    public Empresa findTopByOrderByIdDesc();
    
    //Obtiene una lista por razon social
    public List<Empresa> findByRazonSocialContaining(String razonSocial);
    
}
