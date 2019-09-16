package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.ITipoComprobanteDAO;
import ar.com.draimo.jitws.dao.IVentaTipoItemDAO;
import ar.com.draimo.jitws.model.VentaTipoItem;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio VentaTipoItem
 * @author blas
 */

@Service
public class VentaTipoItemService {
    
    //Define la referencia al dao
    @Autowired
    IVentaTipoItemDAO elementoDAO;
    
    //Define la referencia al dao de tipo comprobante
    @Autowired
    ITipoComprobanteDAO comprobanteDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        VentaTipoItem elemento = elementoDAO.findTopByOrderByIdDesc();
        return (elemento!=null?elemento.getId()+1:1);
    }
    
    //Obtiene la lista completa
    public List<VentaTipoItem> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista por nombre
    public List<VentaTipoItem> listarPorNombre(String nombre) {
        if(nombre.equals("***")) {
            return elementoDAO.findAll();
        } else {
            return elementoDAO.findByNombreContaining(nombre);
        }
    }
    
    //Obtiene un listado por tipoComprobante
    public List<VentaTipoItem> listarItems(int idTipoComprobante) {
        return elementoDAO.findByTipoComprobante(comprobanteDAO.findById(idTipoComprobante));
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public VentaTipoItem agregar(VentaTipoItem elemento) {
        elemento = formatearStrings(elemento);
        return elementoDAO.save(elemento);
    }
    
    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(VentaTipoItem elemento) {
        elemento = formatearStrings(elemento);
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }
    
    //Formatea los strings
    private VentaTipoItem formatearStrings(VentaTipoItem elemento) {
        elemento.setNombre(elemento.getNombre().trim());
        return elemento;
    }
    
}
