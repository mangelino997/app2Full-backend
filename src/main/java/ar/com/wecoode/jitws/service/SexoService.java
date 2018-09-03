package ar.com.wecoode.jitws.service;

import ar.com.wecoode.jitws.dao.ISexoDAO;
import ar.com.wecoode.jitws.model.Sexo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Servicio Sexo
 * @author blas
 */

@Service
public class SexoService {

    //Define la referencia al dao
    @Autowired
    ISexoDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        return elementoDAO.obtenerSiguienteId();
    }
    
    //Obtiene la lista completa
    public List<Sexo> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista por nombre
    public List<Sexo> listarPorNombre(String nombre) {
        return elementoDAO.findByNombreContaining(nombre);
    }

    //Agrega un registro
    public void agregar(Sexo elemento) {
        elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    public void actualizar(Sexo elemento) {
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    public void eliminar(Sexo elemento) {
        elementoDAO.delete(elemento);
    }

}
