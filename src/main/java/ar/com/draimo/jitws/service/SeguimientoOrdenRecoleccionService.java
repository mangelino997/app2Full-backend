//Paquete al que pertenece el servicio
package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IOrdenRecoleccionDAO;
import ar.com.draimo.jitws.dao.ISeguimientoEstadoDAO;
import ar.com.draimo.jitws.model.SeguimientoOrdenRecoleccion;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ar.com.draimo.jitws.dao.ISeguimientoOrdenRecoleccionDAO;
import ar.com.draimo.jitws.dao.ISeguimientoSituacionDAO;
import ar.com.draimo.jitws.model.SeguimientoEstado;
import ar.com.draimo.jitws.model.SeguimientoSituacion;
import java.time.LocalDateTime;

/**
 * Servicio de SeguimientoOrdenRecoleccion
 *
 * @author blas
 */
@Service
public class SeguimientoOrdenRecoleccionService {

    //Define la referencia al dao
    @Autowired
    ISeguimientoOrdenRecoleccionDAO elementoDAO;

    //define la referencia al dao de seguimientoEstado
    @Autowired
    ISeguimientoEstadoDAO seguimientoEstadoDAO;

    //define la referencia al dao de seguimientoSituacion
    @Autowired
    ISeguimientoSituacionDAO seguimientoSituacionDAO;

    //Define la referencia al dao de ordenRecoleccion
    @Autowired
    IOrdenRecoleccionDAO ordenRecoleccionDAO;

    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        SeguimientoOrdenRecoleccion elemento = elementoDAO.findTopByOrderByIdDesc();
        return (elemento != null ? elemento.getId() + 1 : 1);
    }

    //Obtiene la lista completa
    public List<SeguimientoOrdenRecoleccion> listar() {
        return elementoDAO.findByOrderByFechaDesc();
    }

    //Obtiene una lista por OrdenRecoleccion
    public List<SeguimientoOrdenRecoleccion> listarPorOrdenRecoleccion(int idOrdenRecoleccion) {
        return elementoDAO.findByOrdenRecoleccion(ordenRecoleccionDAO.findById(idOrdenRecoleccion).get());
    }

    //Obtiene una lista por SeguimientoEstado
    public List<SeguimientoOrdenRecoleccion> listarPorSeguimientoEstado(int idSeguimientoEstado) {
        return elementoDAO.findBySeguimientoEstado(seguimientoEstadoDAO.findById(idSeguimientoEstado).get());
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public SeguimientoOrdenRecoleccion agregar(SeguimientoOrdenRecoleccion elemento) {
        SeguimientoEstado se = seguimientoEstadoDAO.findById(4).get();
        SeguimientoSituacion ss = seguimientoSituacionDAO.findById(1).get();
        LocalDateTime fecha = LocalDateTime.now();
        elemento.setSeguimientoEstado(se);
        elemento.setSeguimientoSituacion(ss);
        elemento.setFecha(fecha);
        return elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(SeguimientoOrdenRecoleccion elemento) {
        elementoDAO.save(elemento);
    }

    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }

}
