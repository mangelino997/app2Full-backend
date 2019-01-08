package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.model.AfipCondicionIva;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ar.com.draimo.jitws.dao.IAfipCondicionIvaDAO;

/**
 * Servicio Afip Condicion Iva
 * @author blas
 */

@Service
public class AfipCondicionIvaService {
    
    //Define la referencia al dao
    @Autowired
    IAfipCondicionIvaDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        AfipCondicionIva elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId()+1 : 1;
    }
    
    //Obtiene la lista completa
    public List<AfipCondicionIva> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista por nombre
    public List<AfipCondicionIva> listarPorNombre(String nombre) {
        if(nombre.equals("***")) {
            return elementoDAO.findAll();
        } else {
            return elementoDAO.findByNombreContaining(nombre);
        }
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public AfipCondicionIva agregar(AfipCondicionIva elemento) {
        elemento = formatearStrings(elemento);
        return elementoDAO.save(elemento);
    }
    
    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(AfipCondicionIva elemento) {
        elemento = formatearStrings(elemento);
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(AfipCondicionIva elemento) {
        elementoDAO.delete(elemento);
    }
    
    //Formatea los strings
    private AfipCondicionIva formatearStrings(AfipCondicionIva elemento) {
        elemento.setNombre(elemento.getNombre().trim());
        return elemento;
    }
    
}