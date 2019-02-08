//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.Usuario;
import ar.com.draimo.jitws.model.UsuarioEmpresa;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * Interfaz DAO UsuarioEmpresa
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IUsuarioEmpresaDAO extends JpaRepository<UsuarioEmpresa, Integer> {
    
    //Obtiene el siguiente id
    public UsuarioEmpresa findTopByOrderByIdDesc();
    
    //Obtiene una lista por usuario
    public List<UsuarioEmpresa> findByUsuario(Optional<Usuario> usuario);
    
    //Obtiene una lista por usuario
    public List<UsuarioEmpresa> findByUsuarioAndMostrarTrue(Optional<Usuario> usuario);
    
    //Elimina todos los datos de la tabla
    @Modifying
    @Query(value = "DELETE FROM usuarioempresa", nativeQuery = true)
    public void eliminarTodo();
    
    //Reestablece autoincremental
    @Modifying
    @Query(value = "ALTER TABLE usuarioempresa AUTO_INCREMENT=1", nativeQuery = true)
    public void reestablecerAutoincremental();
    
}
