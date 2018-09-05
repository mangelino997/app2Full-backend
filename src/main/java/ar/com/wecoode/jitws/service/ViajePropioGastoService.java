package ar.com.wecoode.jitws.service;

import ar.com.wecoode.jitws.dao.IViajePropioGastoDAO;
import ar.com.wecoode.jitws.model.ViajePropioGasto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio ViajePropioGasto
 * @author blas
 */

@Service
public class ViajePropioGastoService {

    //Define la referencia al dao
    @Autowired
    IViajePropioGastoDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        return elementoDAO.obtenerSiguienteId();
    }
    
    //Obtiene la lista completa
    public List<ViajePropioGasto> listar() {
        return elementoDAO.findAll();
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public void agregar(ViajePropioGasto elemento) {
        elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(ViajePropioGasto elemento) {
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(ViajePropioGasto elemento) {
        elementoDAO.delete(elemento);
    }

}
