package ar.com.wecoode.jitws.service;

import ar.com.wecoode.jitws.dao.IInsumoDAO;
import ar.com.wecoode.jitws.model.Insumo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Servicio Insumo
 * @author blas
 */

@Service
public class InsumoService {
    
    //Define la referencia al dao
    @Autowired
    IInsumoDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        return elementoDAO.obtenerSiguienteId();
    }
    
    //Obtiene la lista completa
    public List<Insumo> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista por nombre
    public List<Insumo> listarPorNombre(String nombre) {
        if(nombre.equals("***")) {
            return elementoDAO.findAll();
        } else {
            return elementoDAO.findByNombreContaining(nombre);
        }
    }
    
    //Obtiene una lista de insumos combustibles
    public List<Insumo> listarCombustibles() {
        return elementoDAO.findByEsCombustible(1);
    }
    
    //Obtiene una lista de insumos no combustibles
    public List<Insumo> listarNoCombustibles() {
        return elementoDAO.findByEsCombustible(0);
    }
    
    //Agrega un registro
    public void agregar(Insumo elemento) {
        elementoDAO.save(elemento);
    }
    
    //Actualiza un registro
    public void actualizar(Insumo elemento) {
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    public void eliminar(Insumo elemento) {
        elementoDAO.delete(elemento);
    }
    
}
