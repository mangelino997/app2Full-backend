package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IOpcionDAO;
import ar.com.draimo.jitws.dao.ISubopcionDAO;
import ar.com.draimo.jitws.model.Opcion;
import ar.com.draimo.jitws.model.Subopcion;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio Moneda
 * @author blas
 */

@Service
public class OpcionService {
    
    //Define la referencia al dao
    @Autowired
    IOpcionDAO elementoDAO;
    
    //Define la referencia al dao subopcion
    @Autowired
    ISubopcionDAO subopcionDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        Opcion elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId()+1 : 1;
    }
    
    //Obtiene la lista completa
    public List<Opcion> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista por nombre
    public List<Opcion> listarPorNombre(String nombre) {
        if(nombre.equals("***")) {
            return elementoDAO.findAll();
        } else {
            return elementoDAO.findByNombreContaining(nombre);
        }
    }
    
    //Obtiene una lista por subopcion
    public List<Opcion> listarPorSubopcion(int idSubopcion) {
        //Obtiene la subopcion por id
        Optional<Subopcion> subopcion = subopcionDAO.findById(idSubopcion);
        return elementoDAO.findBySubopcion(subopcion);
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public Opcion agregar(Opcion elemento) {
        elemento = formatearStrings(elemento);
        return elementoDAO.save(elemento);
    }
    
    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(Opcion elemento) {
        elemento = formatearStrings(elemento);
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(Opcion elemento) {
        elementoDAO.delete(elemento);
    }
    
    //Formatea los strings
    private Opcion formatearStrings(Opcion elemento) {
        elemento.setNombre(elemento.getNombre().trim());
        return elemento;
    }
    
}
