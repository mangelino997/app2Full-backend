package ar.com.wecode.jitws.service;

import ar.com.wecode.jitws.dao.ILocalidadDAO;
import ar.com.wecode.jitws.dao.IProvinciaDAO;
import ar.com.wecode.jitws.model.Localidad;
import ar.com.wecode.jitws.model.Provincia;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Servicio Localidad
 * @author blas
 */

@Service
public class LocalidadService {

    //Define la referencia al dao
    @Autowired
    ILocalidadDAO elementoDAO;
    
    //Define la referencia al dao provincia
    @Autowired
    IProvinciaDAO provinciaDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        return elementoDAO.obtenerSiguienteId();
    }
    
    //Obtiene una lista completa
    public List<Localidad> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista por nombre
    public List<Localidad> listarPorNombre(String nombre) {
        if(nombre.equals("***")) {
            return elementoDAO.findAll();
        } else {
            return elementoDAO.findByNombreContaining(nombre);
        }
    }
    
    //Obtiene una lista por provincia
    public List<Localidad> listarPorProvincia(Optional<Provincia> provincia) {
        return elementoDAO.findByProvincia(provincia);
    }

    //Agrega un registro
    public void agregar(Localidad elemento) {
        elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    public void actualizar(Localidad elemento) {
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    public void eliminar(Localidad elemento) {
        elementoDAO.delete(elemento);
    } 

}
