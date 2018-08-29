package ar.com.wecoode.jitws.dao;

import ar.com.wecoode.jitws.constant.NombreConstant;
import ar.com.wecoode.jitws.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Interfaz Usuario DAO
 * Metodos contra la base de datos
 * @author blas
 */

@Repository
public interface IUsuarioDAO extends JpaRepository<Usuario, Integer> {
    
    public final String NOMBRE_TABLA = "usuario";
    
    //Obtiene el siguiente id
    @Query(value = "SELECT Auto_increment FROM information_schema.tables "
            + "WHERE table_name='" + NOMBRE_TABLA +"'" + " AND table_schema='" 
            + NombreConstant.NOMBRE_BASE_DATOS + "'", nativeQuery = true)
    public int obtenerSiguienteId();
    
    /**
     * Obtiene un usuario por username
     * @param username
     * @return
     */
    public Usuario findOneByUsername(String username);
    
}
