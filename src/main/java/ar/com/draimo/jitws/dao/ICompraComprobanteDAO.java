package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.CompraComprobante;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Interfaz CompraComprobante DAO
 * Metodos contra la base de datos
 * @author blas
 */

@Repository
public interface ICompraComprobanteDAO extends JpaRepository<CompraComprobante, Integer> {
    
    //Obtiene el siguiente id
    public CompraComprobante findTopByOrderByIdDesc();
    
    //Obtiene un registro por puntoVenta, letra, numero y proveedor
    @Query(value = "SELECT * FROM compracomprobante WHERE idProveedor=:idProveedor AND "
            + "codigoAfip=:codigoAfip AND puntoVenta=:puntoVenta AND numero=:numero", nativeQuery = true)
    public List<CompraComprobante> verificarUnicidad(@Param("idProveedor") int idProveedor,
            @Param("codigoAfip") String codigoAfip,@Param("puntoVenta") int puntoVenta,
            @Param("numero") int numero);
    
}
