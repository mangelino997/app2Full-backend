package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.constant.Funcion;
import ar.com.draimo.jitws.dao.IUnidadMedidaDAO;
import ar.com.draimo.jitws.model.UnidadMedida;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio Pais
 * @author blas
 */

@Service
public class UnidadMedidaService {

    //Define la referencia al dao
    @Autowired
    IUnidadMedidaDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        UnidadMedida elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento.getId()+1;
    }
    
    //Obtiene la lista completa
    public List<UnidadMedida> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista por nombre
    public List<UnidadMedida> listarPorNombre(String nombre) {
        if(nombre.equals("***")) {
            return elementoDAO.findAll();
        } else {
            return elementoDAO.findByNombreContaining(nombre);
        }
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public UnidadMedida agregar(UnidadMedida elemento) {
        elemento = formatearStrings(elemento);
        return elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(UnidadMedida elemento) {
        elemento = formatearStrings(elemento);
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(UnidadMedida elemento) {
        elementoDAO.delete(elemento);
    }
    
    //Formatea los strings
    private UnidadMedida formatearStrings(UnidadMedida elemento) {
        elemento.setNombre(Funcion.convertirATitulo(elemento.getNombre().trim()));
        return elemento;
    }

}
