package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IAfipTipoPresentacionDAO;
import ar.com.draimo.jitws.model.AfipTipoPresentacion;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio AfipTipoPresentacion
 * @author blas
 */

@Service
public class AfipTipoPresentacionService {
    
    //Define la referencia al dao
    @Autowired
    IAfipTipoPresentacionDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        AfipTipoPresentacion elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento.getId()+1;
    }
    
    //Obtiene la lista completa
    public List<AfipTipoPresentacion> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista por Descripcion
    public List<AfipTipoPresentacion> listarPorDescripcion(String descripcion) {
        if(descripcion.equals("***")) {
            return elementoDAO.findAll();
        } else {
            return elementoDAO.findByDescripcionContaining(descripcion);
        }
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public AfipTipoPresentacion agregar(AfipTipoPresentacion elemento) {
        elemento = formatearStrings(elemento);
        return elementoDAO.save(elemento);
    }
    
    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(AfipTipoPresentacion elemento) {
        elemento = formatearStrings(elemento);
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }
    
    //Formatea los strings
    private AfipTipoPresentacion formatearStrings(AfipTipoPresentacion elemento) {
        elemento.setDescripcion(elemento.getDescripcion().trim());
        return elemento;
    }
    
}
