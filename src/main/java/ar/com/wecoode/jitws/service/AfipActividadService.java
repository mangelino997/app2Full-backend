package ar.com.wecoode.jitws.service;

import ar.com.wecoode.jitws.dao.IAfipActividadDAO;
import ar.com.wecoode.jitws.model.AfipActividad;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Servicio AfipActividad
 * @author blas
 */

@Service
public class AfipActividadService {
    
    //Define el dao
    @Autowired
    IAfipActividadDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        return elementoDAO.obtenerSiguienteId();
    }
    
    //Obtiene la lista completa
    public List<AfipActividad> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista por nombre
    public List<AfipActividad> listarPorNombre(String nombre) {
        if(nombre.equals("***")) {
            return elementoDAO.findAll();
        } else {
            return elementoDAO.findByNombreContaining(nombre);
        }
    }
    
    //Agrega un registro
    public void agregar(AfipActividad elemento) {
        elementoDAO.save(elemento);
    }
    
    //Actualiza un registro
    public void actualizar(AfipActividad elemento) {
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    public void eliminar(AfipActividad elemento) {
        elementoDAO.delete(elemento);
    }
    
}
