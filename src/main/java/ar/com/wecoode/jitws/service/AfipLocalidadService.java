package ar.com.wecoode.jitws.service;

import ar.com.wecoode.jitws.dao.IAfipLocalidadDAO;
import ar.com.wecoode.jitws.model.AfipLocalidad;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Servicio AfipLocalidad
 * @author blas
 */

@Service
public class AfipLocalidadService {
    
    //Define el dao
    @Autowired
    IAfipLocalidadDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        return elementoDAO.obtenerSiguienteId();
    }
    
    //Obtiene la lista completa
    public List<AfipLocalidad> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista por nombre
    public List<AfipLocalidad> listarPorNombre(String nombre) {
        if(nombre.equals("***")) {
            return elementoDAO.findAll();
        } else {
            return elementoDAO.findByNombreContaining(nombre);
        }
    }
    
    //Agrega un registro
    public void agregar(AfipLocalidad elemento) {
        elementoDAO.save(elemento);
    }
    
    //Actualiza un registro
    public void actualizar(AfipLocalidad elemento) {
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    public void eliminar(AfipLocalidad elemento) {
        elementoDAO.delete(elemento);
    }
    
}
