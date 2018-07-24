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
    
    public List<Barrio> listar() {
        return barrioDAO.findAll();
    }

    public Barrio save(Barrio barrio) {
        return barrioDAO.saveAndFlush(barrio);
    }

    public Barrio update(Barrio barrio) {
        return barrioDAO.save(barrio);
    }

    public List<Barrio> listarPorNombre(String nombre) {
        return barrioDAO.listarPorNombre(nombre);
    }

}
