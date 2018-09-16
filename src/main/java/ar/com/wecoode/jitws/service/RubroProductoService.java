package ar.com.wecoode.jitws.service;

import ar.com.wecoode.jitws.constant.Funcion;
import ar.com.wecoode.jitws.dao.IRubroProductoDAO;
import ar.com.wecoode.jitws.model.RubroProducto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio RubroProducto
 * @author blas
 */

@Service
public class RubroProductoService {

    //Define la referencia al dao
    @Autowired
    IRubroProductoDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        RubroProducto elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento.getId()+1;
    }
    
    //Obtiene la lista completa
    public List<RubroProducto> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista por nombre
    public List<RubroProducto> listarPorNombre(String nombre) {
        if(nombre.equals("***")) {
            return elementoDAO.findAll();
        } else {
            return elementoDAO.findByNombreContaining(nombre);
        }
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public RubroProducto agregar(RubroProducto elemento) {
        elemento = formatearStrings(elemento);
        return elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(RubroProducto elemento) {
        elemento = formatearStrings(elemento);
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(RubroProducto elemento) {
        elementoDAO.delete(elemento);
    }
    
    //Formatea los strings
    private RubroProducto formatearStrings(RubroProducto elemento) {
        elemento.setNombre(Funcion.convertirATitulo(elemento.getNombre().trim()));
        return elemento;
    }

}
