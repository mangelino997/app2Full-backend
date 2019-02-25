package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IOrdenRecoleccionDAO;
import ar.com.draimo.jitws.model.OrdenRecoleccion;
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
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        OrdenRecoleccion elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento.getId()+1;
    }
    
    //Obtiene un registro por id
    public OrdenRecoleccion obtenerPorId(int idOrdenRecoleccion) {
        return (elementoDAO.findById(idOrdenRecoleccion).get());
    } 
    
    //Obtiene la lista completa
    public List<OrdenRecoleccion> listar() {
        return elementoDAO.findAll();
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public OrdenRecoleccion agregar(OrdenRecoleccion elemento) {
        elemento = formatearStrings(elemento);
        return elementoDAO.save(elemento);
    }
    
    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(OrdenRecoleccion elemento) {
        elemento = formatearStrings(elemento);
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
