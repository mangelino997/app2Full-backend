package ar.com.draimo.jitws.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ar.com.draimo.jitws.dao.IVentaComprobanteItemNCDAO;
import ar.com.draimo.jitws.model.VentaComprobanteItemNC;

/**
 * Servicio VentaComprobanteItem NC
 * @author blas
 */

@Service
public class VentaComprobanteItemNCService {
    
    //Define la referencia al dao
    @Autowired
    IVentaComprobanteItemNCDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        VentaComprobanteItemNC elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId()+1 : 1;
    }
    
    //Obtiene la lista completa
    public List<VentaComprobanteItemNC> listar() {
        return elementoDAO.findAll();
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public VentaComprobanteItemNC agregar(VentaComprobanteItemNC elemento) {
        return elementoDAO.save(elemento);
    }
    
    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(VentaComprobanteItemNC elemento) {
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(VentaComprobanteItemNC elemento) {
        elementoDAO.delete(elemento);
    }
    
}
