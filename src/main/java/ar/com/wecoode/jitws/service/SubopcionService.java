package ar.com.wecoode.jitws.service;

import ar.com.wecoode.jitws.dao.ISubmoduloDAO;
import ar.com.wecoode.jitws.dao.ISubopcionDAO;
import ar.com.wecoode.jitws.model.Submodulo;
import ar.com.wecoode.jitws.model.Subopcion;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Servicio Submodulo
 * @author blas
 */

@Service
public class SubopcionService {

    //Define la referencia al dao
    @Autowired
    ISubopcionDAO elementoDAO;
    
    //Define la referencia al dao modulo
    @Autowired
    ISubmoduloDAO submoduloDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        return elementoDAO.obtenerSiguienteId();
    }
    
    //Obtiene la lista completa
    public List<Subopcion> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista por submodulo
    public List<Subopcion> listarPorSubmodulo(int idSubmodulo) {
        //Obtiene el submodulo por id
        Optional<Submodulo> submodulo = submoduloDAO.findById(idSubmodulo);
        return elementoDAO.findBySubmodulo(submodulo);
    }
    
    //Obtiene una lista por nombre
    public List<Subopcion> listarPorNombre(String nombre) {
        return elementoDAO.findByNombreContaining(nombre);
    }

    //Agrega un registro
    public void agregar(Subopcion elemento) {
        elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    public void actualizar(Subopcion elemento) {
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    public void eliminar(Subopcion elemento) {
        elementoDAO.delete(elemento);
    }

}
