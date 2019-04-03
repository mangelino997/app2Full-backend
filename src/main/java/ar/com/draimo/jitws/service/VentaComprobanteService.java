package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IMonedaDAO;
import ar.com.draimo.jitws.dao.IVentaComprobanteDAO;
import ar.com.draimo.jitws.dao.IVentaComprobanteItemCRDAO;
import ar.com.draimo.jitws.dao.IVentaComprobanteItemFADAO;
import ar.com.draimo.jitws.dao.IViajeRemitoDAO;
import ar.com.draimo.jitws.model.VentaComprobante;
import ar.com.draimo.jitws.model.VentaComprobanteItemFA;
import ar.com.draimo.jitws.model.ViajeRemito;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio VentaComprobante
 *
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
    
    //Define la referancia a MonedaDAO
    @Autowired
    IMonedaDAO monedaDAO;

    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        VentaComprobante elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId() + 1 : 1;
    }

    //Obtiene la lista completa
    public List<VentaComprobante> listar() {
        return elementoDAO.findAll();
    }

    //Obtiene un registro por puntoVenta, letra y numero
    public VentaComprobante obtener(int puntoVenta, String letra, int numero) {
        return elementoDAO.findByPuntoVentaAndLetraAndNumero(puntoVenta, letra, numero);
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public VentaComprobante agregar(VentaComprobante elemento) {
        elemento = formatearStrings(elemento);
        elemento.setMoneda(monedaDAO.findById(1).get());
        elemento.setMonedaCotizacion(new BigDecimal(0.0));
        elemento.setFechaRegistracion(LocalDateTime.now());
        VentaComprobante vc = elementoDAO.saveAndFlush(elemento);
        ViajeRemito vr;
        for (VentaComprobanteItemFA ventaComprobanteItemFA : elemento.getVentaComprobanteItemFAs()) {
            ventaComprobanteItemFA.setVentaComprobante(vc);
            if (ventaComprobanteItemFA.getViajeRemito() != null) {
                vr = viajeRemitoDAO.findById(ventaComprobanteItemFA.getViajeRemito().getId()).get();
                vr.setEstaFacturado(true);
                viajeRemitoDAO.save(vr);
            }
            ventaComprobanteItemFADAO.saveAndFlush(ventaComprobanteItemFA);
        }
        elemento.getVentaComprobanteItemCRs().get(0).setVentaComprobante(vc);
        ventaComprobanteItemCRDAO.saveAndFlush(elemento.getVentaComprobanteItemCRs().get(0));
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
