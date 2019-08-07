package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IAfipDeduccionPersonalDAO;
import ar.com.draimo.jitws.model.AfipDeduccionPersonal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio AfipDeduccionPersonal
 * @author blas
 */

@Service
public class AfipDeduccionPersonalService {
    
    //Define la referencia al dao
    @Autowired
    IAfipDeduccionPersonalDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        AfipDeduccionPersonal elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento.getId()+1;
    }
    
    //Obtiene la lista completa
    public List<AfipDeduccionPersonal> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista por Descripcion
    public List<AfipDeduccionPersonal> listarPorDescripcion(String descripcion) {
        if(descripcion.equals("***")) {
            return elementoDAO.findAll();
        } else {
            return elementoDAO.findByDescripcionContaining(descripcion);
        }
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public AfipDeduccionPersonal agregar(AfipDeduccionPersonal elemento) {
        elemento = formatearStrings(elemento);
        return elementoDAO.save(elemento);
    }
    
    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(AfipDeduccionPersonal elemento) {
        elemento = formatearStrings(elemento);
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }
    
    //Formatea los strings
    private AfipDeduccionPersonal formatearStrings(AfipDeduccionPersonal elemento) {
        elemento.setDescripcion(elemento.getDescripcion().trim());
        return elemento;
    }
    
}
