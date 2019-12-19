//Paquete al que pertenece el servicio
package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IAfipAlicuotaIvaDAO;
import ar.com.draimo.jitws.dao.IAfipComprobanteDAO;
import ar.com.draimo.jitws.dao.ICompraComprobanteDAO;
import ar.com.draimo.jitws.dao.ICompraComprobanteItemDAO;
import ar.com.draimo.jitws.dao.ICompraComprobantePercepcionDAO;
import ar.com.draimo.jitws.dao.ICompraComprobanteVencimientoDAO;
import ar.com.draimo.jitws.dao.ICompraCptePercepcionJurisdDAO;
import ar.com.draimo.jitws.dao.ICondicionCompraDAO;
import ar.com.draimo.jitws.dao.IDepositoInsumoProductoDAO;
import ar.com.draimo.jitws.dao.IMonedaDAO;
import ar.com.draimo.jitws.dao.IProveedorDAO;
import ar.com.draimo.jitws.dao.ITipoComprobanteDAO;
import ar.com.draimo.jitws.dto.InitCompraComprobanteDTO;
import ar.com.draimo.jitws.exception.MensajeRespuesta;
import ar.com.draimo.jitws.model.CompraComprobante;
import ar.com.draimo.jitws.model.CompraComprobanteItem;
import ar.com.draimo.jitws.model.CompraComprobantePercepcion;
import ar.com.draimo.jitws.model.CompraComprobanteVencimiento;
import ar.com.draimo.jitws.model.CompraCptePercepcionJurisd;
import ar.com.draimo.jitws.model.Proveedor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio de CompraComprobante
 * @author blas
 */

@Service
public class CompraComprobanteService {

    //Referencia al dao
    @Autowired
    ICompraComprobanteDAO elementoDAO;

    //Referencia al dao de CompraComprobanteVencimiento
    @Autowired
    ICompraComprobanteVencimientoDAO vencimientoDAO;

    //Referencia al dao de CompraComprobantePercepcion
    @Autowired
    ICompraComprobantePercepcionDAO percepcionDAO;

    //Referencia al dao de CompraComprobanteItem
    @Autowired
    ICompraComprobanteItemDAO itemDAO;

    //Referencia al dao de CompraCtePercepcionJurisd
    @Autowired
    ICompraCptePercepcionJurisdDAO jurisdiccionDAO;

    //Define la referancia a tipoComprobanteDAO
    @Autowired
    ITipoComprobanteDAO tipoComprobanteDAO;

    //Define la referancia a alicuotaIva
    @Autowired
    IAfipAlicuotaIvaDAO afipAlicuotaIvaDAO;

    //Define la referancia a alicuotaIva
    @Autowired
    IAfipComprobanteDAO afipComprobanteDAO;

    //Define la referancia a condicionCompra
    @Autowired
    ICondicionCompraDAO condicionCompraDAO;

    //Define la referancia a depositoInsumoProducto
    @Autowired
    IDepositoInsumoProductoDAO depositoInsumoProductoDAO;
    
    //Referencia al DAO de moneda
    @Autowired
    IMonedaDAO monedaDAO;
    
    //Referencia al DAO de proveedor
    @Autowired
    IProveedorDAO proveedorDAO;
    
    //Referencia al service de subopcionpestania
    @Autowired
    SubopcionPestaniaService subopcionPestaniaService;
    
    //Obtiene listas necesarias para inicializar el componente (front)
    public InitCompraComprobanteDTO inicializar(int idRol, int idSubopcion) {
        InitCompraComprobanteDTO p = new InitCompraComprobanteDTO();
        p.setPestanias(subopcionPestaniaService.listarPestaniasPorRolYSubopcion(idRol, idSubopcion));
        p.setAfipAlicuotaIvas(afipAlicuotaIvaDAO.findByEstaActivaTrue());
        p.setTipoComprobantes(tipoComprobanteDAO.listarParaFactura());
        p.setTipoComprobantes(tipoComprobanteDAO.listarParaFactura());
        p.setCondicionCompras(condicionCompraDAO.findAll());
        p.setDepositoInsumoProductos(depositoInsumoProductoDAO.findAll());
        p.setLetras(afipComprobanteDAO.listarLetras(0));
        p.setUltimoId(obtenerSiguienteId());
        return p;
    }
    
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
    public Object listarPorFiltros(int idEmpresa, int idProveedor, 
            int fechaTipo, Date fechaDesde, Date fechaHasta, int idTipoComprobante) throws IOException {
        List<CompraComprobante> comprobantes= elementoDAO.listarPorFiltros(idEmpresa, idProveedor, fechaDesde, fechaHasta,
                idTipoComprobante, fechaTipo);
        return aplicarFiltros(comprobantes);
    }
    
    //Obtiene la lista por proveedor y empresa
    public Object listarParaOrdenPago(int idEmpresa, int idProveedor) throws IOException {
        List<CompraComprobante> comprobantes= elementoDAO.listarParaOrdenPago(idEmpresa, idProveedor);
        return aplicarFiltros(comprobantes);
    }
    
    //Verifica que el comprobante no exista
    public boolean verificarUnicidadComprobante(int idProveedor, String codigoAfip, int puntoVenta, int numero) {
        return !elementoDAO.verificarUnicidad(idProveedor, codigoAfip, puntoVenta, numero).isEmpty();
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public CompraComprobante agregar(CompraComprobante elemento) {
        Timestamp fechaRegistracion= new Timestamp(new java.util.Date().getTime());
        elemento.setFechaRegistracion(fechaRegistracion);
        Proveedor p = proveedorDAO.findById(elemento.getProveedor().getId()).get();
        elemento.setAfipCondicionIva(p.getAfipCondicionIva());
        elemento.setMoneda(monedaDAO.findById(1).get());
        elemento.setImporteSaldo(BigDecimal.ZERO);
        elemento.setMonedaCotizacion(BigDecimal.ZERO);
        //Establece condicion de compra, tipo y numero de documento por el proveedor
        elemento.setTipoDocumento(p.getTipoDocumento());
        elemento.setNumeroDocumento(p.getNumeroDocumento());
//        elemento.setCondicionCompra(p.getCondicionCompra());
        //Establece las listas del elemento en otras listas
        List<CompraComprobanteItem> items = elemento.getCompraComprobanteItems();
        List<CompraComprobantePercepcion> percepciones = elemento.getCompraComprobantePercepciones();
        List<CompraComprobanteVencimiento> vencimientos = elemento.getCompraComprobanteVencimientos();
        //Vacia las listas del elemento para poder guardarlo
        elemento.setCompraComprobanteItems(null);
        elemento.setCompraComprobantePercepciones(null);
        elemento.setCompraComprobanteVencimientos(null);
        elemento = elementoDAO.saveAndFlush(elemento);
        //Si items no esta vacio, recorre la lista, establece el comprobante y los guarda
        if(!items.isEmpty()){
            for (CompraComprobanteItem item : items) {
                item.setObservaciones("");
                item.setCompraComprobante(elemento);
                itemDAO.save(item);
            }
        }else {
            throw new DataIntegrityViolationException(MensajeRespuesta.LISTA_SIN_CONTENIDO+" ITEMS");
        }
        //Si percepciones no esta vacio, recorre la lista estableciendo punto de venta, letra y numero
        if(!percepciones.isEmpty()){
            for (CompraComprobantePercepcion percepcion : percepciones) {
                percepcion.setCompraComprobante(elemento);
                percepcion.setLetra(elemento.getLetra());
                percepcion.setPuntoVenta(elemento.getPuntoVenta());
                percepcion.setNumero(elemento.getNumero());
                //Si tiene jurisdicciones se guardan en una lista y se vacia el campo
                List<CompraCptePercepcionJurisd> jurisdicciones =percepcion.getCompraCptePercepcionJurisdicciones();
                percepcion.setCompraCptePercepcionJurisdicciones(null);
                //Guarda la percepcion y se la establece a las jurisdicciones, luego las guarda
                percepcionDAO.save(percepcion);
                if(!jurisdicciones.isEmpty()){
                    for (CompraCptePercepcionJurisd cteJurisdicciones : jurisdicciones) {
                        cteJurisdicciones.setCompraComprobantePercepcion(percepcion);
                        jurisdiccionDAO.save(cteJurisdicciones);
                    }
                }
            }
        }
        //Si vencimientos no esta vacio, recorre la lista, establece el comprobante y los guarda
        if(!vencimientos.isEmpty()){
            for (CompraComprobanteVencimiento vencimiento : vencimientos) {
                vencimiento.setCompraComprobante(elemento);
                vencimientoDAO.save(vencimiento);
            }
        }
        //Retorna el elemento
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
    
    //Retorna un object con los filtros aplicados
    private Object aplicarFiltros(List<CompraComprobante> elementos) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("padre");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("filtroPlanCuenta", theFilter);
        String string =  mapper.writer(filters).writeValueAsString(elementos);
        return new ObjectMapper().readValue(string, Object.class);
    }

}