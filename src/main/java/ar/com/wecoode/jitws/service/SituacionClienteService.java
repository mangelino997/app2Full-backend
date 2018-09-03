package ar.com.wecoode.jitws.service;

import ar.com.wecoode.jitws.dao.ISituacionClienteDAO;
import ar.com.wecoode.jitws.model.SituacionCliente;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Servicio SituacionCliente
 * @author blas
 */

@Service
public class SituacionClienteService {

    //Define la referencia al dao
    @Autowired
    ISituacionClienteDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        return elementoDAO.obtenerSiguienteId();
    }
    
    //Obtiene la lista completa
    public List<SituacionCliente> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista por nombre
    public List<SituacionCliente> listarPorNombre(String nombre) {
        return elementoDAO.findByNombreContaining(nombre);
    }

    //Agrega un registro
    public void agregar(SituacionCliente elemento) {
        elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    public void actualizar(SituacionCliente elemento) {
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    public void eliminar(SituacionCliente elemento) {
        elementoDAO.delete(elemento);
    }

}
