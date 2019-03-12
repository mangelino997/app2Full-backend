package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IRepartoTerceroComprobanteDAO;
import ar.com.draimo.jitws.dao.IRepartoTerceroDAO;
import ar.com.draimo.jitws.model.RepartoTercero;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio RepartoTercero
 * @author blas
 */

@Service
public class RepartoTerceroService {

    //Define la referencia al dao
    @Autowired
    IRepartoTerceroDAO elementoDAO;
    
    //Define la referencia al dao de reparto tercero comprobante
    @Autowired
    IRepartoTerceroComprobanteDAO repartoTerceroComprobanteDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        RepartoTercero elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento.getId()+1;
    }
    
    //Obtiene la lista completa
    public List<RepartoTercero> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene la lista por EstaCerrada 
    public List<RepartoTercero> listarPorEstaCerrada(boolean estaCerrada) {
        return elementoDAO.listarPorEstaCerrada(estaCerrada);
    }
    
    //Cierra un reparto
    public boolean cerrarReparto(int idRepartoTercero) {
        RepartoTercero r = elementoDAO.findById(idRepartoTercero).get();
        if (repartoTerceroComprobanteDAO.findByRepartoTercero(r).isEmpty()) {
            return false;
        }else {
            r.setEstaCerrada(true);
            elementoDAO.save(r);
            return true;
        }
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public RepartoTercero agregar(RepartoTercero elemento) {
        elemento.setFechaRegistracion(LocalDateTime.now());
        elemento.setEstaCerrada(false);
        elementoDAO.saveAndFlush(elemento);
        return elemento;
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(RepartoTercero elemento) {
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public boolean eliminar(int elemento) {
        if(repartoTerceroComprobanteDAO.findByRepartoTercero(elementoDAO.findById(elemento).get()).isEmpty()){
            elementoDAO.deleteById(elemento);
            return true;
        }else {
            return false;
        }
    }

}
