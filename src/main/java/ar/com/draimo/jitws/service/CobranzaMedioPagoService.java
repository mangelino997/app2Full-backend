//Paquete al que pertenece el servicio
package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IChequeCarteraDAO;
import ar.com.draimo.jitws.dao.ICobranzaAnticipoDAO;
import ar.com.draimo.jitws.dao.ICobranzaMedioPagoDAO;
import ar.com.draimo.jitws.dao.ICobranzaDAO;
import ar.com.draimo.jitws.dao.IDocumentoCarteraDAO;
import ar.com.draimo.jitws.dao.IEfectivoDAO;
import ar.com.draimo.jitws.dao.ILibroBancoDAO;
import ar.com.draimo.jitws.dao.IMonedaCarteraDAO;
import ar.com.draimo.jitws.model.ChequeCartera;
import ar.com.draimo.jitws.model.Cobranza;
import ar.com.draimo.jitws.model.CobranzaAnticipo;
import ar.com.draimo.jitws.model.CobranzaMedioPago;
import ar.com.draimo.jitws.model.DocumentoCartera;
import ar.com.draimo.jitws.model.Efectivo;
import ar.com.draimo.jitws.model.LibroBanco;
import ar.com.draimo.jitws.model.MonedaCartera;
import java.math.RoundingMode;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio de CobranzaMedioPago
 *
 * @author blas
 */
@Service
public class CobranzaMedioPagoService {

    //Define la referencia al DAO
    @Autowired
    ICobranzaMedioPagoDAO elementoDAO;

    //Define la referencia al DAO de cobranza
    @Autowired
    ICobranzaDAO cobranzaDAO;

    //Define la referencia al DAO de cheque
    @Autowired
    IChequeCarteraDAO chequeCarteraDAO;

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
    ICobranzaAnticipoDAO cobranzaAnticipoDAO;

    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        CobranzaMedioPago elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId() + 1 : 1;
    }

    //Obtiene la lista completa
    public List<CobranzaMedioPago> listar() {
        return elementoDAO.findAll();
    }

    //Obtiene una lista por Cobranza 
    public List<CobranzaMedioPago> listarPorCobranza(int idCobranza) {
        return elementoDAO.findByCobranza(cobranzaDAO.findById(idCobranza).get());
    }

    //Agrega un lote de registros
    @Transactional(rollbackFor = Exception.class)
    public CobranzaMedioPago agregarLote(List<CobranzaMedioPago> elementos) {
        Cobranza cobranza = cobranzaDAO.findById(elementos.get(0).getId()).get();
        CobranzaAnticipo anticipo = null;
        for (CobranzaMedioPago elemento : elementos) {
            elemento.setCobranza(cobranza);
            if (elemento.getChequeCartera() != null) {
                chequeCarteraDAO.saveAndFlush(elemento.getChequeCartera());
            }
            if (elemento.getMonedaCartera() != null) {
                monedaCarteraDAO.saveAndFlush(elemento.getMonedaCartera());
            }
            if (elemento.getDocumentoCartera() != null) {
                documentoCarteraDAO.saveAndFlush(elemento.getDocumentoCartera());
            }
            if (elemento.getLibroBanco() != null) {
                libroBancoDAO.saveAndFlush(elemento.getLibroBanco());
            }
            if (elemento.getEfectivo() != null) {
                efectivoDAO.saveAndFlush(elemento.getEfectivo());
            }
            if (elemento.getCobranzaAnticipo() != null) {
                anticipo = cobranzaAnticipoDAO.findById(elemento.getCobranzaAnticipo().getId()).get();
                anticipo.setSaldo(anticipo.getSaldo().subtract(
                        elemento.getImporteCobranzaAnticipo()).setScale(2, RoundingMode.HALF_UP));
                cobranzaAnticipoDAO.save(anticipo);

            }
        }
        return elementoDAO.saveAndFlush(elementos.get(0));
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public CobranzaMedioPago agregar(CobranzaMedioPago elemento) {
        return elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(CobranzaMedioPago elemento) {
        elementoDAO.save(elemento);
    }

    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }

}
