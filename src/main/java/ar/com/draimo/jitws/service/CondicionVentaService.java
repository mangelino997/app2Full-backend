package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.ICondicionVentaDAO;
import ar.com.draimo.jitws.model.CondicionVenta;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio CondicionVenta
 * @author blas
 */

@Service
public class CondicionVentaService {
    
    //Define la referencia al dao
    @Autowired
    ICondicionVentaDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        CondicionVenta elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId()+1 : 1;
    }
    
    //Obtiene la lista completa
    public List<CondicionVenta> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista por nombre
    public List<CondicionVenta> listarPorNombre(String nombre) {
        if(nombre.equals("***")) {
            return elementoDAO.findAll();
        } else {
            return elementoDAO.findByNombreContaining(nombre);
        }
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public CondicionVenta agregar(CondicionVenta elemento) {
        elemento = formatearStrings(elemento);
        return elementoDAO.save(elemento);
    }
    
    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(CondicionVenta elemento) {
        elemento = formatearStrings(elemento);
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(CondicionVenta elemento) {
        elementoDAO.delete(elemento);
    }
    
    //Formatea los strings
    private CondicionVenta formatearStrings(CondicionVenta elemento) {
        elemento.setNombre(elemento.getNombre().trim());
        return elemento;
    }
    
}
