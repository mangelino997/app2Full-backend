package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IVentaComprobanteDAO;
import ar.com.draimo.jitws.dao.IVentaComprobanteItemCRDAO;
import ar.com.draimo.jitws.dao.IVentaComprobanteItemFADAO;
import ar.com.draimo.jitws.dao.IViajeRemitoDAO;
import ar.com.draimo.jitws.model.VentaComprobante;
import ar.com.draimo.jitws.model.VentaComprobanteItemCR;
import ar.com.draimo.jitws.model.VentaComprobanteItemFA;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio VentaComprobante
 * @author blas
 */

@Service
public class VentaComprobanteService {
    
    //Define la referencia al dao
    @Autowired
    IVentaComprobanteDAO elementoDAO;
    
    //Define la referencia VentaComprobanteItemCRDAO
    @Autowired
    IVentaComprobanteItemCRDAO ventaComprobanteItemCRDAO;
    
    //Define la referencia a VentaComprobanteitemFADAO
    @Autowired
    IVentaComprobanteItemFADAO ventaComprobanteItemFADAO;
    
    //Define la referancia a RemitoDAO
    @Autowired
    IViajeRemitoDAO viajeRemitoDAO; 
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        VentaComprobante elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId()+1 : 1;
    }
    
    //Obtiene la lista completa
    public List<VentaComprobante> listar() {
        return elementoDAO.findAll();
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public VentaComprobante agregar(VentaComprobante elemento) {
        VentaComprobanteItemFA comprobanteItemFA;
        VentaComprobanteItemCR comprobanteItemCR;
        elementoDAO.save(elemento);
        for (VentaComprobanteItemFA ventaComprobanteItemFA : elemento.getVentaComprobanteItemFAs()) {
            comprobanteItemFA = ventaComprobanteItemFA;
            comprobanteItemFA.setVentaComprobante(elemento);
            if (ventaComprobanteItemFA.getViajeRemito()!=null) {
                ventaComprobanteItemFA.getViajeRemito().setEstaFacturado(true);
            }
            ventaComprobanteItemFADAO.saveAndFlush(comprobanteItemFA);
        }
        comprobanteItemCR = elemento.getVentaComprobanteItemCR();
        comprobanteItemCR.setVentaComprobante(elemento);
        ventaComprobanteItemCRDAO.saveAndFlush(comprobanteItemCR);
        elemento = formatearStrings(elemento);
        return elementoDAO.saveAndFlush(elemento);
    }
    
    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(VentaComprobante elemento) {
        elemento = formatearStrings(elemento);
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(VentaComprobante elemento) {
        elementoDAO.delete(elemento);
    }
    
    //Formatea los strings
    private VentaComprobante formatearStrings(VentaComprobante elemento) {
        elemento.setLetra(elemento.getLetra().trim());
        elemento.setCodigoAfip(elemento.getCodigoAfip().trim());
        return elemento;
    }
    
}
