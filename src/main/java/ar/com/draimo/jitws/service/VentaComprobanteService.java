//Paquete al que pertenece el servicio
package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IAfipAlicuotaIvaDAO;
import ar.com.draimo.jitws.dao.IClienteDAO;
import ar.com.draimo.jitws.dao.IEmpresaDAO;
import ar.com.draimo.jitws.dao.IEmpresaOrdenVentaDAO;
import ar.com.draimo.jitws.dao.IMonedaDAO;
import ar.com.draimo.jitws.dao.IOrdenVentaTarifaDAO;
import ar.com.draimo.jitws.dao.IProvinciaDAO;
import ar.com.draimo.jitws.dao.IPuntoVentaDAO;
import ar.com.draimo.jitws.dao.ISucursalDAO;
import ar.com.draimo.jitws.dao.ITipoComprobanteDAO;
import ar.com.draimo.jitws.dao.IVentaComprobanteDAO;
import ar.com.draimo.jitws.dao.IVentaComprobanteItemCRDAO;
import ar.com.draimo.jitws.dao.IVentaComprobanteItemFADAO;
import ar.com.draimo.jitws.dao.IVentaComprobanteItemNCDAO;
import ar.com.draimo.jitws.dao.IVentaTipoItemDAO;
import ar.com.draimo.jitws.dao.IViajeRemitoDAO;
import ar.com.draimo.jitws.dao.IViajeTramoClienteRemitoDAO;
import ar.com.draimo.jitws.dto.InitFacturaDTO;
import ar.com.draimo.jitws.model.PuntoVenta;
import ar.com.draimo.jitws.model.TipoComprobante;
import ar.com.draimo.jitws.model.VentaComprobante;
import ar.com.draimo.jitws.model.VentaComprobanteItemFA;
import ar.com.draimo.jitws.model.VentaComprobanteItemNC;
import ar.com.draimo.jitws.model.ViajeRemito;
import ar.com.draimo.jitws.model.ViajeTramoClienteRemito;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
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

    //Define la referencia a VentaComprobanteitemNCDAO
    @Autowired
    IVentaComprobanteItemNCDAO ventaComprobanteItemNCDAO;

    //Define la referencia a VentaTipoitem
    @Autowired
    IVentaTipoItemDAO ventaTipoItemDAO;

    //Define la referancia a ViajeRemitoDAO
    @Autowired
    IViajeRemitoDAO viajeRemitoDAO;

    //Define la referancia a ViajeTramoClienteRemitoDAO
    @Autowired
    IViajeTramoClienteRemitoDAO viajeTramoClienteRemitoDAO;

    //Define la referancia a tipoComprobanteDAO
    @Autowired
    ITipoComprobanteDAO tipoComprobanteDAO;

    //Define la referancia a MonedaDAO
    @Autowired
    IMonedaDAO monedaDAO;

    //Define la referancia a alicuotaIva
    @Autowired
    IAfipAlicuotaIvaDAO afipAlicuotaIvaDAO;

    //Define la referancia a PuntoVentaDAO
    @Autowired
    IPuntoVentaDAO puntoVentaDAO;

    //Define la referancia a provinciaDAO
    @Autowired
    IProvinciaDAO provinciaDAO;

    //Define la referancia a ClienteDAO
    @Autowired
    IClienteDAO clienteDAO;

    //Define la referancia a EmpresaDAO
    @Autowired
    IEmpresaDAO empresaDAO;

    //Define la referancia a sucursalDAO
    @Autowired
    ISucursalDAO sucursalDAO;

    //Define la referancia a EmpresaOrdenVentaDAO
    @Autowired
    IEmpresaOrdenVentaDAO empresaOrdenVentaDAO;

    //Define la referancia a ordenVentaTarifaDAO
    @Autowired
    IOrdenVentaTarifaDAO ordenVentaTarifaDAO;

    //Obtiene el listado de elementos necesarios para inicializar el componente
    public InitFacturaDTO inicializarFactura(int idEmpresa, int idSucursal) {
        InitFacturaDTO p = new InitFacturaDTO();
        p.setAfipAlicuotaIvas(afipAlicuotaIvaDAO.findByEstaActivaTrue());
        p.setEmpresaOrdenVentas(empresaOrdenVentaDAO.findAll());
        p.setPuntoVentas(puntoVentaDAO.findByEmpresaAndSucursalAndFeTrueAndEstaHabilitadoTrue(
                empresaDAO.findById(idEmpresa).get(), sucursalDAO.findById(idSucursal).get()));
        p.setTipoComprobantes(tipoComprobanteDAO.listarParaFactura());
        p.setUltimoId(obtenerSiguienteId());
        p.setVentaTipoItems(ventaTipoItemDAO.listarTipoComprobante(1));
        return p;
    }

    //Obtiene el listado de elementos necesarios para inicializar el componente
    public InitFacturaDTO inicializarNotaCredito(int idEmpresa, int idSucursal) {
        InitFacturaDTO p = new InitFacturaDTO();
        p.setAfipAlicuotaIvas(afipAlicuotaIvaDAO.findByEstaActivaTrue());
        p.setProvincias(provinciaDAO.findAll());
        p.setPuntoVentas(puntoVentaDAO.findByEmpresaAndSucursalAndFeTrueAndEstaHabilitadoTrue(
                empresaDAO.findById(idEmpresa).get(), sucursalDAO.findById(idSucursal).get()));
        p.setTipoComprobantes(tipoComprobanteDAO.listarParaNotaCredito());
        p.setUltimoId(obtenerSiguienteId());
        p.setVentaTipoItems(ventaTipoItemDAO.listarTipoComprobante(3));
        return p;
    }

    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        VentaComprobante elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId() + 1 : 1;
    }

    //Obtiene la lista completa
    public Object listar() throws IOException {
        List<VentaComprobante> elementos = elementoDAO.findAll();
        return retornarObjeto(elementos, null);
    }

    //Obtiene la lista de registros que no estan en reparto
    public Object listarComprobantesDisponibles() throws IOException {
        List<VentaComprobante> elementos = elementoDAO.listarComprobantesDisponibles();
        return retornarObjeto(elementos, null);
    }

    //Obtiene la lista por tipo de comprobante
    public Object listarPorTipoComprobante(int idTipoComprobante) throws IOException {
        TipoComprobante tipoComprobante = tipoComprobanteDAO.findById(idTipoComprobante).get();
        List<VentaComprobante> elementos = elementoDAO.findByTipoComprobante(tipoComprobante);
        return retornarObjeto(elementos, null);
    }

    //Obtiene un registro por puntoVenta, letra y numero
    public Object obtener(int puntoVenta, String letra, int numero, int idTipoComprobante) throws IOException {
        TipoComprobante t = tipoComprobanteDAO.findById(idTipoComprobante).get();
        VentaComprobante ventaComprobante
                = elementoDAO.findByPuntoVentaAndLetraAndNumeroAndTipoComprobante(
                        puntoVenta, letra, numero, t);
        ventaComprobante.setVentaComprobanteItemFAs(
                ventaComprobanteItemFADAO.listarPorVentaComprobante(ventaComprobante.getId()));
        return retornarObjeto(null, ventaComprobante);
    }

    //Obtiene una lista por cliente y empresa
    public Object listarPorClienteYEmpresa(int idCliente, int idEmpresa) throws IOException {
        List<VentaComprobante> elementos = elementoDAO.findByClienteAndEmpresa(
                clienteDAO.findById(idCliente).get(), empresaDAO.findById(idEmpresa).get());
        return retornarObjeto(elementos, null);
    }

    //Obtiene una lista por cliente y empresa para cobranza
    public Object listarParaCobranza(int idCliente, int idEmpresa) throws IOException {
        List<VentaComprobante> elementos = elementoDAO.listarComprobantesPorClienteYEmpresa(idCliente, idEmpresa);
        return retornarObjeto(elementos, null);
    }

    //Obtiene una lista para notas de credito por cliente y empresa
    public Object listarParaCreditosPorClienteYEmpresa(int idCliente, int idEmpresa) throws IOException {
        List<VentaComprobante> elementos = elementoDAO.listarParaNotasDeCredito(idCliente, idEmpresa);
        return retornarObjeto(elementos, null);
    }

    //Obtiene una lista de letras
    public List<String> listarLetras() {
        return elementoDAO.listarLetras();
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public VentaComprobante agregar(VentaComprobante elemento) {
        elemento.setMoneda(monedaDAO.findById(1).get());
        elemento.setMonedaCotizacion(new BigDecimal(0.0));
        elemento.setFechaRegistracion(Timestamp.valueOf(LocalDateTime.now()));
        VentaComprobante ventaComprobante = elementoDAO.saveAndFlush(elemento);
        ViajeRemito viajeRemito;
        ViajeTramoClienteRemito viajeTramoClienteRemito;
        BigDecimal importeTotal = new BigDecimal(0.00);
        //Actualiza el ultimo numero de punto de venta
        PuntoVenta puntoVenta = puntoVentaDAO.findByPuntoVentaAndSucursalAndEmpresaAndAfipComprobante_CodigoAfip(
                elemento.getPuntoVenta(), elemento.getSucursal(), elemento.getEmpresa(), elemento.getCodigoAfip());
        puntoVenta.setUltimoNumero(elemento.getNumero());
        puntoVentaDAO.save(puntoVenta);
        //Agrega los items FA (Si es una factura)
        if (elemento.getVentaComprobanteItemFAs() != null) {
            for (VentaComprobanteItemFA itemFA : elemento.getVentaComprobanteItemFAs()) {
                itemFA.setVentaComprobante(ventaComprobante);
                if (itemFA.getViajeRemito() != null) {
                    viajeRemito = viajeRemitoDAO.findById(itemFA.getViajeRemito().getId()).get();
                    viajeRemito.setEstaFacturado(true);
                    viajeRemitoDAO.save(viajeRemito);
                }
                if (itemFA.getViajeTramoClienteRemito() != null) {
                    viajeTramoClienteRemito = viajeTramoClienteRemitoDAO.obtenerPorId(
                            itemFA.getViajeTramoClienteRemito().getId());
                    viajeTramoClienteRemito.setEstaFacturado(true);
                    viajeTramoClienteRemitoDAO.save(viajeTramoClienteRemito);
                }
                importeTotal.add(itemFA.getImporteNetoGravado());
                if (itemFA.getOrdenVentaTarifa() != null) {
                    itemFA.setOrdenVentaTarifa(ordenVentaTarifaDAO.obtenerPorOrdenVentaYTipoTarifa(
                            itemFA.getOrdenVentaTarifa().getOrdenVenta().getId(),
                            itemFA.getOrdenVentaTarifa().getTipoTarifa().getId()));
                }
                ventaComprobanteItemFADAO.saveAndFlush(itemFA);
            }
        }
        //Agrega item ContraReembolso
        if (elemento.getVentaComprobanteItemCR() != null && elemento.getVentaComprobanteItemCR().size() > 0) {
            elemento.getVentaComprobanteItemCR().get(0).setVentaComprobante(ventaComprobante);
            importeTotal.add(elemento.getVentaComprobanteItemCR().get(0).getImporteNetoGravado());
            ventaComprobanteItemCRDAO.saveAndFlush(elemento.getVentaComprobanteItemCR().get(0));
        }
        if (elemento.getVentaComprobanteItemNC() != null) {
            //Agrega item NC
            for (VentaComprobanteItemNC ventaComprobanteItemNC : elemento.getVentaComprobanteItemNC()) {
                //Resta el saldo al comprobante afectado
                VentaComprobante ventaComprobanteItem = elementoDAO.findById(ventaComprobanteItemNC.getId()).get();
                BigDecimal saldoRestante = ventaComprobanteItem.getImporteSaldo().subtract(ventaComprobanteItemNC.getImporteNetoGravado());
                ventaComprobanteItem.setImporteSaldo(saldoRestante);
                elementoDAO.saveAndFlush(ventaComprobanteItem);

                ventaComprobanteItemNC.setVentaComprobante(ventaComprobante);
                ventaComprobanteItemNCDAO.saveAndFlush(ventaComprobanteItemNC);
            }
        }
        elemento.setImporteSaldo(elemento.getImporteTotal());
        return elementoDAO.save(formatearStrings(elemento));
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(VentaComprobante elemento) {
        elementoDAO.save(formatearStrings(elemento));
    }

    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }

    //Formatea los strings
    private VentaComprobante formatearStrings(VentaComprobante elemento) {
        elemento.setLetra(elemento.getLetra().trim());
        elemento.setCodigoAfip(elemento.getCodigoAfip().trim());
        return elemento;
    }

    //Convierte una lista o un elemento a object para retornar con filtros aplicados
    private Object retornarObjeto(List<VentaComprobante> elementos, VentaComprobante elemento) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("ventaComprobante", "ordenVenta", "cliente");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("filtroVentaComprobanteItemFA", theFilter)
                .addFilter("filtroVentaComprobanteItemCR", theFilter)
                .addFilter("filtroVentaComprobanteItemNC", theFilter)
                .addFilter("filtroOrdenVentaEscala", theFilter)
                .addFilter("clienteordenventafiltro", theFilter)
                .addFilter("filtroOrdenVentaTramo", theFilter);
        String string = mapper.writer(filters).writeValueAsString(elemento != null ? elemento : elementos);
        return mapper.readValue(string, Object.class);
    }

}