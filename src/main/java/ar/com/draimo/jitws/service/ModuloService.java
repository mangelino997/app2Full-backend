//Paquete al que pertence el servicio
package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IModuloDAO;
import ar.com.draimo.jitws.model.Modulo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio Modulo
 * @author blas
 */

@Service
public class ModuloService {
    
    //Define la referencia al dao
    @Autowired
    IModuloDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        Modulo elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId()+1 : 1;
    }
    
    //Obtiene la lista completa
    public List<Modulo> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista por nombre
    public List<Modulo> listarPorNombre(String nombre) {
        return nombre.equals("*")? elementoDAO.findAll():
            elementoDAO.findByNombreContaining(nombre);
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public Modulo agregar(Modulo elemento) {
        elemento = formatearStrings(elemento);
        return elementoDAO.saveAndFlush(elemento);
    }
    
    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(Modulo elemento) {
        elemento = formatearStrings(elemento);
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }
    
    //Formatea los strings
    private Modulo formatearStrings(Modulo elemento) {
        elemento.setNombre(elemento.getNombre().trim());
        return elemento;
    }
    
}