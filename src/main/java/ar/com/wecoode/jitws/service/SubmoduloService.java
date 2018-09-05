package ar.com.wecoode.jitws.service;

import ar.com.wecoode.jitws.dao.IModuloDAO;
import ar.com.wecoode.jitws.dao.ISubmoduloDAO;
import ar.com.wecoode.jitws.model.Modulo;
import ar.com.wecoode.jitws.model.Submodulo;
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
public class SubmoduloService {

    //Define la referencia al dao
    @Autowired
    ISubmoduloDAO elementoDAO;
    
    //Define la referencia al dao modulo
    @Autowired
    IModuloDAO moduloDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        return elementoDAO.obtenerSiguienteId();
    }
    
    //Obtiene la lista completa
    public List<Submodulo> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista por modulo
    public List<Submodulo> listarPorModulo(int idModulo) {
        //Obtiene el modulo por id
        Optional<Modulo> modulo = moduloDAO.findById(idModulo);
        return elementoDAO.findByModulo(modulo);
    }
    
    //Obtiene una lista por nombre
    public List<Submodulo> listarPorNombre(String nombre) {
        return elementoDAO.findByNombreContaining(nombre);
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public void agregar(Submodulo elemento) {
        elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(Submodulo elemento) {
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(Submodulo elemento) {
        elementoDAO.delete(elemento);
    }

}
