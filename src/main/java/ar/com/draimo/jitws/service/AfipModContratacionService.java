//Paquete al que pertenece el servicio
package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.constant.Funcion;
import ar.com.draimo.jitws.dao.IAfipModContratacionDAO;
import ar.com.draimo.jitws.model.AfipModContratacion;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio AfipModContratacion
 * @author blas
 */

@Service
public class AfipModContratacionService {
    
    //Define el dao
    @Autowired
    IAfipModContratacionDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        AfipModContratacion elemento = elementoDAO.findTopByOrderByIdDesc();
        return (elemento!=null?elemento.getId()+1:1);
    }
    
    //Obtiene la lista completa
    public List<AfipModContratacion> listar() {
        return elementoDAO.findByOrderByCodigoAfipAsc();
    }
    
    //Obtiene una lista por alias
    public List<AfipModContratacion> listarPorAlias(String alias) {
        return alias.equals("*") ? elementoDAO.findByOrderByCodigoAfipAsc()
                : elementoDAO.findByAliasContaining(alias);
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public AfipModContratacion agregar(AfipModContratacion elemento) {
        formatearString(elemento);
        return elementoDAO.save(elemento);
    }
    
    //Establece el alias de un registro
    @Transactional(rollbackFor = Exception.class)
    public void establecerAlias(AfipModContratacion elemento) {
        elemento.setAlias(elemento.getCodigoAfip()
                + " - " + elemento.getNombre());
        elementoDAO.save(elemento);
    }
    
    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(AfipModContratacion elemento) {
        formatearString(elemento);
        establecerAlias(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }
    
    //Formatea los string 
    private AfipModContratacion formatearString(AfipModContratacion elemento) {
        elemento.setNombre(Funcion.convertirATitulo(elemento.getNombre().trim()));
        return elemento;
    }
    
}