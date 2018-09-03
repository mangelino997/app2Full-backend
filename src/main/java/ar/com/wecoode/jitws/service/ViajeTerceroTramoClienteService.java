package ar.com.wecoode.jitws.service;

import ar.com.wecoode.jitws.dao.IViajeTerceroTramoClienteDAO;
import ar.com.wecoode.jitws.model.ViajeTerceroTramoCliente;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return elementoDAO.obtenerSiguienteId();
    }
    
    //Obtiene la lista completa
    public List<ViajeTerceroTramoCliente> listar() {
        return elementoDAO.findAll();
    }
    
    //Agrega un registro
    public void agregar(ViajeTerceroTramoCliente elemento) {
        elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    public void actualizar(ViajeTerceroTramoCliente elemento) {
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    public void eliminar(ViajeTerceroTramoCliente elemento) {
        elementoDAO.delete(elemento);
    }

}
