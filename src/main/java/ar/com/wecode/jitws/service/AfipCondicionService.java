package ar.com.wecode.jitws.service;

import ar.com.wecode.jitws.dao.IAfipCondicionDAO;
import ar.com.wecode.jitws.model.AfipCondicion;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Servicio AfipCondicion
 * @author blas
 */

@Service
public class AfipCondicionService {
    
    //Define el dao
    @Autowired
    IAfipCondicionDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        return elementoDAO.obtenerSiguienteId();
    }
    
    //Obtiene la lista completa
    public List<AfipCondicion> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista por nombre
    public List<AfipCondicion> listarPorNombre(String nombre) {
        if(nombre.equals("***")) {
            return elementoDAO.findAll();
        } else {
            return elementoDAO.findByNombreContaining(nombre);
        }
    }
    
    //Agrega un registro
    public void agregar(AfipCondicion elemento) {
        elementoDAO.save(elemento);
    }
    
    //Actualiza un registro
    public void actualizar(AfipCondicion elemento) {
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    public void eliminar(AfipCondicion elemento) {
        elementoDAO.delete(elemento);
    }
    
}
