package ar.com.wecoode.jitws.service;

import ar.com.wecoode.jitws.dao.IMonedaDAO;
import ar.com.wecoode.jitws.model.Moneda;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Servicio Moneda
 * @author blas
 */

@Service
public class MonedaService {
    
    //Define la referencia al dao
    @Autowired
    IMonedaDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        return elementoDAO.obtenerSiguienteId();
    }
    
    //Obtiene la lista completa
    public List<Moneda> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista por nombre
    public List<Moneda> listarPorNombre(String nombre) {
        if(nombre.equals("***")) {
            return elementoDAO.findAll();
        } else {
            return elementoDAO.findByNombreContaining(nombre);
        }
    }
    
    //Agrega un registro
    public void agregar(Moneda elemento) {
        elementoDAO.save(elemento);
    }
    
    //Actualiza un registro
    public void actualizar(Moneda elemento) {
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    public void eliminar(Moneda elemento) {
        elementoDAO.delete(elemento);
    }
    
}
