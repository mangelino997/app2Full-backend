//Paquete al que pertenece el servicio
package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IVentaComprobanteDAO;
import ar.com.draimo.jitws.dao.ISeguimientoEstadoDAO;
import ar.com.draimo.jitws.model.VentaComprobante;
import ar.com.draimo.jitws.model.SeguimientoVentaComprobante;
import ar.com.draimo.jitws.model.SeguimientoEstado;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ar.com.draimo.jitws.dao.ISeguimientoVentaComprobanteDAO;

/**
 * Servicio de SeguimientoVentaComprobante
 *
 * @author blas
 */
@Service
public class SeguimientoVentaComprobanteService {

    //Define la referencia al dao
    @Autowired
    ISeguimientoVentaComprobanteDAO elementoDAO;

    //Define la referencia al dao de seguimiento estado
    @Autowired
    ISeguimientoEstadoDAO seguimientoEstadoDAO;

    //Define la referencia al dao de ventaComprobante
    @Autowired
    IVentaComprobanteDAO ventaComprobanteDAO;

    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        SeguimientoVentaComprobante elemento = elementoDAO.findTopByOrderByIdDesc();
        return (elemento != null ? elemento.getId() + 1 : 1);
    }

    //Obtiene la lista completa
    public List<SeguimientoVentaComprobante> listar() {
        return elementoDAO.findByOrderByFechaDesc();
    }

    //Obtiene una lista por VentaComprobante
    public List<SeguimientoVentaComprobante> listarPorVentaComprobante(int idVentaComprobante) {
        return elementoDAO.findByVentaComprobante(ventaComprobanteDAO.findById(idVentaComprobante).get());
    }

    //Obtiene una lista por SeguimientoEstado
    public List<SeguimientoVentaComprobante> listarPorSeguimientoEstado(int idSeguimientoEstado) {
        return elementoDAO.findBySeguimientoEstado(seguimientoEstadoDAO.findById(idSeguimientoEstado).get());
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public SeguimientoVentaComprobante agregar(SeguimientoVentaComprobante elemento) {
        return elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(SeguimientoVentaComprobante elemento) {
        elementoDAO.save(elemento);
    }

    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }

}