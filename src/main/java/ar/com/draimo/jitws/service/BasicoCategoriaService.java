package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IBasicoCategoriaDAO;
import ar.com.draimo.jitws.dao.ICategoriaDAO;
import ar.com.draimo.jitws.model.BasicoCategoria;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio BasicoCategoria
 * @author blas
 */

@Service
public class BasicoCategoriaService {
    
    //Define la referencia al dao
    @Autowired
    IBasicoCategoriaDAO elementoDAO;
    
    //Define la referencia al dao de categoria
    @Autowired
    ICategoriaDAO categoriaDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        BasicoCategoria elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento.getId()+1;
    }
    
    //Obtiene la lista completa
    public List<BasicoCategoria> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista por nombre
    public List<BasicoCategoria> listarPorCategoriaNombre(String nombre) {
        if(nombre.equals("***")){
            return elementoDAO.findAll();
        } else{
        return elementoDAO.findByCategoria_NombreContaining(nombre);
        }
    }
    
    //Obtiene el ultimo registro por categoria
    public BasicoCategoria obtenerPorCategoria(int idCategoria) {
        return elementoDAO.obtenerPorCategoria(idCategoria);
    }
    
    //Obtiene una lista por nombre
    public List<BasicoCategoria> listarPorCategoria(int idCategoria) {
        return elementoDAO.findByCategoria(categoriaDAO.findById(idCategoria).get());
    }
    
    //Obtiene una lista por categoria y aio
    public List<BasicoCategoria> listarPorCategoriaYAnio(int idCategoria, short anio) {
        return elementoDAO.findByCategoriaAndAnio(categoriaDAO.findById(idCategoria).get(), anio);
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public BasicoCategoria agregar(BasicoCategoria elemento) throws Exception {
        //Obtiene longitud de anio, si supera 4 retorna error
        String anio = String.valueOf(elemento.getAnio());
        if (anio.length()>4) {
            throw new DataIntegrityViolationException("Cantidad caracteres excedida en AÑO");
        }
        return elementoDAO.save(elemento);
    }
    
    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(BasicoCategoria elemento) throws Exception {
        //Obtiene longitud de anio, si supera 4 retorna error
        String anio = String.valueOf(elemento.getAnio());
        if (anio.length()>4) {
            throw new DataIntegrityViolationException("Cantidad caracteres excedida en AÑO");
        }
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }
    
}
