package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.ICompraComprobanteDAO;
import ar.com.draimo.jitws.dao.ICompraComprobanteItemDAO;
import ar.com.draimo.jitws.dao.ICompraComprobantePercepcionDAO;
import ar.com.draimo.jitws.dao.ICompraComprobanteVencimientoDAO;
import ar.com.draimo.jitws.dao.ICompraCptePercepcionJurisdDAO;
import ar.com.draimo.jitws.dao.IMonedaDAO;
import ar.com.draimo.jitws.model.CompraComprobante;
import ar.com.draimo.jitws.model.CompraComprobanteItem;
import ar.com.draimo.jitws.model.CompraComprobantePercepcion;
import ar.com.draimo.jitws.model.CompraComprobanteVencimiento;
import ar.com.draimo.jitws.model.CompraCptePercepcionJurisd;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author blas
 */

@Service
public class CompraComprobanteService {

    @Autowired
    ICompraComprobanteDAO elementoDAO;

    @Autowired
    ICompraComprobanteVencimientoDAO vencimientoDAO;

    @Autowired
    ICompraComprobantePercepcionDAO percepcionDAO;

    @Autowired
    ICompraComprobanteItemDAO itemDAO;

    @Autowired
    ICompraCptePercepcionJurisdDAO jurisdiccionDAO;
    
    @Autowired
    IMonedaDAO monedaDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        CompraComprobante elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId()+1 : 1;
    }
    
    //Obtiene la lista completa
    public List<CompraComprobante> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene la lista por filtros
    public List<CompraComprobante> listarPorFiltros(int idEmpresa, int idProveedor, 
            int fechaTipo, Date fechaDesde, Date fechaHasta, int idTipoComprobante) {
        return elementoDAO.listarPorFiltros(idEmpresa, idProveedor, fechaDesde, fechaHasta,
                idTipoComprobante, fechaTipo);
    }
    
    public boolean verificarUnicidadComprobante(int idProveedor, String codigoAfip, int puntoVenta, int numero) {
        if(!elementoDAO.verificarUnicidad(idProveedor, codigoAfip, puntoVenta, numero).isEmpty()) {
            return true;
        }else {
            return false;
        }
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public CompraComprobante agregar(CompraComprobante elemento) {
        Timestamp fechaRegistracion= new Timestamp(new java.util.Date().getTime());
        elemento.setFechaRegistracion(fechaRegistracion);
        elemento.setAfipCondicionIva(elemento.getProveedor().getAfipCondicionIva());
        elemento.setMoneda(monedaDAO.findById(1).get());
        elemento.setImporteSaldo(BigDecimal.ZERO);
        elemento.setMonedaCotizacion(BigDecimal.ZERO);
        elemento.setTipoDocumento(elemento.getProveedor().getTipoDocumento());
        elemento.setNumeroDocumento(elemento.getProveedor().getNumeroDocumento());
        elemento.setCondicionCompra(elemento.getProveedor().getCondicionCompra());
        List<CompraComprobanteItem> items = elemento.getCompraComprobanteItems();
        List<CompraComprobantePercepcion> percepciones = elemento.getCompraComprobantePercepciones();
        List<CompraComprobanteVencimiento> vencimientos = elemento.getCompraComprobanteVencimientos();
        elemento.setCompraComprobanteItems(null);
        elemento.setCompraComprobantePercepciones(null);
        elemento.setCompraComprobanteVencimientos(null);
        elemento = elementoDAO.saveAndFlush(elemento);
        if(!items.isEmpty()){
            for (CompraComprobanteItem item : items) {
                item.setObservaciones("");
                item.setCompraComprobante(elemento);
                itemDAO.save(item);
            }
        }else {
            throw new DataIntegrityViolationException("La lista de ITEMS no puede estar vacia");
        }
        if(!percepciones.isEmpty()){
            for (CompraComprobantePercepcion percepcion : percepciones) {
                percepcion.setCompraComprobante(elemento);
                percepcion.setLetra(elemento.getLetra());
                percepcion.setPuntoVenta(elemento.getPuntoVenta());
                percepcion.setNumero(elemento.getNumero());
                List<CompraCptePercepcionJurisd> jurisdicciones =percepcion.getCompraCptePercepcionJurisdicciones();
                percepcion.setCompraCptePercepcionJurisdicciones(null);
                percepcionDAO.save(percepcion);
                if(!jurisdicciones.isEmpty()){
                    for (CompraCptePercepcionJurisd cteJurisdicciones : jurisdicciones) {
                        cteJurisdicciones.setCompraComprobantePercepcion(percepcion);
                        jurisdiccionDAO.save(cteJurisdicciones);
                    }
                }
            }
        }
        if(!vencimientos.isEmpty()){
            for (CompraComprobanteVencimiento vencimiento : vencimientos) {
                vencimiento.setCompraComprobante(elemento);
                vencimientoDAO.save(vencimiento);
            }
        }
        return elemento;
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(CompraComprobante elemento) {
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }

}
