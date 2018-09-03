package ar.com.wecoode.jitws.service;

import ar.com.wecoode.jitws.dao.IUnidadMedidaDAO;
import ar.com.wecoode.jitws.model.UnidadMedida;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return elementoDAO.obtenerSiguienteId();
    }
    
    //Obtiene la lista completa
    public List<UnidadMedida> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista por nombre
    public List<UnidadMedida> listarPorNombre(String nombre) {
        return elementoDAO.findByNombreContaining(nombre);
    }

    //Agrega un registro
    public void agregar(UnidadMedida elemento) {
        elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    public void actualizar(UnidadMedida elemento) {
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    public void eliminar(UnidadMedida elemento) {
        elementoDAO.delete(elemento);
    }

}
