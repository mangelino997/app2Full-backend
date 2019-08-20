package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IAfipDeduccionGeneralTopeDAO;
import ar.com.draimo.jitws.model.AfipDeduccionGeneralTope;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio AfipDeduccionGeneralTope
 * @author blas
 */

@Service
public class AfipDeduccionGeneralTopeService {
    
    //Define la referencia al dao
    @Autowired
    IAfipDeduccionGeneralTopeDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        AfipDeduccionGeneralTope elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento.getId()+1;
    }
    
    //Obtiene la lista completa
    public List<AfipDeduccionGeneralTope> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista por Descripcion
    public List<AfipDeduccionGeneralTope> listarPorDescripcion(String descripcion) {
        if(descripcion.equals("***")) {
            return elementoDAO.findAll();
        } else {
            return elementoDAO.findByDescripcionContaining(descripcion);
        }
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public AfipDeduccionGeneralTope agregar(AfipDeduccionGeneralTope elemento) {
        elemento = formatearStrings(elemento);
        return elementoDAO.save(elemento);
    }
    
    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(AfipDeduccionGeneralTope elemento) {
        elemento = formatearStrings(elemento);
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }
    
    //Formatea los strings
    private AfipDeduccionGeneralTope formatearStrings(AfipDeduccionGeneralTope elemento) {
        elemento.setDescripcion(elemento.getDescripcion().trim());
        return elemento;
    }
    
}
