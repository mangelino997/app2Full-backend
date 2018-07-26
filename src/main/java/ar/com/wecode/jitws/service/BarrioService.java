package ar.com.wecode.jitws.service;

import ar.com.wecode.jitws.dao.IBarrioDAO;
import ar.com.wecode.jitws.model.Barrio;
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
    IBarrioDAO barrioDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        return barrioDAO.obtenerSiguienteId();
    }
    
    //Obtiene la lista completa
    public List<Barrio> listar() {
        return barrioDAO.findAll();
    }
    
    //Obtiene una lista por nombre
    public List<Barrio> listarPorNombre(String nombre) {
        return barrioDAO.findByNombreContaining(nombre);
    }

    //Agrega un registro
    public Barrio save(Barrio barrio) {
        return barrioDAO.saveAndFlush(barrio);
    }

    //Actualiza un registro
    public Barrio update(Barrio barrio) {
        return barrioDAO.save(barrio);
    }

}
