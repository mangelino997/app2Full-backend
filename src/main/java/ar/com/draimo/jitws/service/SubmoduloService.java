package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.constant.Funcion;
import ar.com.draimo.jitws.dao.IModuloDAO;
import ar.com.draimo.jitws.dao.ISubmoduloDAO;
import ar.com.draimo.jitws.model.Modulo;
import ar.com.draimo.jitws.model.Submodulo;
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
        Submodulo elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento.getId()+1;
    }
    
    //Obtiene la lista completa
    public List<Submodulo> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista por nombre
    public List<Submodulo> listarPorNombre(String nombre) {
        if(nombre.equals("***")) {
            return elementoDAO.findAll();
        } else {
            return elementoDAO.findByNombreContaining(nombre);
        }
    }
    
    //Obtiene una lista por modulo
    public List<Submodulo> listarPorModulo(int idModulo) {
        //Obtiene el modulo por id
        Optional<Modulo> modulo = moduloDAO.findById(idModulo);
        return elementoDAO.findByModulo(modulo);
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public Submodulo agregar(Submodulo elemento) {
        elemento = formatearStrings(elemento);
        return elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(Submodulo elemento) {
        elemento = formatearStrings(elemento);
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(Submodulo elemento) {
        elementoDAO.delete(elemento);
    }
    
    //Formatea los strings
    private Submodulo formatearStrings(Submodulo elemento) {
        elemento.setNombre(Funcion.convertirATitulo(elemento.getNombre().trim()));
        return elemento;
    }

}
