package ar.com.wecoode.jitws.service;

import ar.com.wecoode.jitws.dao.IRubroDAO;
import ar.com.wecoode.jitws.model.Rubro;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Servicio Rubro
 * @author blas
 */

@Service
public class RubroService {

    //Define la referencia al dao
    @Autowired
    IRubroDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        return elementoDAO.obtenerSiguienteId();
    }
    
    //Obtiene la lista completa
    public List<Rubro> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista por nombre
    public List<Rubro> listarPorNombre(String nombre) {
        return elementoDAO.findByNombreContaining(nombre);
    }

    //Agrega un registro
    public void agregar(Rubro elemento) {
        elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    public void actualizar(Rubro elemento) {
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    public void eliminar(Rubro elemento) {
        elementoDAO.delete(elemento);
    }

}
