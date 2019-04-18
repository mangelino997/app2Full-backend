package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.constant.Funcion;
import ar.com.draimo.jitws.dao.IBasicoCategoriaDAO;
import ar.com.draimo.jitws.dao.ICategoriaDAO;
import ar.com.draimo.jitws.model.BasicoCategoria;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
    
    //Obtiene una lista por nombre
    public List<BasicoCategoria> listarBasicoPorCategoria(int idCategoria) {
        return elementoDAO.findByCategoria(categoriaDAO.findById(idCategoria).get());
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public BasicoCategoria agregar(BasicoCategoria elemento) {
        return elementoDAO.save(elemento);
    }
    
    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(BasicoCategoria elemento) {
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(BasicoCategoria elemento) {
        elementoDAO.delete(elemento);
    }
    
}
