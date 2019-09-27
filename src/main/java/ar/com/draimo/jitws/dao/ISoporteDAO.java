//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.Soporte;
import ar.com.draimo.jitws.model.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interfaz Soporte DAO
 * Metodos contra la base de datos
 * @author blas
 */

@Repository
public interface ISoporteDAO extends JpaRepository<Soporte, Integer> {
    
    //Obtiene el ultimo registro
    public Soporte findTopByOrderByIdDesc();
    
    //Obtiene un listado por usuario y alias
    public List<Soporte> findByUsuarioAndAliasContaining(Usuario usuario ,String alias);
    
    //Obtiene un listado por usuario
    public List<Soporte> findByUsuario(Usuario usuario);
    
}