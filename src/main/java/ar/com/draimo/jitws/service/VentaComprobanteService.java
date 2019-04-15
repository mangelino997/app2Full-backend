package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IClienteDAO;
import ar.com.draimo.jitws.dao.IEmpresaDAO;
import ar.com.draimo.jitws.dao.IMonedaDAO;
import ar.com.draimo.jitws.dao.IVentaComprobanteDAO;
import ar.com.draimo.jitws.dao.IVentaComprobanteItemCRDAO;
import ar.com.draimo.jitws.dao.IVentaComprobanteItemFADAO;
import ar.com.draimo.jitws.dao.IVentaComprobanteItemNCDAO;
import ar.com.draimo.jitws.dao.IViajeRemitoDAO;
import ar.com.draimo.jitws.model.VentaComprobante;
import ar.com.draimo.jitws.model.VentaComprobanteItemFA;
import ar.com.draimo.jitws.model.VentaComprobanteItemNC;
import ar.com.draimo.jitws.model.ViajeRemito;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import java.io.IOException;
import java.math.BigDecimal;
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

    //Define la referancia a RemitoDAO
    @Autowired
    IViajeRemitoDAO viajeRemitoDAO;
    
    //Define la referancia a MonedaDAO
    @Autowired
    IMonedaDAO monedaDAO;
    
    //Define la referancia a ClienteDAO
    @Autowired
    IClienteDAO clienteDAO;
    
    //Define la referancia a EmpresaDAO
    @Autowired
    IEmpresaDAO empresaDAO;

    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        VentaComprobante elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId() + 1 : 1;
    }

    //Obtiene la lista completa
    public Object listar() throws IOException {
        List<VentaComprobante> ventasComprobantes = elementoDAO.findAll();
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("ventaComprobante", "ordenVenta");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("filtroVentaComprobanteItemFA", theFilter)
                .addFilter("filtroVentaComprobanteItemCR", theFilter)
                .addFilter("filtroVentaComprobanteItemNC", theFilter)
                .addFilter("filtroOrdenVentaEscala", theFilter)
                .addFilter("filtroOrdenVentaTramo", theFilter);
        String string =  mapper.writer(filters).writeValueAsString(ventasComprobantes);
        return new ObjectMapper().readValue(string, Object.class);
    }

    //Obtiene un registro por puntoVenta, letra y numero
    public VentaComprobante obtener(int puntoVenta, String letra, int numero) {
        return elementoDAO.findByPuntoVentaAndLetraAndNumero(puntoVenta, letra, numero);
    }
    
    //Obtiene una lista por cliente y empresa
    public Object listarPorClienteYEmpresa(int idCliente, int idEmpresa) throws IOException {
        List<VentaComprobante> ventasComprobantes = elementoDAO.findByClienteAndEmpresa(
                clienteDAO.findById(idCliente).get(), empresaDAO.findById(idEmpresa).get());
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("ventaComprobante", "ordenVenta");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("filtroVentaComprobanteItemFA", theFilter)
                .addFilter("filtroVentaComprobanteItemCR", theFilter)
                .addFilter("filtroVentaComprobanteItemNC", theFilter)
                .addFilter("filtroOrdenVentaEscala", theFilter)
                .addFilter("filtroOrdenVentaTramo", theFilter);
        String string =  mapper.writer(filters).writeValueAsString(ventasComprobantes);
        return new ObjectMapper().readValue(string, Object.class);
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public VentaComprobante agregar(VentaComprobante elemento) {
        elemento = formatearStrings(elemento);
        elemento.setMoneda(monedaDAO.findById(1).get());
        elemento.setMonedaCotizacion(new BigDecimal(0.0));
        elemento.setFechaRegistracion(LocalDateTime.now());
        VentaComprobante vc = elementoDAO.saveAndFlush(elemento);
        ViajeRemito viajeRemito;
        //Agrega los items FA
        for (VentaComprobanteItemFA ventaComprobanteItemFA : elemento.getVentaComprobanteItemFAs()) {
            ventaComprobanteItemFA.setVentaComprobante(vc);
            if (ventaComprobanteItemFA.getViajeRemito() != null) {
                viajeRemito = viajeRemitoDAO.findById(ventaComprobanteItemFA.getViajeRemito().getId()).get();
                viajeRemito.setEstaFacturado(true);
                viajeRemitoDAO.save(viajeRemito);
            }
            ventaComprobanteItemFADAO.saveAndFlush(ventaComprobanteItemFA);
        }
        //Agrega item ContraReembolso
        if(elemento.getVentaComprobanteItemCR() != null) {
            elemento.getVentaComprobanteItemCRs().get(0).setVentaComprobante(vc);
            ventaComprobanteItemCRDAO.saveAndFlush(elemento.getVentaComprobanteItemCRs().get(0));
        }
        //Agrega item NC
        for(VentaComprobanteItemNC ventaComprobanteItemNC : elemento.getVentaComprobanteItemNC()) {
            ventaComprobanteItemNC.setVentaComprobante(vc);
            ventaComprobanteItemNCDAO.saveAndFlush(ventaComprobanteItemNC);
        }
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
