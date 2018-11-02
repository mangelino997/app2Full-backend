package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.constant.Funcion;
import ar.com.draimo.jitws.dao.ICondicionCompraDAO;
import ar.com.draimo.jitws.model.CondicionCompra;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio Condicion Compra
 * @author blas
 */

@Service
public class CondicionCompraService {
    
    //Define la referencia al dao
    @Autowired
    ICondicionCompraDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        CondicionCompra elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento.getId()+1;
    }
    
    //Obtiene una lista completa
    public List<CondicionCompra> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista por nombre
    public List<CondicionCompra> listarPorNombre(String nombre) {
        if(nombre.equals("***")) {
            return elementoDAO.findAll();
        } else {
            return elementoDAO.findByNombreContaining(nombre);
        }
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public CondicionCompra agregar(CondicionCompra elemento) {
        elemento = formatearStrings(elemento);
        return elementoDAO.save(elemento);
    }
    
    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(CondicionCompra elemento) {
        elemento = formatearStrings(elemento);
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(CondicionCompra elemento) {
        elementoDAO.delete(elemento);
    }
    
    //Formatea los strings
    private CondicionCompra formatearStrings(CondicionCompra elemento) {
        elemento.setNombre(Funcion.convertirATitulo(elemento.getNombre().trim()));
        return elemento;
    }
    
}