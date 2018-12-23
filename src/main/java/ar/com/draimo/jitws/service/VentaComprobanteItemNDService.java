package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IVentaComprobanteItemNDDAO;
import ar.com.draimo.jitws.model.VentaComprobanteItemND;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio VentaComprobanteItem ND
 * @author blas
 */

@Service
public class VentaComprobanteItemNDService {
    
    //Define la referencia al dao
    @Autowired
    IVentaComprobanteItemNDDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        VentaComprobanteItemND elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId()+1 : 1;
    }
    
    //Obtiene la lista completa
    public List<VentaComprobanteItemND> listar() {
        return elementoDAO.findAll();
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public VentaComprobanteItemND agregar(VentaComprobanteItemND elemento) {
        return elementoDAO.save(elemento);
    }
    
    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(VentaComprobanteItemND elemento) {
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(VentaComprobanteItemND elemento) {
        elementoDAO.delete(elemento);
    }
    
}
