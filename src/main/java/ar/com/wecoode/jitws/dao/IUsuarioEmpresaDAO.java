//Paquete al que pertenece la interfaz
package ar.com.wecoode.jitws.dao;

import ar.com.wecoode.jitws.constant.NombreConstant;
import ar.com.wecoode.jitws.model.Usuario;
import ar.com.wecoode.jitws.model.UsuarioEmpresa;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Interfaz DAO UsuarioEmpresa
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IUsuarioEmpresaDAO extends JpaRepository<UsuarioEmpresa, Integer> {
    
    public final String NOMBRE_TABLA = "usuarioempresa";
    
    //Obtiene el siguiente id
    @Query(value = "SELECT Auto_increment FROM information_schema.tables "
            + "WHERE table_name='" + NOMBRE_TABLA +"'" + " AND table_schema='" 
            + NombreConstant.NOMBRE_BASE_DATOS + "'", nativeQuery = true)
    public int obtenerSiguienteId();
    
    //Obtiene una lista por usuario
    public List<UsuarioEmpresa> findByUsuario(Optional<Usuario> usuario);
    
    //Obtiene una lista por usuario
    public List<UsuarioEmpresa> findByUsuarioAndMostrarTrue(Optional<Usuario> usuario);
    
    //Elimina todos los datos de la tabla
    @Query(value = "DELETE FROM usuarioempresa", nativeQuery = true)
    public void eliminarTodo();
    
    //Reestablece autoincremental
    @Query(value = "ALTER TABLE usuarioempresa AUTO_INCREMENT=1", nativeQuery = true)
    public void reestablecerAutoincremental();
    
}
