//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.Empresa;
import ar.com.draimo.jitws.model.Personal;
import ar.com.draimo.jitws.model.Sucursal;
import java.sql.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Interfaz DAO Personal
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IPersonalDAO extends JpaRepository<Personal, Integer> {
    
    //Obtiene el ultimo registro
    public Personal findTopByOrderByIdDesc();
    
    //Obtiene una lista por alias
    public List<Personal> findByAliasContaining(String alias);
    
    //Obtiene una lista de activos 
    public List<Personal> findByFechaFinIsNull();
    
    //Obtiene una lista de activos o todos por alias 
    @Query(value = "SELECT * FROM personal WHERE case :activos when true then "
            + "(:alias LIKE '***' OR alias LIKE %:alias%) and (fechaFin is null "
            + "or datediff(fechaFin, :fecha)>0) and (:idEmpresa=0 OR idEmpresa=:idEmpresa)"
            + " when false then (:alias LIKE '***' OR alias LIKE %:alias%) and "
            + "(:idEmpresa=0 OR idEmpresa=:idEmpresa) end", nativeQuery = true)
    public List<Personal> listarPorAlias(@Param("alias") String alias, @Param("activos") 
            boolean activos, @Param("idEmpresa") int idEmpresa);
    
    //Obtiene una lista de choferes activos por alias y empresa 
    public List<Personal> findByAliasContainingAndEsChoferTrueAndEmpresaAndFechaFinIsNull(
            String alias, Empresa empresa);
    
    //Obtiene una lista de choferes activos por empresa 
    public List<Personal> findByEmpresaAndEsChoferTrueAndFechaFinIsNull(Empresa empresa);
    
    //Obtiene una lista de personales por empresa, sucursal y categoria(opcional) para adelantos en lote
    @Query(value = "SELECT * FROM personal WHERE idEmpresa=:idEmpresa AND "
            + "idSucursal=:idSucursal AND (:idCategoria=0 OR idCategoria=:idCategoria) "
            + "ORDER BY nombreCompleto ASC", nativeQuery = true)
    public List<Personal> listarPersonalesParaAdelanto(@Param("idEmpresa") int idEmpresa,
            @Param("idSucursal") int idSucursal, @Param("idCategoria") int idCategoria);
    
    //Obtiene una lista de choferes activos de larga o corta distancia por alias ordenados por nombre
    @Query(value = "SELECT * FROM personal WHERE esChofer=1 AND esChoferLargaDistancia="
            + ":largaDistancia AND (:alias LIKE '***' OR alias LIKE %:alias%) AND "
            + "(fechaFin is null or datediff(fechaFin, :fecha)>0) AND (:idEmpresa=0 "
            + "OR idEmpresa=:idEmpresa) ORDER BY nombreCompleto ASC", nativeQuery = true)
    public List<Personal> listarChoferesPorDistanciaPorAliasOrdenadoPorNombre(
            @Param("alias") String alias, @Param("largaDistancia") boolean largaDistancia,
            @Param("idEmpresa") int idEmpresa, @Param("fecha") Date fecha);
    
    //Obtiene una lista de choferes activos por empresa ordenados por nombre
    @Query(value = "SELECT * FROM personal WHERE esChofer=1 AND idEmpresa=:idEmpresa"
            + "AND (fechaFin is null or datediff(fechaFin, :fecha)>0) ORDER BY"
            + " nombreCompleto ASC", nativeQuery = true)
    public List<Personal> listarChoferesPorEmpresa(@Param("idEmpresa") int idEmpresa, @Param("fecha") Date fecha);
    
    //Obtiene una lista de acompañantes activos  por alias ordenados por nombre
    @Query(value = "SELECT * FROM personal WHERE esAcompReparto=1 AND (:alias LIKE '***' "
            + "OR alias LIKE %:alias%) AND (fechaFin is null or datediff(fechaFin,"
            + " :fecha)>0) ORDER BY nombreCompleto ASC ", nativeQuery = true)
    public List<Personal> listarAcompaniantesPorAliasOrdenadoPorNombre(@Param("alias") 
            String alias, @Param("fecha") Date fecha);
    
    //Obtiene un listado por alias y empresa
    public List<Personal> findByEmpresaAndAliasContaining(Empresa empresa, String alias);
    
    //Obtiene un listado por empresa
    @Query(value = "SELECT * FROM personal WHERE case :activo when true then (:idEmpresa=0 "
            + "OR idEmpresa=:idEmpresa) and (fechaFin is null or datediff(fechaFin, "
            + ":fecha)>0) when false then (:idEmpresa=0 OR idEmpresa=:idEmpresa) end",nativeQuery = true)
    public List<Personal> listarPorEmpresa(@Param("idEmpresa")int idEmpresa,@Param("activo") 
            boolean activo,@Param("fecha") Date fecha);
    
    //Obtiene un listado por empresa y sucursal
    @Query(value = "SELECT * FROM personal WHERE case :activo when true then (:idSucursal=0 "
            + "OR idSucursal=:idSucursal) and(:idEmpresa=0 OR idEmpresa=:idEmpresa)"
            + " and (:alias LIKE '***' OR  alias LIKE %:alias%) and (fechaFin is null"
            + " or datediff(fechaFin, :fecha)>0) when false then (:idSucursal=0 and"
            + " (:alias LIKE '***' OR  alias LIKE %:alias%) and (:idEmpresa=0 OR idEmpresa=:idEmpresa)"
            + " end",nativeQuery = true)
    public List<Personal> listarPorAliasEmpresaYSucursal(@Param("idEmpresa")int idEmpresa,
            @Param("idSucursal") int idSucursal, @Param("activo") boolean activo, 
            @Param("alias") String alias, @Param("fecha") Date fecha);
    
}
