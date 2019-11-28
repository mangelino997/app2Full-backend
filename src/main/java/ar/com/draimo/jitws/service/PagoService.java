//Paquete al que pertenece el servicio
package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IPagoAnticipoDAO;
import ar.com.draimo.jitws.dao.IPagoItemDAO;
import ar.com.draimo.jitws.dao.IPagoRetencionDAO;
import ar.com.draimo.jitws.dao.IPagoDAO;
import ar.com.draimo.jitws.dao.IEmpresaDAO;
import ar.com.draimo.jitws.dao.ITipoComprobanteDAO;
import ar.com.draimo.jitws.dao.ICompraComprobanteDAO;
import ar.com.draimo.jitws.model.CompraComprobante;
import ar.com.draimo.jitws.model.Pago;
import ar.com.draimo.jitws.model.PagoAnticipo;
import ar.com.draimo.jitws.model.PagoItem;
import ar.com.draimo.jitws.model.PagoRetencion;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio de Pago
 *
 * @author blas
 */
@Service
public class PagoService {

    //Define el dao
    @Autowired
    IPagoDAO elementoDAO;

    //Define el dao de empresa
    @Autowired
    IEmpresaDAO empresaDAO;

    //Define el dao de tipoComprobante
    @Autowired
    ITipoComprobanteDAO tipoComprobanteDAO;

    //Define el dao de pagoItem
    @Autowired
    IPagoItemDAO pagoItemDAO;

    //Define el dao de pagoAnticipo
    @Autowired
    IPagoAnticipoDAO pagoAnticipoDAO;

    //Define el dao de pagoRetencion
    @Autowired
    IPagoRetencionDAO pagoRetencionDAO;

    //Define el dao de CompraComprobante
    @Autowired
    ICompraComprobanteDAO compraComprobanteDAO;

    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        Pago elemento = elementoDAO.findTopByOrderByIdDesc();
        return (elemento != null ? elemento.getId() + 1 : 1);
    }

    //Obtiene la lista completa
    public List<Pago> listar() {
        return elementoDAO.findAll();
    }

    //Obtiene una lista por empresa
    public List<Pago> listarPorEmpresa(int idEmpresa) {
        return elementoDAO.findByEmpresa(empresaDAO.findById(idEmpresa).get());
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public Pago agregar(Pago elemento) {
        elemento.setFechaRegistracion(new Timestamp(new java.util.Date().getTime()));
        elemento.setTipoComprobante(tipoComprobanteDAO.findById(21).get());
        Pago p = elementoDAO.saveAndFlush(elemento);
        BigDecimal montoTotal = new BigDecimal(0);
        PagoAnticipo anticipo = new PagoAnticipo();
        if(!elemento.getPagoItems().isEmpty()) {
            for (PagoItem pagoItem : elemento.getPagoItems()) {
                CompraComprobante compraCpte = compraComprobanteDAO.findById(
                        pagoItem.getCompraComprobante().getId()).get();
                if(pagoItem.getImporte()!=null) {
                    compraCpte.setImporteSaldo(pagoItem.getCompraComprobante().getImporteSaldo().subtract(
                            pagoItem.getImporte()));
                } else {
                    pagoItem.setImporte(compraCpte.getImporteSaldo());
                    compraCpte.setImporteSaldo(new BigDecimal(0));
                }
                montoTotal.add(pagoItem.getImporte());
                compraComprobanteDAO.save(compraCpte);
                pagoItem.setPago(p);
                pagoItemDAO.saveAndFlush(pagoItem);
            }
            for (PagoRetencion pagoRetencion : elemento.getPagoRetenciones()) {
                pagoRetencion.setPago(p);
                pagoRetencionDAO.saveAndFlush(pagoRetencion);
            }
            anticipo.setImporte(montoTotal.compareTo(p.getImporte())==(-1) ? 
                    p.getImporte().subtract(montoTotal) : null);
        } else {
            anticipo.setImporte(p.getImporte());
        }
        anticipo.setPago(p);
        anticipo.setSaldo(anticipo.getImporte());
        anticipo = anticipo.getImporte()== null ? null : pagoAnticipoDAO.saveAndFlush(anticipo);
        return p;
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(Pago elemento) {
        elementoDAO.save(elemento);
    }

    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }

}
