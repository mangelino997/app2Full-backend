package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IOrdenRecoleccionDAO;
import ar.com.draimo.jitws.dao.ITipoComprobanteDAO;
import ar.com.draimo.jitws.model.OrdenRecoleccion;
import ar.com.draimo.jitws.model.TipoComprobante;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio OrdenRecoleccion
 * @author blas
 */

@Service
public class OrdenRecoleccionService {
    
    //Define la referencia al dao
    @Autowired
    IOrdenRecoleccionDAO elementoDAO;
    
    //Define la referencia al dao tipo comprobante
    @Autowired
    ITipoComprobanteDAO tipoComprobanteDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        OrdenRecoleccion elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento.getId()+1;
    }
    
    //Obtiene la lista completa
    public List<OrdenRecoleccion> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista por alias
    public List<OrdenRecoleccion> listarPorAlias(String alias) {
        return elementoDAO.findByAliasContaining(alias);
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public OrdenRecoleccion agregar(OrdenRecoleccion elemento) {
        elemento = formatearStrings(elemento);
        elemento.setFechaEmision(LocalDateTime.now());
        elemento.setEstaEnReparto(false);
        TipoComprobante tipoComprobante = tipoComprobanteDAO.findById(13).get();
        elemento.setTipoComprobante(tipoComprobante);
        OrdenRecoleccion or = elementoDAO.saveAndFlush(elemento);
        or.setAlias(or.getId() + " | " + or.getCliente().getId() + " - " + or.getCliente().getRazonSocial() + " | " + or.getFecha());
        return elementoDAO.save(or);
    }
    
    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(OrdenRecoleccion elemento) {
        elemento = formatearStrings(elemento);
        elemento.setAlias(elemento.getId() + " | " + elemento.getCliente().getId() + " - " + elemento.getCliente().getRazonSocial() + " | " + elemento.getFecha());
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(OrdenRecoleccion elemento) {
        elementoDAO.delete(elemento);
    }
    
    //Formatea los strings
    private OrdenRecoleccion formatearStrings(OrdenRecoleccion elemento) {
        elemento.setDomicilio(elemento.getDomicilio().trim());
        elemento.setSolicitadoPor(elemento.getSolicitadoPor().trim());
        elemento.setTelefonoContacto(elemento.getTelefonoContacto().trim());
        elemento.setDescripcionCarga(elemento.getDescripcionCarga().trim());
        if(elemento.getObservaciones()!= null) {
            elemento.setObservaciones(elemento.getObservaciones().trim());
        }
        return elemento;
    }
    
}
