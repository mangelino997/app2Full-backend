package ar.com.wecoode.jitws.service;

import ar.com.wecoode.jitws.dao.IMarcaProductoDAO;
import ar.com.wecoode.jitws.model.MarcaProducto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Servicio Marca Producto
 * @author blas
 */

@Service
public class MarcaProductoService {
    
    //Define la referencia al dao
    @Autowired
    IMarcaProductoDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        return elementoDAO.obtenerSiguienteId();
    }
    
    //Obtiene la lista completa
    public List<MarcaProducto> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista por nombre
    public List<MarcaProducto> listarPorNombre(String nombre) {
        if(nombre.equals("***")) {
            return elementoDAO.findAll();
        } else {
            return elementoDAO.findByNombreContaining(nombre);
        }
    }
    
    //Agrega un registro
    public void agregar(MarcaProducto elemento) {
        elementoDAO.save(elemento);
    }
    
    //Actualiza un registro
    public void actualizar(MarcaProducto elemento) {
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    public void eliminar(MarcaProducto elemento) {
        elementoDAO.delete(elemento);
    }
    
}
