//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.Reparto;
import java.sql.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Interfaz DAO Reparto
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IRepartoDAO extends JpaRepository<Reparto, Integer> {
    
    //Obtiene el siguiente id
    public Reparto findTopByOrderByIdDesc();
    
    //Obtiene u listado por estaCerrada 
    @Query(value = "SELECT * FROM reparto WHERE estaCerrada=:estaCerrada", nativeQuery = true )
    public List<Reparto> listarPorEstaCerrada(@Param("estaCerrada") boolean estaCerrada);
    
    //Obtiene u listado por estaCerrada falso y tercero o propio
    @Query(value = "SELECT * FROM reparto WHERE estaCerrada=:estaCerrada AND "
            + "esRepartoPropio=:esRepartoPropio", nativeQuery = true )
    public List<Reparto> listarPorEstaCerradaYReparto(@Param("estaCerrada") boolean estaCerrada, 
            @Param("esRepartoPropio") boolean esRepartoPropio);
    
    //Obtiene un listado por filtros
    @Query(value = "SELECT * FROM reparto WHERE esRepartoPropio=:esRepartoPropio "
            + "AND fechaRegistracion BETWEEN :fechaDesde AND :fechaHasta AND CASE "
            + ":esRepartoPropio WHEN true THEN idPersonal=:idChofer WHEN false THEN "
            + "idChoferProveedor=:idChofer END AND estaCerrada=:estaCerrada AND "
            + "idEmpresaEmision=:idEmpresa", nativeQuery = true)
    public List<Reparto> listarPorFiltros(@Param("esRepartoPropio") boolean esRepartoPropio, 
            @Param("fechaDesde") Date fechaDesde, @Param("fechaHasta") Date fechaHasta,
            @Param("idChofer") int idChofer, @Param("estaCerrada") boolean estaCerrada,
            @Param("idEmpresa") int idEmpresa);
}
