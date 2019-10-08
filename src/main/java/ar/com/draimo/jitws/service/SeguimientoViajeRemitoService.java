//Paquete al que pertenece el servicio
package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IViajeRemitoDAO;
import ar.com.draimo.jitws.dao.ISeguimientoEstadoDAO;
import ar.com.draimo.jitws.dao.ISeguimientoSituacionDAO;
import ar.com.draimo.jitws.model.SeguimientoViajeRemito;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ar.com.draimo.jitws.dao.ISeguimientoViajeRemitoDAO;
import ar.com.draimo.jitws.model.SeguimientoEstado;
import ar.com.draimo.jitws.model.SeguimientoSituacion;
import java.time.LocalDateTime;

/**
 * Servicio de SeguimientoViajeRemito
 *
 * @author blas
 */
@Service
public class SeguimientoViajeRemitoService {

    //Define la referencia al dao
    @Autowired
    ISeguimientoViajeRemitoDAO elementoDAO;

    //define la referencia al dao de seguimientoEstado
    @Autowired
    ISeguimientoEstadoDAO seguimientoEstadoDAO;

    //define la referencia al dao de seguimientoSituacion
    @Autowired
    ISeguimientoSituacionDAO seguimientoSituacionDAO;

    //Define la referencia al dao de viajeRemito
    @Autowired
    IViajeRemitoDAO viajeRemitoDAO;

    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        SeguimientoViajeRemito elemento = elementoDAO.findTopByOrderByIdDesc();
        return (elemento != null ? elemento.getId() + 1 : 1);
    }

    //Obtiene la lista completa
    public List<SeguimientoViajeRemito> listar() {
        return elementoDAO.findByOrderByFechaDesc();
    }

    //Obtiene una lista por viajeRemito
    public List<SeguimientoViajeRemito> listarPorViajeRemito(int idViajeRemito) {
        return elementoDAO.findByViajeRemito(viajeRemitoDAO.findById(idViajeRemito).get());
    }

    //Obtiene una lista por SeguimientoEstado
    public List<SeguimientoViajeRemito> listarPorSeguimientoEstado(int idSeguimientoEstado) {
        return elementoDAO.findBySeguimientoEstado(seguimientoEstadoDAO.findById(idSeguimientoEstado).get());
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public SeguimientoViajeRemito agregar(SeguimientoViajeRemito elemento) {
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
    public void actualizar(SeguimientoViajeRemito elemento) {
        elementoDAO.save(elemento);
    }

    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }

}