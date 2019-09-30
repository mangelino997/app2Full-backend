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
    
    /* Si opcion= 0 Lista empresas activas. Si opcion = 1 lista empresas por razonSocial
    * Si opcion=2 lista empresas por razon social y feCaea true. Si opcion = 3 lista
    * empresas por razonSocial y activas. Si opcion=4 lista empresas por razon social
    Activas y fe true */
    @Query(value = "SELECT * FROM jitdb.empresa where case :opcion when 0 then estaActiva "
            + "= true when 1 then  razonSocial LIKE %:razonSocial% when 2 then  razonSocial "
            + "LIKE %:razonSocial% and feCAEA = true when 3 then  razonSocial LIKE "
            + "%:razonSocial% and estaActiva = true when 4 then  razonSocial LIKE "
            + "%:razonSocial% and estaActiva= true and fe=true end", nativeQuery = true)
    public List<Empresa> listarPorFiltros(@Param("razonSocial") String razonSocial, @Param("opcion") int opcion);
    
    //Obtiene una lista de empresas activas por usuario (usuarioEmpresa)
    @Query(value = "SELECT e.* FROM usuarioempresa u INNER JOIN empresa e ON "
            + "u.idUsuario = e.id WHERE u.idUsuario=:idUsuario AND u.mostrar=1 AND e.estaActiva=1", nativeQuery = true)
    public List<Empresa> listarPorUsuarioYMostrarTrue(@Param("idUsuario") int idUsuario);
    
}
