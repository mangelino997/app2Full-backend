package ar.com.wecoode.jitws.service;

import ar.com.wecoode.jitws.dao.IRubroProductoDAO;
import ar.com.wecoode.jitws.model.RubroProducto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Servicio RubroProducto
 * @author blas
 */

@Service
public class RubroProductoService {

    //Define la referencia al dao
    @Autowired
    IRubroProductoDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        return elementoDAO.obtenerSiguienteId();
    }
    
    //Obtiene la lista completa
    public List<RubroProducto> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista por nombre
    public List<RubroProducto> listarPorNombre(String nombre) {
        return elementoDAO.findByNombreContaining(nombre);
    }

    //Agrega un registro
    public void agregar(RubroProducto elemento) {
        elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    public void actualizar(RubroProducto elemento) {
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    public void eliminar(RubroProducto elemento) {
        elementoDAO.delete(elemento);
    }

}
