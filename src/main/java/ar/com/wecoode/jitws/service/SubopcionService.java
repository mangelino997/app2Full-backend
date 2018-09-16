package ar.com.wecoode.jitws.service;

import ar.com.wecoode.jitws.constant.Funcion;
import ar.com.wecoode.jitws.dao.ISubmoduloDAO;
import ar.com.wecoode.jitws.dao.ISubopcionDAO;
import ar.com.wecoode.jitws.model.Submodulo;
import ar.com.wecoode.jitws.model.Subopcion;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        Subopcion elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento.getId()+1;
    }
    
    //Obtiene la lista completa
    public List<Subopcion> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista por nombre
    public List<Subopcion> listarPorNombre(String nombre) {
        if(nombre.equals("***")) {
            return elementoDAO.findAll();
        } else {
            return elementoDAO.findByNombreContaining(nombre);
        }
    }
    
    //Obtiene una lista por submodulo
    public List<Subopcion> listarPorSubmodulo(int idSubmodulo) {
        //Obtiene el submodulo por id
        Optional<Submodulo> submodulo = submoduloDAO.findById(idSubmodulo);
        return elementoDAO.findBySubmodulo(submodulo);
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public Subopcion agregar(Subopcion elemento) {
        elemento = formatearStrings(elemento);
        return elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(Subopcion elemento) {
        elemento = formatearStrings(elemento);
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(Subopcion elemento) {
        elementoDAO.delete(elemento);
    }
    
    //Formatea los strings
    private Subopcion formatearStrings(Subopcion elemento) {
        elemento.setNombre(Funcion.convertirATitulo(elemento.getNombre().trim()));
        return elemento;
    }

}
