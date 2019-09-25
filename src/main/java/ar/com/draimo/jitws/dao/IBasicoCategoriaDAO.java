//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.BasicoCategoria;
import ar.com.draimo.jitws.model.Categoria;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Interfaz DAO BasicoCategoria
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IBasicoCategoriaDAO extends JpaRepository<BasicoCategoria, Integer> {
    
    //Obtiene el ultimo registro
    public BasicoCategoria findTopByOrderByIdDesc();
    
    //Obtiene una lista por categoria
    public List<BasicoCategoria> findByCategoria(Categoria categoria);
    
    //Obtiene el ultimo registro por categoria
    @Query(value = "SELECT * FROM basicocategoria WHERE idCategoria=:idCategoria ORDER BY "
            + "anio, idMes DESC LIMIT 1", nativeQuery= true)
    public BasicoCategoria obtenerPorCategoria(@Param("idCategoria") int idCategoria);
    
    //Obtiene un listado por el nombre de categoria
    public List<BasicoCategoria> findByCategoria_NombreContaining(String nombre);
    
    //Obtiene una lista por categoria y anio
    public List<BasicoCategoria> findByCategoriaAndAnio(Categoria categoria, short anio);
    
}