package ar.com.wecoode.jitws.service;

import ar.com.wecoode.jitws.dao.IChoferProveedorDAO;
import ar.com.wecoode.jitws.model.ChoferProveedor;
import ar.com.wecoode.jitws.model.Proveedor;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Servicio Chofer Proveedor
 * @author blas
 */

@Service
public class ChoferProveedorService {
    
    //Define la referencia al dao
    @Autowired
    IChoferProveedorDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        return elementoDAO.obtenerSiguienteId();
    }
    
    //Obtiene la lista completa
    public List<ChoferProveedor> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista por nombre
    public List<ChoferProveedor> listarPorNombre(String nombre) {
        if(nombre.equals("***")) {
            return elementoDAO.findAll();
        } else {
            return elementoDAO.findByNombreContaining(nombre);
        }
    }
    
    //Obtiene una lista por proveedor
    public List<ChoferProveedor> listarPorProveedor(Optional<Proveedor> proveedor) {
        return elementoDAO.findByProveedor(proveedor);
    }
    
    //Agrega un registro
    public void agregar(ChoferProveedor elemento) {
        elementoDAO.save(elemento);
    }
    
    //Actualiza un registro
    public void actualizar(ChoferProveedor elemento) {
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    public void eliminar(ChoferProveedor elemento) {
        elementoDAO.delete(elemento);
    }
    
}
