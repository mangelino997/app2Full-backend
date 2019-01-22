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
    
    //Obtiene una lista por razon social y CAEA habilitado
    public List<Empresa> findByRazonSocialContainingAndFeCAEATrue(String razonSocial);
    
    //Obtiene un listado por razon social y activa
    public List<Empresa> findByRazonSocialContainingAndEstaActivaTrue(String razonSocial);
    
    //Obtiene un listado por razon social, esta activa y fe
    public List<Empresa> findByRazonSocialContainingAndEstaActivaTrueAndFeTrue(String razonSocial);
    
}
