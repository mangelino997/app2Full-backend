package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IAfipComprobanteDAO;
import ar.com.draimo.jitws.model.AfipComprobante;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio AfipComprobante
 * @author blas
 */

@Service
public class AfipComprobanteService {
    
    //Define el dao
    @Autowired
    IAfipComprobanteDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        AfipComprobante elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento.getId()+1;
    }
    
    //Obtiene la lista completa
    public List<AfipComprobante> listar() {
        return elementoDAO.findAll();
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public AfipComprobante agregar(AfipComprobante elemento) {
        elemento = formatearStrings(elemento);
        return elementoDAO.save(elemento);
    }
    
    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(AfipComprobante elemento) {
        elemento = formatearStrings(elemento);
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(AfipComprobante elemento) {
        elementoDAO.delete(elemento);
    }
    
    //Formatea los strings
    private AfipComprobante formatearStrings(AfipComprobante elemento) {
        elemento.setLetra(elemento.getLetra().trim());
        elemento.setCodigoAfip(elemento.getCodigoAfip().trim());
        return elemento;
    }
    
}
