//Paquete al que pertenece el servicio
package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IOrdenRecoleccionDAO;
import ar.com.draimo.jitws.dao.ISeguimientoEstadoDAO;
import ar.com.draimo.jitws.model.OrdenRecoleccion;
import ar.com.draimo.jitws.model.SeguimientoOrdenRecoleccion;
import ar.com.draimo.jitws.model.SeguimientoEstado;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ar.com.draimo.jitws.dao.ISeguimientoOrdenRecoleccionDAO;

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

    //Define la referencia al dao de seguimiento estado
    @Autowired
    ISeguimientoEstadoDAO seguimientoEstadoDAO;

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
