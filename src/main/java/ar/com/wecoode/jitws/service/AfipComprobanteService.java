package ar.com.wecoode.jitws.service;

import ar.com.wecoode.jitws.dao.IAfipComprobanteDAO;
import ar.com.wecoode.jitws.model.AfipComprobante;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return elementoDAO.obtenerSiguienteId();
    }
    
    //Obtiene la lista completa
    public List<AfipComprobante> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista por nombre
    public List<AfipComprobante> listarPorNombre(String nombre) {
        if(nombre.equals("***")) {
            return elementoDAO.findAll();
        } else {
            return elementoDAO.findByNombreContaining(nombre);
        }
    }
    
    //Agrega un registro
    public void agregar(AfipComprobante elemento) {
        elementoDAO.save(elemento);
    }
    
    //Actualiza un registro
    public void actualizar(AfipComprobante elemento) {
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    public void eliminar(AfipComprobante elemento) {
        elementoDAO.delete(elemento);
    }
    
}
