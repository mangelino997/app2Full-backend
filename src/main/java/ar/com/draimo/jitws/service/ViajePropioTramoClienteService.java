package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IViajePropioTramoClienteDAO;
import ar.com.draimo.jitws.model.ViajePropioTramoCliente;
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
        ViajePropioTramoCliente elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId()+1 : 1;
    }
    
    //Obtiene la lista completa
    public List<ViajePropioTramoCliente> listar() {
        return elementoDAO.findAll();
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public ViajePropioTramoCliente agregar(ViajePropioTramoCliente elemento) {
        return elementoDAO.saveAndFlush(elemento);
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
