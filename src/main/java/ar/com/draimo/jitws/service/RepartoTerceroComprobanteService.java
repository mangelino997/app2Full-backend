package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IRepartoTerceroComprobanteDAO;
import ar.com.draimo.jitws.model.RepartoTerceroComprobante;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio RepartoPropioComprobante
 * @author blas
 */

@Service
public class RepartoTerceroComprobanteService {
    
    //Define la referencia al dao
    @Autowired
    IRepartoTerceroComprobanteDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        RepartoTerceroComprobante elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento.getId()+1;
    }
    
    //Obtiene la lista completa
    public List<RepartoTerceroComprobante> listar() {
        return elementoDAO.findAll();
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public RepartoTerceroComprobante agregar(RepartoTerceroComprobante elemento) {
        return elementoDAO.save(elemento);
    }
    
    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(RepartoTerceroComprobante elemento) {
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(RepartoTerceroComprobante elemento) {
        elementoDAO.delete(elemento);
    }
    
}