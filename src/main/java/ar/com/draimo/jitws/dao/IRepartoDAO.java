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
    
    //Obtiene el ultimo registro
    public Reparto findTopByOrderByIdDesc();
    
    //Obtiene un listado por estaCerrada 
    @Query(value = "SELECT * FROM reparto WHERE estaCerrada=:estaCerrada AND "
            + "(:idEmpresa=0 OR idEmpresaEmision=:idEmpresa)", nativeQuery = true )
    public List<Reparto> listarPorEstaCerradaYEmpresa(@Param("estaCerrada") boolean estaCerrada,
    @Param("idEmpresa") int idEmpresa);
    
    //Obtiene u listado por estaCerrada falso y si es reparto tercero o propio
    @Query(value = "SELECT * FROM reparto WHERE estaCerrada=:estaCerrada AND "
            + "esRepartoPropio=:esRepartoPropio", nativeQuery = true )
    public List<Reparto> listarPorEstaCerradaYReparto(@Param("estaCerrada") boolean 
            estaCerrada, @Param("esRepartoPropio") boolean esRepartoPropio);
    
    /*Obtiene un listado por tipo de reparto(propio/tercero), rango de fecha, chofer,
    Si esta cerrada y idEmpresa*/
    @Query(value = "SELECT * FROM reparto WHERE esRepartoPropio=:esRepartoPropio "
            + "AND DATE(fechaRegistracion) BETWEEN :fechaDesde AND :fechaHasta AND CASE "
            + ":esRepartoPropio WHEN true THEN :idChofer = 0 OR idPersonal=:idChofer WHEN false THEN "
            + ":idChofer = 0 OR idChoferProveedor=:idChofer END AND estaCerrada=:estaCerrada AND "
            + "idEmpresaEmision=:idEmpresa", nativeQuery = true)
    public List<Reparto> listarPorFiltros(@Param("esRepartoPropio") boolean esRepartoPropio, 
            @Param("fechaDesde") Date fechaDesde, @Param("fechaHasta") Date fechaHasta,
            @Param("idChofer") int idChofer, @Param("estaCerrada") boolean estaCerrada,
            @Param("idEmpresa") int idEmpresa);
    
    //Cierra un reparto por id
    @Query(value = "SELECT * FROM reparto WHERE id=:idReparto", nativeQuery = true)
    public Reparto obtenerPorId(@Param("idReparto") int idReparto);

}