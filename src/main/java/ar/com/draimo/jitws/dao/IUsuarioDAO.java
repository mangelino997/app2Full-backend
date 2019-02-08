package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.Rol;
import ar.com.draimo.jitws.model.Usuario;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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
    
    //Obtiene un listado por nombre
    public List<Usuario> findByNombreContaining(String nombre);
    
    //Obtiene una lista por rol
    public List<Usuario> findByRol(Optional<Rol> rol);
    
    //Obtiene una lista por empresa
    @Query(value = "SELECT u.* FROM usuarioempresa e INNER JOIN usuario u ON "
            + "e.idUsuario = u.id WHERE e.idEmpresa=:idEmpresa AND e.mostrar=1 "
            + "ORDER BY u.nombre ", nativeQuery = true)
    public List<Usuario> listarPorEmpresaYMostrarTrue(@Param("idEmpresa") int idEmpresa);
}
