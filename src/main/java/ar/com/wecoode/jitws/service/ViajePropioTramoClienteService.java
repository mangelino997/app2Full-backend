package ar.com.wecoode.jitws.service;

import ar.com.wecoode.jitws.dao.IViajePropioTramoClienteDAO;
import ar.com.wecoode.jitws.model.ViajePropioTramoCliente;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio ViajePropioTramoCliente
 * @author blas
 */

@Service
public class ViajePropioTramoClienteService {

    //Define la referencia al dao
    @Autowired
    IViajePropioTramoClienteDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        return elementoDAO.obtenerSiguienteId();
    }
    
    //Obtiene la lista completa
    public List<ViajePropioTramoCliente> listar() {
        return elementoDAO.findAll();
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public void agregar(ViajePropioTramoCliente elemento) {
        elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(ViajePropioTramoCliente elemento) {
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(ViajePropioTramoCliente elemento) {
        elementoDAO.delete(elemento);
    }

}
