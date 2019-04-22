package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.constant.Funcion;
import ar.com.draimo.jitws.dao.IAfipLocalidadDAO;
import ar.com.draimo.jitws.model.AfipLocalidad;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio AfipLocalidad
 * @author blas
 */

@Service
public class AfipLocalidadService {
    
    //Define el dao
    @Autowired
    IAfipLocalidadDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        AfipLocalidad elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento.getId()+1;
    }
    
    //Obtiene la lista completa
    public List<AfipLocalidad> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista por alias
    public List<AfipLocalidad> listarPorAlias(String alias) {
        if(alias.equals("***")) {
            return elementoDAO.findAll();
        } else {
            return elementoDAO.findByAliasContaining(alias);
        }
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public AfipLocalidad agregar(AfipLocalidad elemento) {
        formatearString(elemento);
        return elementoDAO.save(elemento);
    }
    
    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(AfipLocalidad elemento) {
        formatearString(elemento);
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(AfipLocalidad elemento) {
        elementoDAO.delete(elemento);
    }
    
    //Formatea los string 
    private AfipLocalidad formatearString(AfipLocalidad elemento) {
        elemento.setNombre(Funcion.convertirATitulo(elemento.getNombre().trim()));
        elemento.setAlias(elemento.getCodigoAfip() + " - " + elemento.getNombre());
        return elemento;
    }
    
}
