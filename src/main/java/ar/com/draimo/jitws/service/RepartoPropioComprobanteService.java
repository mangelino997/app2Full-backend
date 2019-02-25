package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IRepartoPropioComprobanteDAO;
import ar.com.draimo.jitws.dao.IRepartoPropioDAO;
import ar.com.draimo.jitws.model.RepartoPropioComprobante;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio RepartoPropioComprobante
 * @author blas
 */

@Service
public class RepartoPropioComprobanteService {
    
    //Define la referencia al dao
    @Autowired
    IRepartoPropioComprobanteDAO elementoDAO;
    
    //Define la referencia al dao de RepartoPropio
    @Autowired
    IRepartoPropioDAO repartoPropioDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        RepartoPropioComprobante elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento.getId()+1;
    }
    
    //Obtiene la lista completa
    public List<RepartoPropioComprobante> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene la lista por repartoPropio
    public List<RepartoPropioComprobante> listarComprobantes(int idRepartoPropio) {
        return elementoDAO.findByRepartoPropio(repartoPropioDAO.findById(idRepartoPropio).get());
    }
    
    //Quita un comprobante de la tabla y la planilla
    public void quitarComprobante() {
     
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public RepartoPropioComprobante agregar(RepartoPropioComprobante elemento) {
        return elementoDAO.save(elemento);
    }
    
    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(RepartoPropioComprobante elemento) {
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(RepartoPropioComprobante elemento) {
        elementoDAO.delete(elemento);
    }
    
}
