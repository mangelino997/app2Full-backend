//Paquete al que pertenece el servicio
package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.ICobranzaDAO;
import ar.com.draimo.jitws.dao.ICobranzaItemDAO;
import ar.com.draimo.jitws.dao.IEmpresaDAO;
import ar.com.draimo.jitws.dao.ITipoComprobanteDAO;
import ar.com.draimo.jitws.dao.IVentaComprobanteDAO;
import ar.com.draimo.jitws.model.Cobranza;
import ar.com.draimo.jitws.model.CobranzaItem;
import ar.com.draimo.jitws.model.VentaComprobante;
import java.sql.Timestamp;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio de Cobranza
 *
 * @author blas
 */
@Service
public class CobranzaService {

    //Define el dao
    @Autowired
    ICobranzaDAO elementoDAO;

    //Define el dao de empresa
    @Autowired
    IEmpresaDAO empresaDAO;

    //Define el dao de tipoComprobante
    @Autowired
    ITipoComprobanteDAO tipoComprobanteDAO;

    //Define el dao de cobranzaItem
    @Autowired
    ICobranzaItemDAO cobranzaItemDAO;

    //Define el dao de ventaComprobante
    @Autowired
    IVentaComprobanteDAO ventaComprobanteDAO;

    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        Cobranza elemento = elementoDAO.findTopByOrderByIdDesc();
        return (elemento != null ? elemento.getId() + 1 : 1);
    }

    //Obtiene la lista completa
    public List<Cobranza> listar() {
        return elementoDAO.findAll();
    }

    //Obtiene una lista por empresa
    public List<Cobranza> listarPorEmpresa(int idEmpresa) {
        return elementoDAO.findByEmpresa(empresaDAO.findById(idEmpresa).get());
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public Cobranza agregar(Cobranza elemento) {
        elemento.setFechaRegistracion(new Timestamp(new java.util.Date().getTime()));
        elemento.setTipoComprobante(tipoComprobanteDAO.findById(4).get());
        Cobranza c = elementoDAO.saveAndFlush(elemento);
        if(!elemento.getCobranzaItems().isEmpty()) {
            for (CobranzaItem cobranzaItem : elemento.getCobranzaItems()) {
                if(cobranzaItem.getImporte()!=null) {
                    VentaComprobante vtaCpte = ventaComprobanteDAO.findById(
                            cobranzaItem.getVentaComprobante().getId()).get();
                    vtaCpte.setImporteSaldo(cobranzaItem.getVentaComprobante().getImporteSaldo().subtract(
                            cobranzaItem.getImporte()));
                    ventaComprobanteDAO.save(vtaCpte);
                }
                cobranzaItem.setCobranza(c);
                cobranzaItemDAO.saveAndFlush(cobranzaItem);
            }
        }
        return c;
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(Cobranza elemento) {
        elementoDAO.save(elemento);
    }

    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }

}
