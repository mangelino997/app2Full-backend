package ar.com.wecoode.jitws.service;

import ar.com.wecoode.jitws.dao.ICategoriaDAO;
import ar.com.wecoode.jitws.model.Categoria;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Servicio Categoria
 * @author blas
 */

@Service
public class CategoriaService {
    
    //Define la referencia al dao
    @Autowired
    ICategoriaDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        return elementoDAO.obtenerSiguienteId();
    }
    
    //Obtiene la lista completa
    public List<Categoria> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista por nombre
    public List<Categoria> listarPorNombre(String nombre) {
        if(nombre.equals("***")) {
            return elementoDAO.findAll();
        } else {
            return elementoDAO.findByNombreContaining(nombre);
        }
    }
    
    //Agrega un registro
    public void agregar(Categoria elemento) {
        elementoDAO.save(elemento);
    }
    
    //Actualiza un registro
    public void actualizar(Categoria elemento) {
        elementoDAO.save(elemento);
    }
    
    //Eliminar un registro
    public void eliminar(Categoria elemento) {
        elementoDAO.delete(elemento);
    }
    
}
