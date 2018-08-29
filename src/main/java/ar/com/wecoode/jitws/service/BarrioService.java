package ar.com.wecoode.jitws.service;

import ar.com.wecoode.jitws.dao.IBarrioDAO;
import ar.com.wecoode.jitws.model.Barrio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author blas
 */

@Service
public class BarrioService {

    @Autowired
    IBarrioDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        return elementoDAO.obtenerSiguienteId();
    }
    
    //Obtiene la lista completa
    public List<Barrio> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista por nombre
    public List<Barrio> listarPorNombre(String nombre) {
        if(nombre.equals("***")) {
            return elementoDAO.findAll();
        } else {
            return elementoDAO.findByNombreContaining(nombre);
        }
    }

    //Agrega un registro
    public Barrio save(Barrio barrio) {
        return elementoDAO.saveAndFlush(barrio);
    }

    //Actualiza un registro
    public Barrio update(Barrio barrio) {
        return elementoDAO.save(barrio);
    }

}
