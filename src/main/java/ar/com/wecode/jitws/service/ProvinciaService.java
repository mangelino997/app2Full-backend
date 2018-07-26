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
 * Servicio Provincia
 * @author blas
 */

@Service
public class ProvinciaService {

    //Define la referencia al dao
    @Autowired
    IProvinciaDAO elementoDAO;
    
    //Define la referencia al dao pais
    @Autowired
    IPaisDAO paisDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        return elementoDAO.obtenerSiguienteId();
    }
    
    //Obtiene la lista completa
    public List<Provincia> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista por nombre
    public List<Provincia> listarPorNombre(String nombre) {
        return elementoDAO.findByNombreContaining(nombre);
    }
    
    //Obtiene una lista por pais
    public List<Provincia> listarPorPais(int id) {
        Optional<Pais> p = paisDAO.findById(id);
        return elementoDAO.findByPais(p);
    }

    //Agrega un registro
    public void agregar(Provincia elemento) {
        elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    public void actualizar(Provincia elemento) {
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    public void eliminar(Provincia elemento) {
        elementoDAO.delete(elemento);
    }

}
