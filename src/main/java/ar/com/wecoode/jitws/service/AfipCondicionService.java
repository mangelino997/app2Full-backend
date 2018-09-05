package ar.com.wecoode.jitws.service;

import ar.com.wecoode.jitws.dao.IAfipCondicionDAO;
import ar.com.wecoode.jitws.model.AfipCondicion;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional(rollbackFor = Exception.class)
    public void agregar(AfipCondicion elemento) {
        elementoDAO.save(elemento);
    }
    
    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(AfipCondicion elemento) {
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(AfipCondicion elemento) {
        elementoDAO.delete(elemento);
    }
    
}
