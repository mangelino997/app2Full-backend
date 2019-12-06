//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.Personal;
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
    
    /*Obtiene una lista por alias y/o activos y/o empresa y/o sucursal
    El parametro 'activos' sirve para saber si quiere o no filtrar por el mismo
    El parametro 'fecha' recibe la fecha actual para ver si el personal esta 
    activo controlando o que no haya fecha fin o que sea mayor a la fecha actual*/
    @Query(value = "SELECT * FROM personal WHERE case :activos when true then "
            + "(alias LIKE %:alias%) and (fechaFin is null or datediff(fechaFin, "
            + ":fecha)>0) and (:idEmpresa=0 OR idEmpresa=:idEmpresa) and (:idSucursal=0 "
            + "OR idSucursal=:idSucursal) when false then (alias LIKE %:alias%) and "
            + "(:idEmpresa=0 OR idEmpresa=:idEmpresa) AND (:idSucursal=0 OR "
            + "idSucursal=:idSucursal) end", nativeQuery = true)
    public List<Personal> listarPorAlias(@Param("alias") String alias, @Param("activos") 
            boolean activos, @Param("idEmpresa") int idEmpresa, @Param("idSucursal") 
                    int idSucursal,@Param("fecha") Date fecha );
    
    //Obtiene una lista de personales por empresa, sucursal y categoria(opcional) para adelantos en lote
    @Query(value = "SELECT * FROM personal WHERE idEmpresa=:idEmpresa AND "
            + "idSucursal=:idSucursal AND (:idCategoria=0 OR idCategoria=:idCategoria) "
            + "ORDER BY nombreCompleto ASC", nativeQuery = true)
    public List<Personal> listarPersonalesParaAdelanto(@Param("idEmpresa") int idEmpresa,
            @Param("idSucursal") int idSucursal, @Param("idCategoria") int idCategoria);
    
    /* Obtiene un listado de choferes por distancia o no y/o alias y/o empresa y activos. 
    El parametro 'largaDistancia' es un int que recibe 3 posibles numeros 0, 1 o 2.
    Si recibe 1 lista por larga distancia, 0 corta distancia y 2 todos*/
    @Query(value = "SELECT * FROM personal WHERE case :largaDistancia when 1 then "
            + "esChofer=1 AND  (alias LIKE %:alias%) AND (fechaFin is null or datediff(fechaFin,"
            + " :fecha)>0) AND (:idEmpresa=0 OR idEmpresa=:idEmpresa) AND "
            + "esChoferLargaDistancia=:largaDistancia when 0 then esChofer=1 AND  "
            + "(alias LIKE %:alias%) AND (fechaFin is null or datediff(fechaFin,"
            + " :fecha)>0) AND (:idEmpresa=0 OR idEmpresa=:idEmpresa) AND "
            + "esChoferLargaDistancia=:largaDistancia when 2 then esChofer=1 AND  "
            + "(alias LIKE %:alias%) AND (fechaFin is null or datediff(fechaFin,"
            + " :fecha)>0) AND (:idEmpresa=0 OR idEmpresa=:idEmpresa) end ORDER BY "
            + "nombreCompleto ASC", nativeQuery = true)
    public List<Personal> listarChoferesPorDistanciaPorAliasOrdenadoPorNombre(
            @Param("alias") String alias, @Param("largaDistancia") int largaDistancia,
            @Param("idEmpresa") int idEmpresa, @Param("fecha") Date fecha);
    
    //Obtiene una lista de acompañantes activos  por alias ordenados por nombre
    @Query(value = "SELECT * FROM personal WHERE esAcompReparto=1 AND (alias LIKE "
            + "%:alias%) AND (fechaFin is null or datediff(fechaFin, :fecha)>0) "
            + "ORDER BY nombreCompleto ASC ", nativeQuery = true)
    public List<Personal> listarAcompaniantesPorAliasOrdenadoPorNombre(@Param("alias") 
            String alias, @Param("fecha") Date fecha);
    
    //Obtiene una lista de personales por filtros
    @Query(value = "SELECT * FROM personal WHERE (:idSucursal = 0 OR idSucursal = :idSucursal) "
            + "AND (:idArea = 0 OR idArea = :idArea) AND (:idModContratacion = 0 OR "
            + "idAfipModContratacion = :idModContratacion) AND (:idCategoria = 0 OR "
            + "idCategoria = :idCategoria) AND (:tipoEmpleado = 0 OR (:tipoEmpleado = 2 "
            + "AND esChoferLargaDistancia=1) OR (:tipoEmpleado = 1 AND esChofer=1) OR "
            + "(:tipoEmpleado=3 AND esChofer=0))ORDER BY nombreCompleto ASC ", nativeQuery = true)
    public List<Personal> listarPorFiltros(@Param("idSucursal") int idSucursal, 
            @Param("idArea") int idArea, @Param("idModContratacion") int idModContratacion,
            @Param("idCategoria") int idCategoria, @Param("tipoEmpleado") int tipoEmpleado);
    
    //Obtiene un registro por id
    @Query(value = "SELECT * FROM personal WHERE id=:id", nativeQuery = true)
    public Personal obtenerPorId(@Param("id") int id);
    
    //Obtiene una lista de choferes
    @Query(value = "SELECT * FROM personal WHERE esChofer=1 AND (:vtoFisico IS NULL OR "
            + "vtoPsicoFisico=:vtoFisico) AND (:vtoCurso IS NULL OR vtoCurso=:vtoCurso) AND "
            + "(:vtoCargaPeligrosa IS NULL OR vtoCursoCargaPeligrosa=:vtoCargaPeligrosa) AND "
            + "(:vtoLicConducir IS NULL OR vtoLicenciaConducir=:vtoLicConducir) AND (:vtoLinti "
            + "IS NULL OR vtoLINTI=:vtoLinti) AND (:vtoLibSanidad IS NULL OR "
            + "vtoLibretaSanidad=:vtoLibSanidad) AND ((:tipoChofer = 2 AND esChoferLargaDistancia=1) "
            + "OR (:tipoChofer =1 AND esChoferLargaDistancia=0) OR :tipoChofer=0) ", nativeQuery = true)
    public List<Personal> listarChoferesPorFiltros(@Param("tipoChofer") int tipoChofer, 
            @Param("vtoCurso") Date vtoCurso, @Param("vtoCargaPeligrosa") Date vtoCargaPeligrosa, 
            @Param("vtoLicConducir") Date vtoLicConducir, @Param("vtoLinti") Date vtoLinti,
            @Param("vtoLibSanidad") Date vtoLibSanidad, @Param("vtoFisico") Date vtoFisico);
    
    
    
}
