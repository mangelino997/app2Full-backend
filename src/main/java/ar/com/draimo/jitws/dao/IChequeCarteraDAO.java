//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.ChequeCartera;
import ar.com.draimo.jitws.model.Empresa;
import ar.com.draimo.jitws.model.Banco;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Interfaz DAO ChequeCartera
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IChequeCarteraDAO extends JpaRepository<ChequeCartera, Integer> {
    
    //Obtiene el ultimo registro
    public ChequeCartera findTopByOrderByIdDesc();
    
    //Obtiene una lista por Empresa
    public List<ChequeCartera> findByEmpresa(Empresa empresa);
    
    //Obtiene una lista por Banco
    public List<ChequeCartera> findByBanco(Banco banco);
    
    //Obtiene una lista por filtros
    @Query(value = "SELECT * FROM jitdb.chequecartera WHERE (fechaPago between :fechaPagoDesde AND :fechaPagoHasta) "
            + "AND (importe between :importeDesde AND :importeHasta) AND (:numero is null or numero=:numero) "
            + "AND eCheq=:eCheq", nativeQuery = true)
    public List<ChequeCartera> listarPorFiltros(@Param("fechaPagoDesde") Date fechaPagoDesde, 
            @Param("fechaPagoHasta") Date fechaPagoHasta, @Param("importeDesde") BigDecimal importeDesde,
            @Param("importeHasta") BigDecimal importeHasta, @Param("numero") String numero, @Param("eCheq") boolean eCheq);
    
    //Obtiene una lista por filtros sin importe hasta
    @Query(value = "SELECT * FROM jitdb.chequecartera WHERE (fechaPago between :fechaPagoDesde AND :fechaPagoHasta) "
            + "AND (importe >= :importeDesde) AND (:numero is null or numero=:numero) AND eCheq=:eCheq", nativeQuery = true)
    public List<ChequeCartera> listarPorFiltrosSinImporteHasta(@Param("fechaPagoDesde") Date fechaPagoDesde, 
            @Param("fechaPagoHasta") Date fechaPagoHasta, @Param("importeDesde") BigDecimal importeDesde,
            @Param("numero") String numero, @Param("eCheq") boolean eCheq);
    
    //Obtiene una lista por filtros sin importe desde
    @Query(value = "SELECT * FROM jitdb.chequecartera WHERE (fechaPago between :fechaPagoDesde AND :fechaPagoHasta) "
            + "AND (importe <= :importeHasta) AND (:numero is null or numero=:numero) AND eCheq=:eCheq", nativeQuery = true)
    public List<ChequeCartera> listarPorFiltrosSinImporteDesde(@Param("fechaPagoDesde") Date fechaPagoDesde, 
            @Param("fechaPagoHasta") Date fechaPagoHasta, @Param("importeHasta") BigDecimal importeHasta,
            @Param("numero") String numero, @Param("eCheq") boolean eCheq);
    
    //Obtiene una lista por filtros sin importes
    @Query(value = "SELECT * FROM jitdb.chequecartera WHERE (fechaPago between :fechaPagoDesde AND :fechaPagoHasta) "
            + "AND (:numero is null or numero=:numero) AND eCheq=:eCheq", nativeQuery = true)
    public List<ChequeCartera> listarPorFiltrosSinImportes(@Param("fechaPagoDesde") Date fechaPagoDesde, 
            @Param("fechaPagoHasta") Date fechaPagoHasta, @Param("numero") String numero, @Param("eCheq") boolean eCheq);
    
}