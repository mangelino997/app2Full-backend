package ar.com.wecode.jitws.service;

import ar.com.wecode.jitws.dao.IProvinciaDAO;
import ar.com.wecode.jitws.dao.IPaisDAO;
import ar.com.wecode.jitws.model.Provincia;
import ar.com.wecode.jitws.model.Pais;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author blas
 */

@Service
public class ProvinciaService {

    @Autowired
    IProvinciaDAO elementoDAO;
    
    @Autowired
    IPaisDAO paisDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        return elementoDAO.obtenerSiguienteId();
    }
    
    public List<Provincia> listar() {
        return elementoDAO.findAll();
    }

    public Provincia save(Provincia elemento) {
        return elementoDAO.saveAndFlush(elemento);
    }

    public Provincia update(Provincia elemento) {
        return elementoDAO.save(elemento);
    }

    public List<Provincia> listarPorNombre(String nombre) {
        return elementoDAO.findByNombreContaining(nombre);
    }
    
    public List<Provincia> listarPorPais(int id) {
        Optional<Pais> p = paisDAO.findById(id);
        return elementoDAO.findByPais(p);
    }

}
