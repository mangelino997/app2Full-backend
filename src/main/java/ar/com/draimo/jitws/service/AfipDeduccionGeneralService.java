package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IAfipDeduccionGeneralDAO;
import ar.com.draimo.jitws.model.AfipDeduccionGeneral;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio AfipDeduccionGeneral
 * @author blas
 */

@Service
public class AfipDeduccionGeneralService {
    
    //Define la referencia al dao
    @Autowired
    IAfipDeduccionGeneralDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        AfipDeduccionGeneral elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId()+1 : 1;
    }
    
    //Obtiene la lista completa
    public List<AfipDeduccionGeneral> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista por Descripcion
    public List<AfipDeduccionGeneral> listarPorDescripcion(String descripcion) {
        if(descripcion.equals("***")) {
            return elementoDAO.findAll();
        } else {
            return elementoDAO.findByDescripcionContaining(descripcion);
        }
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public AfipDeduccionGeneral agregar(AfipDeduccionGeneral elemento) {
        elemento = formatearStrings(elemento);
        return elementoDAO.save(elemento);
    }
    
    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(AfipDeduccionGeneral elemento) {
        elemento = formatearStrings(elemento);
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }
    
    //Formatea los strings
    private AfipDeduccionGeneral formatearStrings(AfipDeduccionGeneral elemento) {
        elemento.setDescripcion(elemento.getDescripcion().trim());
        return elemento;
    }
    
}
