package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.constant.Funcion;
import ar.com.draimo.jitws.dao.ISeguimientoSituacionDAO;
import ar.com.draimo.jitws.model.SeguimientoSituacion;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio SeguimientoSituacion
 *
 * @author blas
 */
@Service
public class SeguimientoSituacionService {

    //Define el dao
    @Autowired
    ISeguimientoSituacionDAO elementoDAO;

    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        SeguimientoSituacion elemento = elementoDAO.findTopByOrderByIdDesc();
        return (elemento != null ? elemento.getId() + 1 : 1);
    }

    //Obtiene la lista completa
    public List<SeguimientoSituacion> listar() {
        return elementoDAO.findAll();
    }

    //Obtiene una lista por nombre
    public List<SeguimientoSituacion> listarPorNombre(String nombre) {
        if (nombre.equals("***")) {
            return elementoDAO.findAll();
        } else {
            return elementoDAO.findByNombreContaining(nombre);
        }
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public SeguimientoSituacion agregar(SeguimientoSituacion elemento) {
        formatearString(elemento);
        return elementoDAO.save(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(SeguimientoSituacion elemento) {
        formatearString(elemento);
        elementoDAO.save(elemento);
    }

    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }

    //Formatea los string 
    private SeguimientoSituacion formatearString(SeguimientoSituacion elemento) {
        elemento.setNombre(elemento.getNombre().trim());
        return elemento;
    }

}
