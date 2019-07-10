package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.model.ViajeTramoCliente;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ar.com.draimo.jitws.dao.IViajeTramoClienteDAO;

/**
 * Servicio ViajePropioTramoCliente
 * @author blas
 */

@Service
public class ViajeTramoClienteService {

    //Define la referencia al dao
    @Autowired
    IViajeTramoClienteDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        ViajeTramoCliente elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId()+1 : 1;
    }
    
    //Obtiene la lista completa
    public List<ViajeTramoCliente> listar() {
        return elementoDAO.findAll();
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public ViajeTramoCliente agregar(ViajeTramoCliente elemento) {
        return elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(ViajeTramoCliente elemento) {
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(ViajeTramoCliente elemento) {
        elementoDAO.delete(elemento);
    }

}
