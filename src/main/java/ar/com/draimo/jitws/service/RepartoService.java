package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IPersonalDAO;
import ar.com.draimo.jitws.model.Reparto;
import ar.com.draimo.jitws.model.RepartoComprobante;
import ar.com.draimo.jitws.model.RepartoPersonal;
import java.sql.Timestamp;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ar.com.draimo.jitws.dao.IRepartoComprobanteDAO;
import ar.com.draimo.jitws.dao.IRepartoDAO;
import ar.com.draimo.jitws.dao.IRepartoPersonalDAO;

/**
 * Servicio RepartoPropio
 * @author blas
 */

@Service
public class RepartoService {

    //Define la referencia al dao
    @Autowired
    IRepartoDAO elementoDAO;
    
    //define la referencia al dao de RepartoPersonal
    @Autowired
    IRepartoPersonalDAO repartoPersonalDAO;
    
    //define la referencia al dao de Personal
    @Autowired
    IPersonalDAO personalDAO;
    
    //define la referencia al dao de RepartoPropioComprobante
    @Autowired
    IRepartoComprobanteDAO repartoPropioComprobanteDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        Reparto elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento.getId()+1;
    }
    
    //Obtiene la lista completa
    public List<Reparto> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene la lista por EstaCerrada 
    public List<Reparto> listarPorEstaCerrada(boolean estaCerrada) {
        return elementoDAO.listarPorEstaCerrada(estaCerrada);
    }
    
    //Cierra un reparto
    public boolean cerrarReparto(int idReparto) {
        Reparto r = elementoDAO.findById(idReparto).get();
        List<RepartoComprobante> c = repartoPropioComprobanteDAO.findByReparto(r);
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
    public Reparto agregar(Reparto elemento) {
        elemento.setFechaRegistracion(new Timestamp(new java.util.Date().getTime()));
        elemento.setEstaCerrada(false);
        elementoDAO.saveAndFlush(elemento);
        if (!elemento.getAcompaniantes().isEmpty()) {
            for (RepartoPersonal acompaniante : elemento.getAcompaniantes()) {
                acompaniante.setPersonal(personalDAO.findById(acompaniante.getId()).get());
                acompaniante.setReparto(elemento);
                repartoPersonalDAO.saveAndFlush(acompaniante);
            }
        }
        return elemento;
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(Reparto elemento) {
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public boolean eliminar(int elemento) {
        if(repartoPropioComprobanteDAO.findByReparto(elementoDAO.findById(elemento).get()).isEmpty()){
            elementoDAO.deleteById(elemento);
            return true;
        }else {
            return false;
        }
    }

}
