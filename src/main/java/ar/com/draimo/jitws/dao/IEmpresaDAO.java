//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.Empresa;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Interfaz DAO Empresa
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IEmpresaDAO extends JpaRepository<Empresa, Integer> {
    
    //Obtiene el ultimo registro
    public Empresa findTopByOrderByIdDesc();
    
    //Obtiene una lista de empresas activas
    public List<Empresa> findByEstaActivaTrue();
    
    //Obtiene una lista por razon social
    public List<Empresa> findByRazonSocialContaining(String razonSocial);
    
    //Obtiene una lista por razon social y Fe CAEA habilitado
    public List<Empresa> findByRazonSocialContainingAndFeCAEATrue(String razonSocial);
    
    //Obtiene un listado de activas por razon social 
    public List<Empresa> findByRazonSocialContainingAndEstaActivaTrue(String razonSocial);
    
    //Obtiene un listadode activas por razon social y fe habilitado
    public List<Empresa> findByRazonSocialContainingAndEstaActivaTrueAndFeTrue(String razonSocial);
    
    //Obtiene una lista de empresas activas por usuario (usuarioEmpresa)
    @Query(value = "SELECT e.* FROM usuarioempresa u INNER JOIN empresa e ON "
            + "u.idUsuario = e.id WHERE u.idUsuario=:idUsuario AND u.mostrar=1 AND e.estaActiva=1", nativeQuery = true)
    public List<Empresa> listarPorUsuarioYMostrarTrue(@Param("idUsuario") int idUsuario);
    
}
