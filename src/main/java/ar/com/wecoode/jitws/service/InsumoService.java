package ar.com.wecoode.jitws.service;

import ar.com.wecoode.jitws.constant.Funcion;
import ar.com.wecoode.jitws.dao.IInsumoDAO;
import ar.com.wecoode.jitws.model.Insumo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        Insumo elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento.getId()+1;
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
    @Transactional(rollbackFor = Exception.class)
    public Insumo agregar(Insumo elemento) {
        elemento = formatearStrings(elemento);
        return elementoDAO.save(elemento);
    }
    
    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(Insumo elemento) {
        elemento = formatearStrings(elemento);
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(Insumo elemento) {
        elementoDAO.delete(elemento);
    }
    
    //Formatea los strings
    private Insumo formatearStrings(Insumo elemento) {
        elemento.setNombre(Funcion.convertirATitulo(elemento.getNombre().trim()));
        return elemento;
    }
    
}
