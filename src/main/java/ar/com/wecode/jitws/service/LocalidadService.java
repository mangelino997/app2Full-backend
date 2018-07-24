package ar.com.wecode.jitws.service;

import ar.com.wecode.jitws.dao.ILocalidadDAO;
import ar.com.wecode.jitws.dao.IPaisDAO;
import ar.com.wecode.jitws.dao.IProvinciaDAO;
import ar.com.wecode.jitws.model.Localidad;
import ar.com.wecode.jitws.model.Provincia;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author blas
 */

@Service
public class LocalidadService {

    @Autowired
    ILocalidadDAO elementoDAO;
    
    @Autowired
    IProvinciaDAO provinciaDAO;
    
    @Autowired
    IPaisDAO paisDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        return elementoDAO.obtenerSiguienteId();
    }
    
    public List<Localidad> listar() {
        return elementoDAO.findAll();
    }

    public Localidad save(Localidad elemento) {
        return elementoDAO.saveAndFlush(elemento);
    }

    public Localidad update(Localidad elemento) {
        return elementoDAO.save(elemento);
    }

    public List<Localidad> listarPorNombre(String nombre) {
        return elementoDAO.findByNombreContaining(nombre);
    }
    
    public List<Localidad> listarPorProvincia(int id) {
        Optional<Provincia> provincia = provinciaDAO.findById(id);
        return elementoDAO.findByProvincia(provincia);
    }

}
