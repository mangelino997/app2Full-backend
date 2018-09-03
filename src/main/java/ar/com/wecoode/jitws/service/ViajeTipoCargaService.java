package ar.com.wecoode.jitws.service;

import ar.com.wecoode.jitws.dao.IViajeTipoCargaDAO;
import ar.com.wecoode.jitws.model.ViajeTipoCarga;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Servicio ViajeTipoCarga
 * @author blas
 */

@Service
public class ViajeTipoCargaService {

    //Define la referencia al dao
    @Autowired
    IViajeTipoCargaDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        return elementoDAO.obtenerSiguienteId();
    }
    
    //Obtiene la lista completa
    public List<ViajeTipoCarga> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista por nombre
    public List<ViajeTipoCarga> listarPorNombre(String nombre) {
        return elementoDAO.findByNombreContaining(nombre);
    }

    //Agrega un registro
    public void agregar(ViajeTipoCarga elemento) {
        elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    public void actualizar(ViajeTipoCarga elemento) {
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    public void eliminar(ViajeTipoCarga elemento) {
        elementoDAO.delete(elemento);
    }

}
