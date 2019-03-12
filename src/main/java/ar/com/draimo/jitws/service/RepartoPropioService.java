package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IPersonalDAO;
import ar.com.draimo.jitws.dao.IRepartoPropioComprobanteDAO;
import ar.com.draimo.jitws.dao.IRepartoPropioDAO;
import ar.com.draimo.jitws.dao.IRepartoPropioPersonalDAO;
import ar.com.draimo.jitws.model.RepartoPropio;
import ar.com.draimo.jitws.model.RepartoPropioComprobante;
import ar.com.draimo.jitws.model.RepartoPropioPersonal;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio RepartoPropio
 * @author blas
 */

@Service
public class RepartoPropioService {

    //Define la referencia al dao
    @Autowired
    IRepartoPropioDAO elementoDAO;
    
    //define la referencia al dao de RepartoPropioPersonal
    @Autowired
    IRepartoPropioPersonalDAO repartoPropioPersonalDAO;
    
    //define la referencia al dao de Personal
    @Autowired
    IPersonalDAO personalDAO;
    
    //define la referencia al dao de RepartoPropioComprobante
    @Autowired
    IRepartoPropioComprobanteDAO repartoPropioComprobanteDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        RepartoPropio elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento.getId()+1;
    }
    
    //Obtiene la lista completa
    public List<RepartoPropio> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene la lista por EstaCerrada 
    public List<RepartoPropio> listarPorEstaCerrada(boolean estaCerrada) {
        return elementoDAO.listarPorEstaCerrada(estaCerrada);
    }
    
    //Cierra un reparto
    public boolean cerrarReparto(int idRepartoPropio) {
        RepartoPropio r = elementoDAO.findById(idRepartoPropio).get();
        List<RepartoPropioComprobante> c = repartoPropioComprobanteDAO.findByRepartoPropio(r);
        if (c.isEmpty()) {
            return false;
        }else {
            r.setEstaCerrada(true);
            elementoDAO.save(r);
            return true;
        }
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public RepartoPropio agregar(RepartoPropio elemento) {
        elemento.setFechaRegistracion(LocalDateTime.now());
        elemento.setEstaCerrada(false);
        elementoDAO.saveAndFlush(elemento);
        if (!elemento.getAcompaniantes().isEmpty()) {
            for (RepartoPropioPersonal acompaniante : elemento.getAcompaniantes()) {
                acompaniante.setPersonal(personalDAO.findById(acompaniante.getId()).get());
                acompaniante.setRepartoPropio(elemento);
                repartoPropioPersonalDAO.saveAndFlush(acompaniante);
            }
        }
        return elemento;
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(RepartoPropio elemento) {
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public boolean eliminar(int elemento) {
        if(repartoPropioComprobanteDAO.findByRepartoPropio(elementoDAO.findById(elemento).get()).isEmpty()){
            elementoDAO.deleteById(elemento);
            return true;
        }else {
            return false;
        }
    }

}
