package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IViajeTerceroTramoClienteDAO;
import ar.com.draimo.jitws.model.ViajeTerceroTramoCliente;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio ViajeTerceroTramoCliente
 * @author blas
 */

@Service
public class ViajeTerceroTramoClienteService {

    //Define la referencia al dao
    @Autowired
    IViajeTerceroTramoClienteDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        ViajeTerceroTramoCliente elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento.getId()+1;
    }
    
    //Obtiene la lista completa
    public List<ViajeTerceroTramoCliente> listar() {
        return elementoDAO.findAll();
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public ViajeTerceroTramoCliente agregar(ViajeTerceroTramoCliente elemento) {
        return elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(ViajeTerceroTramoCliente elemento) {
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(ViajeTerceroTramoCliente elemento) {
        elementoDAO.delete(elemento);
    }

}
