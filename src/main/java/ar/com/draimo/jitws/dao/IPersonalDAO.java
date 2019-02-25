//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.Personal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Interfaz DAO Personal
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IPersonalDAO extends JpaRepository<Personal, Integer> {
    
    //Obtiene el siguiente id
    public Personal findTopByOrderByIdDesc();
    
    //Obtiene una lista por alias
    public List<Personal> findByAliasContaining(String alias);
    
    //Obtiene una lista por alias y por esChofer
    public List<Personal> findByAliasContainingAndEsChofer(String alias, int esChofer);
    
    //Obtiene una lista de choferes de corta distancia
    @Query(value = "SELECT * FROM personal WHERE esChofer=1 AND esChoferLargaDistancia=0"
            + " ORDER BY nombreCompleto ASC", nativeQuery = true)
    public List<Personal> listarChoferesCortaDistancia();
    
    //Obtiene una lista de acompañantes ordenados por nombre
    @Query(value = "SELECT * FROM personal WHERE esAcompReparto=1 ORDER BY nombreCompleto ASC ", nativeQuery = true)
    public List<Personal> listarAcompaniantes();
    
}
