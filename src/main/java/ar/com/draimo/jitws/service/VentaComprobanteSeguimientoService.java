package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IVentaComprobanteDAO;
import ar.com.draimo.jitws.dao.IVentaComprobanteSeguimientoDAO;
import ar.com.draimo.jitws.dao.ISeguimientoEstadoDAO;
import ar.com.draimo.jitws.model.VentaComprobante;
import ar.com.draimo.jitws.model.VentaComprobanteSeguimiento;
import ar.com.draimo.jitws.model.SeguimientoEstado;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio de VentaComprobanteSeguimiento
 * @author blas
 */

@Service
public class VentaComprobanteSeguimientoService {
    
    //Define el dao
    @Autowired
    IVentaComprobanteSeguimientoDAO elementoDAO;
    
    //Define el dao de seguimiento estado
    @Autowired
    ISeguimientoEstadoDAO seguimientoEstadoDAO;
    
    //Define el dao de ordenRecoleccion
    @Autowired
    IVentaComprobanteDAO ventaComprobanteDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        VentaComprobanteSeguimiento elemento = elementoDAO.findTopByOrderByIdDesc();
        return (elemento!=null?elemento.getId()+1:1);
    }
    
    //Obtiene la lista completa
    public List<VentaComprobanteSeguimiento> listar() {
        return elementoDAO.findByOrderByFechaDesc();
    }
    
    //Obtiene una lista por VentaComprobante
    public List<VentaComprobanteSeguimiento> listarPorVentaComprobante(int idVentaComprobante) {
        VentaComprobante v = ventaComprobanteDAO.findById(idVentaComprobante).get();
        return elementoDAO.findByVentaComprobante(v);
    }
    
    //Obtiene una lista por SeguimientoEstado
    public List<VentaComprobanteSeguimiento> listarPorSeguimientoEstado(int idSeguimientoEstado) {
        SeguimientoEstado s = seguimientoEstadoDAO.findById(idSeguimientoEstado).get();
        return elementoDAO.findBySeguimientoEstado(s);
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public VentaComprobanteSeguimiento agregar(VentaComprobanteSeguimiento elemento) {
        return elementoDAO.save(elemento);
    }
    
    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(VentaComprobanteSeguimiento elemento) {
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }
    
}
