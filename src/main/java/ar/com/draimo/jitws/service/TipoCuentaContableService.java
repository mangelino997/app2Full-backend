package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.ITipoCuentaContableDAO;
import ar.com.draimo.jitws.model.TipoCuentaContable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio TipoCuentaContable
 * @author blas
 */

@Service
public class TipoCuentaContableService {

    //Define la referencia al dao
    @Autowired
    ITipoCuentaContableDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        TipoCuentaContable elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId()+1 : 1;
    }
    
    //Obtiene la lista completa
    public List<TipoCuentaContable> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista por nombre
    public List<TipoCuentaContable> listarPorNombre(String nombre) {
        if(nombre.equals("***")) {
            return elementoDAO.findAll();
        } else {
            return elementoDAO.findByNombreContaining(nombre);
        }
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public TipoCuentaContable agregar(TipoCuentaContable elemento) {
        elemento = formatearStrings(elemento);
        return elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(TipoCuentaContable elemento) {
        elemento = formatearStrings(elemento);
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(TipoCuentaContable elemento) {
        elementoDAO.delete(elemento);
    }
    
    //Formatea los strings
    private TipoCuentaContable formatearStrings(TipoCuentaContable elemento) {
        elemento.setNombre(elemento.getNombre().trim());
        return elemento;
    }

}