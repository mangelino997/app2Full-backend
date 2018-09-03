package ar.com.wecoode.jitws.service;

import ar.com.wecoode.jitws.dao.IZonaDAO;
import ar.com.wecoode.jitws.model.Zona;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Servicio Zona
 * @author blas
 */

@Service
public class ZonaService {

    //Define la referencia al dao
    @Autowired
    IZonaDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        return elementoDAO.obtenerSiguienteId();
    }
    
    //Obtiene la lista completa
    public List<Zona> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista por nombre
    public List<Zona> listarPorNombre(String nombre) {
        return elementoDAO.findByNombreContaining(nombre);
    }

    //Agrega un registro
    public void agregar(Zona elemento) {
        elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    public void actualizar(Zona elemento) {
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    public void eliminar(Zona elemento) {
        elementoDAO.delete(elemento);
    }

}
