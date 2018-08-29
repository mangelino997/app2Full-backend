package ar.com.wecoode.jitws.service;

import ar.com.wecoode.jitws.dao.IAreaDAO;
import ar.com.wecoode.jitws.model.Area;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Servicio Area
 * @author blas
 */

@Service
public class AreaService {
    
    //Define la referencia al dao
    @Autowired
    IAreaDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        return elementoDAO.obtenerSiguienteId();
    }
    
    //Obtiene la lista completa
    public List<Area> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista por nombre
    public List<Area> listarPorNombre(String nombre) {
        if(nombre.equals("***")) {
            return elementoDAO.findAll();
        } else {
            return elementoDAO.findByNombreContaining(nombre);
        }
    }
    
    //Agrega un registro
    public void agregar(Area elemento) {
        elementoDAO.save(elemento);
    }
    
    //Actualiza un registro
    public void actualizar(Area elemento) {
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    public void eliminar(Area elemento) {
        elementoDAO.delete(elemento);
    }
    
}
