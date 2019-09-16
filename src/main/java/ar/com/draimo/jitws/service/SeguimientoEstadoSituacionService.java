package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.ISeguimientoEstadoDAO;
import ar.com.draimo.jitws.dao.ISeguimientoEstadoSituacionDAO;
import ar.com.draimo.jitws.dao.ISeguimientoSituacionDAO;
import ar.com.draimo.jitws.model.SeguimientoEstadoSituacion;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio SeguimientoEstadoSituacion
 *
 * @author blas
 */
@Service
public class SeguimientoEstadoSituacionService {

    //Define el dao
    @Autowired
    ISeguimientoEstadoSituacionDAO elementoDAO;

    //Define el dao de SeguimientoEstado
    @Autowired
    ISeguimientoEstadoDAO seguimientoEstadoDAO;

    //Define el dao de SeguimientoSituacion
    @Autowired
    ISeguimientoSituacionDAO seguimientoSituacionDAO;

    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        SeguimientoEstadoSituacion elemento = elementoDAO.findTopByOrderByIdDesc();
        return (elemento != null ? elemento.getId() + 1 : 1);
    }

    //Obtiene la lista completa
    public List<SeguimientoEstadoSituacion> listar() {
        return elementoDAO.findAll();
    }

    //Obtiene una lista por estado
    public List<SeguimientoEstadoSituacion> listarPorSeguimientoEstado(int idEstado) {
        return elementoDAO.findBySeguimientoEstado(seguimientoEstadoDAO.findById(idEstado).get());
    }

    //Obtiene una lista por situacion
    public List<SeguimientoEstadoSituacion> listarPorSeguimientoSituacion(int idSituacion) {
        return elementoDAO.findBySeguimientoSituacion(seguimientoSituacionDAO.findById(idSituacion).get());
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public SeguimientoEstadoSituacion agregar(SeguimientoEstadoSituacion elemento) {
        return elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(SeguimientoEstadoSituacion elemento) {
        elementoDAO.save(elemento);
    }

    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }

}
