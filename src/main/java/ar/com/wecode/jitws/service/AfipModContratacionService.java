package ar.com.wecode.jitws.service;

import ar.com.wecode.jitws.dao.IAfipModContratacionDAO;
import ar.com.wecode.jitws.model.AfipModContratacion;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Servicio AfipModContratacion
 * @author blas
 */

@Service
public class AfipModContratacionService {
    
    //Define el dao
    @Autowired
    IAfipModContratacionDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        return elementoDAO.obtenerSiguienteId();
    }
    
    //Obtiene la lista completa
    public List<AfipModContratacion> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista por nombre
    public List<AfipModContratacion> listarPorNombre(String nombre) {
        if(nombre.equals("***")) {
            return elementoDAO.findAll();
        } else {
            return elementoDAO.findByNombreContaining(nombre);
        }
    }
    
    //Agrega un registro
    public void agregar(AfipModContratacion elemento) {
        elementoDAO.save(elemento);
    }
    
    //Actualiza un registro
    public void actualizar(AfipModContratacion elemento) {
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    public void eliminar(AfipModContratacion elemento) {
        elementoDAO.delete(elemento);
    }
    
}
