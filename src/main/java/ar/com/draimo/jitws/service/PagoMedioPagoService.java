//Paquete al que pertenece el servicio
package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IChequeCarteraDAO;
import ar.com.draimo.jitws.dao.IChequeraItemDAO;
import ar.com.draimo.jitws.dao.IDocumentoCarteraDAO;
import ar.com.draimo.jitws.dao.IEfectivoDAO;
import ar.com.draimo.jitws.dao.ILibroBancoDAO;
import ar.com.draimo.jitws.dao.IMonedaCarteraDAO;
import ar.com.draimo.jitws.dao.IPagoAnticipoDAO;
import ar.com.draimo.jitws.dao.IPagoMedioPagoDAO;
import ar.com.draimo.jitws.dao.IPagoDAO;
import ar.com.draimo.jitws.dao.ITipoDocumentoDAO;
import ar.com.draimo.jitws.model.LibroBanco;
import ar.com.draimo.jitws.model.Pago;
import ar.com.draimo.jitws.model.PagoAnticipo;
import ar.com.draimo.jitws.model.PagoMedioPago;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio de PagoMedioPago
 * @author blas
 */

@Service
public class PagoMedioPagoService {

    //Define la referencia al DAO
    @Autowired
    IPagoMedioPagoDAO elementoDAO;
    
    //Define la referencia al DAO de empresa
    @Autowired
    IPagoDAO pagoDAO;

    //Define la referencia al DAO de cheque
    @Autowired
    IChequeCarteraDAO chequeCarteraDAO;

    //Define la referencia al DAO de chequeItem
    @Autowired
    IChequeraItemDAO chequeraItemDAO;

    //Define la referencia al DAO de moneda
    @Autowired
    IMonedaCarteraDAO monedaCarteraDAO;

    //Define la referencia al DAO de efectivo
    @Autowired
    IEfectivoDAO efectivoDAO;

    //Define la referencia al DAO de libroBanco
    @Autowired
    ILibroBancoDAO libroBancoDAO;

    //Define la referencia al DAO de documento
    @Autowired
    IDocumentoCarteraDAO documentoCarteraDAO;

    //Define la referencia al DAO de anticipo
    @Autowired
    IPagoAnticipoDAO pagoAnticipoDAO;

    //Define la referencia al DAO de tipoDocumento
    @Autowired
    ITipoDocumentoDAO tipoDocumentoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        PagoMedioPago elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId()+1 : 1;
    }
    
    //Obtiene la lista completa
    public List<PagoMedioPago> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista por pago 
    public List<PagoMedioPago> listarPorPago(int idPago) {
            return elementoDAO.findByPago(pagoDAO.findById(idPago).get());
    }
    
    //Agrega un lote de registros
    @Transactional(rollbackFor = Exception.class)
    public PagoMedioPago agregarLote(List<PagoMedioPago> elementos) {
        Pago pago = pagoDAO.findById(elementos.get(0).getId()).get();
        PagoAnticipo anticipo = null;
        BigDecimal montoTotal= new BigDecimal(0);
        for (PagoMedioPago elemento : elementos) {
            elemento.setPago(pago);
            if (elemento.getChequeCartera() != null) {
                elemento.getChequeCartera().setTipoDocumentoEmisor(tipoDocumentoDAO.findById(1).get());
                chequeCarteraDAO.saveAndFlush(elemento.getChequeCartera());
                montoTotal.add(elemento.getChequeCartera().getImporte());
            }
            if (elemento.getChequeraItem()!= null) {
                LibroBanco libro = new LibroBanco();
                libro.setEsDebito(true);
                libro.setFecha(elemento.getChequeraItem().getFechaPago());
                libro.setImporte(elemento.getChequeraItem().getImporte());
                elemento.getChequeraItem().setLibroBanco(libroBancoDAO.saveAndFlush(libro));
                chequeraItemDAO.saveAndFlush(elemento.getChequeraItem());
                montoTotal.add(elemento.getChequeraItem().getImporte());
            }
            if (elemento.getMonedaCartera() != null) {
                monedaCarteraDAO.saveAndFlush(elemento.getMonedaCartera());
                montoTotal.add(elemento.getMonedaCartera().getImporte());
            }
            if (elemento.getDocumentoCartera() != null) {
                documentoCarteraDAO.saveAndFlush(elemento.getDocumentoCartera());
                montoTotal.add(elemento.getDocumentoCartera().getImporte());
            }
            if (elemento.getLibroBanco() != null) {
                libroBancoDAO.saveAndFlush(elemento.getLibroBanco());
                montoTotal.add(elemento.getLibroBanco().getImporte());
            }
            if (elemento.getEfectivo() != null) {
                efectivoDAO.saveAndFlush(elemento.getEfectivo());
                montoTotal.add(elemento.getEfectivo().getImporte());
            }
            if (elemento.getPagoAnticipo() != null) {
                anticipo = pagoAnticipoDAO.findById(elemento.getPagoAnticipo().getId()).get();
                anticipo.setSaldo(anticipo.getSaldo().subtract(
                        elemento.getImportePagoAnticipo()).setScale(2, RoundingMode.HALF_UP));
                pagoAnticipoDAO.save(anticipo);
                montoTotal.add(elemento.getImportePagoAnticipo());
            }
        }
        return pago.getImporte().compareTo(montoTotal)==(-1) ?
        elementoDAO.saveAndFlush(elementos.get(0)) : null;
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public PagoMedioPago agregar(PagoMedioPago elemento) throws Exception {
        return elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(PagoMedioPago elemento) throws Exception {
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }
    
}