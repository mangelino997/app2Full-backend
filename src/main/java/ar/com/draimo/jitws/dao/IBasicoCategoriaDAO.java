//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.BasicoCategoria;
import ar.com.draimo.jitws.model.Categoria;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO BasicoCategoria
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IBasicoCategoriaDAO extends JpaRepository<BasicoCategoria, Integer> {
    
    //Obtiene el siguiente id
    public BasicoCategoria findTopByOrderByIdDesc();
    
    //Obtiene una lista por categoria
    public List<BasicoCategoria> findByCategoria(Categoria categoria);
    
    //Obtiene un listado por categoria_nombre
    public List<BasicoCategoria> findByCategoria_NombreContaining(String nombre);
    
}
