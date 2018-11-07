package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IRolDAO;
import ar.com.draimo.jitws.dao.IRolSubopcionDAO;
import ar.com.draimo.jitws.dao.ISubopcionDAO;
import ar.com.draimo.jitws.model.Rol;
import ar.com.draimo.jitws.model.RolSubopcion;
import ar.com.draimo.jitws.model.Subopcion;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio Rol
 * @author blas
 */

@Service
public class RolService {

    //Define la referencia al dao
    @Autowired
    IRolDAO elementoDAO;
    
    //Define la referencia al dao subopcion
    @Autowired
    ISubopcionDAO subopcionDAO;
    
    //Define la referencia al dao rolsubopcion
    @Autowired
    IRolSubopcionDAO rolSubopcionDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        Rol elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId()+1 : 1;
    }
    
    //Obtiene la lista completa
    public List<Rol> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista por nombre
    public List<Rol> listarPorNombre(String nombre) {
        if(nombre.equals("***")) {
            return elementoDAO.findAll();
        } else {
            return elementoDAO.findByNombreContaining(nombre);
        }
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public Rol agregar(Rol elemento) {
        elemento = formatearStrings(elemento);
        Rol rol = elementoDAO.saveAndFlush(elemento);
        //Obtiene la lista completa de subopciones
        List<Subopcion> subopciones = subopcionDAO.findAll();
        //Define un RolSubopcion
        RolSubopcion rolSubopcion;
        //Recorre la lista de submodulos
        for (Subopcion subopcion : subopciones) {
            //Crea una instancia de RolSubopcion
            rolSubopcion = new RolSubopcion();
            rolSubopcion.setRol(rol);
            rolSubopcion.setSubopcion(subopcion);
            rolSubopcion.setMostrar(false);
            rolSubopcionDAO.saveAndFlush(rolSubopcion);
        }
        return rol;
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(Rol elemento) {
        elemento = formatearStrings(elemento);
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(Rol elemento) {
        Optional<Rol> rol = elementoDAO.findById(elemento.getId());
        //Obtiene una lista de RolSubopcion por idRol
        List<RolSubopcion> rolesSubopcion = rolSubopcionDAO.findByRol(rol);
        rolesSubopcion.forEach((rolSubopcion) -> {
            rolSubopcionDAO.delete(rolSubopcion);
        });
        elementoDAO.delete(elemento);
    }
    
    //Formatea los strings
    private Rol formatearStrings(Rol elemento) {
        elemento.setNombre(elemento.getNombre().trim());
        return elemento;
    }

}
