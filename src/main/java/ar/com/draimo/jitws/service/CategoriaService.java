package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.ICategoriaDAO;
import ar.com.draimo.jitws.model.Categoria;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        Categoria elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId()+1 : 1;
    }
    
    //Obtiene la lista completa
    public List<Categoria> listar() {
        return elementoDAO.findByOrderByNombreAsc();
    }
    
    //Obtiene una lista por nombre
    public List<Categoria> listarPorNombre(String nombre) {
        if(nombre.equals("***")) {
            return elementoDAO.findByOrderByNombreAsc();
        } else {
            return elementoDAO.findByNombreContainingOrderByNombreAsc(nombre);
        }
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public Categoria agregar(Categoria elemento) {
        elemento = formatearStrings(elemento);
        return elementoDAO.save(elemento);
    }
    
    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(Categoria elemento) {
        elemento = formatearStrings(elemento);
        elementoDAO.save(elemento);
    }
    
    //Eliminar un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(Categoria elemento) {
        elementoDAO.delete(elemento);
    }
    
    //Formatea los strings
    private Categoria formatearStrings(Categoria elemento) {
        elemento.setNombre(elemento.getNombre().trim());
        return elemento;
    }
    
}
