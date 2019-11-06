//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.Empresa;
import ar.com.draimo.jitws.model.TalonarioReciboLote;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Interfaz TalonarioReciboLote
 * Metodos contra la base de datos
 * @author blas
 */

@Repository
public interface ITalonarioReciboLoteDAO extends JpaRepository<TalonarioReciboLote, Integer> {
    
    //Obtiene el ultimo registro
    public TalonarioReciboLote findTopByOrderByIdDesc();
    
    //Obtiene un listado por empresa y loteEntregadoFalse
    public List<TalonarioReciboLote> findByEmpresaAndLoteEntregadoFalse(Empresa empresa); 
    
    //Obtiene un listado por empresa
    public List<TalonarioReciboLote> findByEmpresaOrderByPuntoVentaAsc(Empresa empresa); 
    
    //Obtiene un listado por desde-hasta, punto de venta y letra
    @Query(value = "SELECT * FROM talonariorecibolote  WHERE  (:desdeHasta BETWEEN "
            + "desde AND hasta) and puntoVenta=:puntoVenta and letra=:letra "
            + "and idEmpresa=:idEmpresa", nativeQuery = true)
    public List<TalonarioReciboLote> listarPorDesdeHasta(@Param("desdeHasta") int 
            desdeHasta, @Param("puntoVenta") int puntoVenta, @Param("letra") String
                    letra, @Param("idEmpresa") int idEmpresa);
    
}