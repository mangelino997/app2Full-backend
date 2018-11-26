package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IVentaComprobanteItemDAO;
import ar.com.draimo.jitws.model.VentaComprobanteItemFA;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio VentaComprobanteItem
 * @author blas
 */

@Service
public class VentaComprobanteItemService {
    
    //Define la referencia al dao
    @Autowired
    IVentaComprobanteItemDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        VentaComprobanteItemFA elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId()+1 : 1;
    }
    
    //Obtiene la lista completa
    public List<VentaComprobanteItemFA> listar() {
        return elementoDAO.findAll();
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public VentaComprobanteItemFA agregar(VentaComprobanteItemFA elemento) {
        elemento = formatearStrings(elemento);
        return elementoDAO.save(elemento);
    }
    
    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(VentaComprobanteItemFA elemento) {
        elemento = formatearStrings(elemento);
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(VentaComprobanteItemFA elemento) {
        elementoDAO.delete(elemento);
    }
    
    //Formatea los strings
    private VentaComprobanteItemFA formatearStrings(VentaComprobanteItemFA elemento) {
        if(elemento.getDescripcionCarga() != null) {
            elemento.setDescripcionCarga(elemento.getDescripcionCarga().trim());
        }
        return elemento;
    }
    
}
