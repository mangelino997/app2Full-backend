package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.ISeguimientoEstadoDAO;
import ar.com.draimo.jitws.dao.ITipoComprobanteDAO;
import ar.com.draimo.jitws.model.SeguimientoEstadoTipoCpte;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ar.com.draimo.jitws.dao.ISeguimientoEstadoTipoCpteDAO;

/**
 * Servicio AfipActividad
 *
 * @author blas
 */
@Service
public class SeguimientoEstadoTipoCpteService {

    //Define el dao
    @Autowired
    ISeguimientoEstadoTipoCpteDAO elementoDAO;

    //Define el dao de tipoComprobante
    @Autowired
    ITipoComprobanteDAO tipoComprobanteDAO;

    //Define el dao de SeguimientoEstado
    @Autowired
    ISeguimientoEstadoDAO seguimientoEstadoDAO;

    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        SeguimientoEstadoTipoCpte elemento = elementoDAO.findTopByOrderByIdDesc();
        return (elemento != null ? elemento.getId() + 1 : 1);
    }

    //Obtiene la lista completa
    public List<SeguimientoEstadoTipoCpte> listar() {
        return elementoDAO.findAll();
    }

    //Obtiene una lista por TipoComprobante
    public List<SeguimientoEstadoTipoCpte> listarPorTipoComprobante(int idTipoComprobante) {
        return elementoDAO.findByTipoComprobante(tipoComprobanteDAO.findById(idTipoComprobante).get());
    }

    //Obtiene una lista por SeguimientoEstado
    public List<SeguimientoEstadoTipoCpte> listarPorSeguimientoEstado(int idSeguimientoEstado) {
        return elementoDAO.findBySeguimientoEstado(seguimientoEstadoDAO.findById(idSeguimientoEstado).get());
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public SeguimientoEstadoTipoCpte agregar(SeguimientoEstadoTipoCpte elemento) {
        return elementoDAO.save(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(SeguimientoEstadoTipoCpte elemento) {
        elementoDAO.save(elemento);
    }

    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }

}
