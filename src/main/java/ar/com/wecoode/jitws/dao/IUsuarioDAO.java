package ar.com.wecoode.jitws.dao;

import ar.com.wecoode.jitws.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interfaz Usuario DAO
 * Metodos contra la base de datos
 * @author blas
 */

@Repository
public interface IUsuarioDAO extends JpaRepository<Usuario, Integer> {
    
    //Obtiene el siguiente id
    public Usuario findTopByOrderByIdDesc();
    
    /**
     * Obtiene un usuario por username
     * @param username
     * @return
     */
    public Usuario findOneByUsername(String username);
    
}
