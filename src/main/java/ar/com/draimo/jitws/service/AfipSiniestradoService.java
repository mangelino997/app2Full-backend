//Paquete al que pertenece el servicio
package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.constant.Funcion;
import ar.com.draimo.jitws.dao.IAfipSiniestradoDAO;
import ar.com.draimo.jitws.model.AfipSiniestrado;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio AfipSiniestrado
 * @author blas
 */

@Service
public class AfipSiniestradoService {
    
    //Define el dao
    @Autowired
    IAfipSiniestradoDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        AfipSiniestrado elemento = elementoDAO.findTopByOrderByIdDesc();
        return (elemento!=null?elemento.getId()+1:1);
    }
    
    //Obtiene la lista completa
    public List<AfipSiniestrado> listar() {
        return elementoDAO.findByOrderByCodigoAfipAsc();
    }
    
    //Obtiene una lista por alias
    public List<AfipSiniestrado> listarPorAlias(String alias) {
        return alias.equals("***") ? elementoDAO.findByOrderByCodigoAfipAsc()
                : elementoDAO.findByAliasContaining(alias);
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public AfipSiniestrado agregar(AfipSiniestrado elemento) {
        formatearString(elemento);
        return elementoDAO.save(elemento);
    }
    
    //Establece el alias de un registro
    @Transactional(rollbackFor = Exception.class)
    public void establecerAlias(AfipSiniestrado elemento) {
        elemento.setAlias(elemento.getCodigoAfip()
                + " - " + elemento.getNombre());
        elementoDAO.save(elemento);
    }
    
    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(AfipSiniestrado elemento) {
        formatearString(elemento);
        establecerAlias(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }
    
    //Formatea los string 
    private AfipSiniestrado formatearString(AfipSiniestrado elemento) {
        elemento.setNombre(Funcion.convertirATitulo(elemento.getNombre().trim()));
        return elemento;
    }
    
}