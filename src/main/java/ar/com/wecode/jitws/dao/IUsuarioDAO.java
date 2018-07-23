package ar.com.wecode.jitws.dao;

import ar.com.wecode.jitws.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interfaz Usuario DAO
 * Metodos contra la base de datos
 * @author blas
 */

@Repository
public interface IUsuarioDAO extends JpaRepository<Usuario, Integer> {
    
    /**
     * Obtiene un usuario por username
     * @param username
     * @return
     */
    public Usuario findOneByUsername(String username);
    
}
