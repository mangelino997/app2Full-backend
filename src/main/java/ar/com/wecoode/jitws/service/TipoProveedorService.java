package ar.com.wecoode.jitws.service;

import ar.com.wecoode.jitws.dao.ITipoProveedorDAO;
import ar.com.wecoode.jitws.model.TipoProveedor;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Servicio TipoProveedor
 * @author blas
 */

@Service
public class TipoProveedorService {

    //Define la referencia al dao
    @Autowired
    ITipoProveedorDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        return elementoDAO.obtenerSiguienteId();
    }
    
    //Obtiene la lista completa
    public List<TipoProveedor> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista por nombre
    public List<TipoProveedor> listarPorNombre(String nombre) {
        return elementoDAO.findByNombreContaining(nombre);
    }

    //Agrega un registro
    public void agregar(TipoProveedor elemento) {
        elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    public void actualizar(TipoProveedor elemento) {
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    public void eliminar(TipoProveedor elemento) {
        elementoDAO.delete(elemento);
    }

}
