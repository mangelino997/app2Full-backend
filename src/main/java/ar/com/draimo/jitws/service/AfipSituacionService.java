//Paquete al que pertenece el servicio
package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.constant.Funcion;
import ar.com.draimo.jitws.dao.IAfipSituacionDAO;
import ar.com.draimo.jitws.model.AfipSituacion;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio AfipSituacion
 * @author blas
 */

@Service
public class AfipSituacionService {
    
    //Define el dao
    @Autowired
    IAfipSituacionDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        AfipSituacion elemento = elementoDAO.findTopByOrderByIdDesc();
        return (elemento!=null?elemento.getId()+1:1);
    }
    
    //Obtiene la lista completa
    public List<AfipSituacion> listar() {
        return elementoDAO.findByOrderByCodigoAfipAsc();
    }
    
    //Obtiene una lista por alias
    public List<AfipSituacion> listarPorAlias(String alias) {
        return alias.equals("*") ? elementoDAO.findByOrderByCodigoAfipAsc()
                : elementoDAO.findByAliasContaining(alias);
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public AfipSituacion agregar(AfipSituacion elemento) {
        formatearString(elemento);
        return elementoDAO.save(elemento);
    }
    
    //Establece el alias de un registro
    @Transactional(rollbackFor = Exception.class)
    public void establecerAlias(AfipSituacion elemento) {
        elemento.setAlias(elemento.getCodigoAfip()
                + " - " + elemento.getNombre());
        elementoDAO.save(elemento);
    }
    
    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(AfipSituacion elemento) {
        formatearString(elemento);
        establecerAlias(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }
    
    //Formatea los string 
    private AfipSituacion formatearString(AfipSituacion elemento) {
        elemento.setNombre(Funcion.convertirATitulo(elemento.getNombre().trim()));
        return elemento;
    } 
    
}