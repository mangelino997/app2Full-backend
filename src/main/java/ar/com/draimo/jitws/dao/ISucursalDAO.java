//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.Sucursal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Interfaz DAO Sucursal Define los metodos particulares contra la base de datos
 *
 * @author blas
 */
public interface ISucursalDAO extends JpaRepository<Sucursal, Integer> {

    //Obtiene el ultimo registro
    public Sucursal findTopByOrderByIdDesc();

    //Obtiene una lista por nombre
    public List<Sucursal> findByNombreContaining(String nombre);

    //Obtiene una sucursal por reparto
    @Query(value = "SELECT * FROM sucursal s INNER JOIN reparto r ON s.id=r.idSucursal"
            + " WHERE r.id=:idReparto", nativeQuery = true)
    public Sucursal obtenerPorReparto(@Param("idReparto") int idReparto);

}
