package ar.com.wecoode.jitws.service;

import ar.com.wecoode.jitws.dao.ITipoComprobanteDAO;
import ar.com.wecoode.jitws.model.TipoComprobante;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Servicio TipoComprobante
 * @author blas
 */

@Service
public class TipoComprobanteService {

    //Define la referencia al dao
    @Autowired
    ITipoComprobanteDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        return elementoDAO.obtenerSiguienteId();
    }
    
    //Obtiene la lista completa
    public List<TipoComprobante> listar() {
        return elementoDAO.findAll();
    }

    //Agrega un registro
    public void agregar(TipoComprobante elemento) {
        elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    public void actualizar(TipoComprobante elemento) {
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    public void eliminar(TipoComprobante elemento) {
        elementoDAO.delete(elemento);
    }

}
