package ar.com.wecoode.jitws.service;

import ar.com.wecoode.jitws.dao.ISindicatoDAO;
import ar.com.wecoode.jitws.model.Sindicato;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Servicio Sindicato
 * @author blas
 */

@Service
public class SindicatoService {

    //Define la referencia al dao
    @Autowired
    ISindicatoDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        return elementoDAO.obtenerSiguienteId();
    }
    
    //Obtiene la lista completa
    public List<Sindicato> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista por nombre
    public List<Sindicato> listarPorNombre(String nombre) {
        return elementoDAO.findByNombreContaining(nombre);
    }

    //Agrega un registro
    public void agregar(Sindicato elemento) {
        elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    public void actualizar(Sindicato elemento) {
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    public void eliminar(Sindicato elemento) {
        elementoDAO.delete(elemento);
    }

}
