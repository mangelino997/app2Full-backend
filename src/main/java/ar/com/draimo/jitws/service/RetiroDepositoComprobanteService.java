//Paquete al que pertenece el servicio
package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IRetiroDepositoComprobanteDAO;
import ar.com.draimo.jitws.dao.IRetiroDepositoDAO;
import ar.com.draimo.jitws.dao.ISeguimientoEstadoDAO;
import ar.com.draimo.jitws.dao.ISeguimientoSituacionDAO;
import ar.com.draimo.jitws.dao.ISeguimientoVentaComprobanteDAO;
import ar.com.draimo.jitws.dao.ISeguimientoViajeRemitoDAO;
import ar.com.draimo.jitws.dao.ISucursalDAO;
import ar.com.draimo.jitws.dao.ITipoComprobanteDAO;
import ar.com.draimo.jitws.dao.IVentaComprobanteDAO;
import ar.com.draimo.jitws.dao.IViajeRemitoDAO;
import ar.com.draimo.jitws.exception.MensajeRespuesta;
import ar.com.draimo.jitws.model.RetiroDeposito;
import ar.com.draimo.jitws.model.RetiroDepositoComprobante;
import ar.com.draimo.jitws.model.SeguimientoEstado;
import ar.com.draimo.jitws.model.SeguimientoSituacion;
import ar.com.draimo.jitws.model.SeguimientoVentaComprobante;
import ar.com.draimo.jitws.model.SeguimientoViajeRemito;
import ar.com.draimo.jitws.model.Sucursal;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio retiroDepositoComprobante
 *
 * @author blas
 */
@Service
public class RetiroDepositoComprobanteService {

    //Define la referencia al dao
    @Autowired
    IRetiroDepositoComprobanteDAO elementoDAO;

    //Define la referencia al dao de RetiroDeposito
    @Autowired
    IRetiroDepositoDAO retiroDepositoDAO;

    //Define la referencia al dao de ventaComprobante
    @Autowired
    IVentaComprobanteDAO ventaComprobanteDAO;

    //Define la referencia al dao de tipoComprobante
    @Autowired
    ITipoComprobanteDAO tipoComprobanteDAO;

    //Define la referencia al dao de viajeRemito
    @Autowired
    IViajeRemitoDAO viajeRemitoDAO;

    //Define la referencia al dao de sucursal
    @Autowired
    ISucursalDAO sucursalDAO;

    //Define la referencia al dao de SeguimientoEstado
    @Autowired
    ISeguimientoEstadoDAO seguimientoEstadoDAO;

    //Define la referencia al dao de SeguimientoSituacion
    @Autowired
    ISeguimientoSituacionDAO seguimientoSituacionDAO;

    //Define la referencia al dao de SeguimientoViajeRemito
    @Autowired
    ISeguimientoViajeRemitoDAO seguimientoViajeRemitoDAO;

    //Define la referencia al dao de SeguimientoventaComprobante
    @Autowired
    ISeguimientoVentaComprobanteDAO seguimientoVentaComprobanteDAO;

    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        RetiroDepositoComprobante elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId() + 1 : 1;
    }

    //Obtiene la lista completa
    public Object listar() throws IOException {
        List<RetiroDepositoComprobante> elementos = elementoDAO.findAll();
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
        String string = mapper.writer(filters).writeValueAsString(elementos);
        return new ObjectMapper().readValue(string, Object.class);
    }

    //Obtiene la lista por RetiroDeposito
    public Object listarComprobantes(int idRetiroDeposito) throws IOException {
        List<RetiroDepositoComprobante> elementos = elementoDAO.findByRetiroDeposito(
                retiroDepositoDAO.findById(idRetiroDeposito).get());
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
        String string = mapper.writer(filters).writeValueAsString(elementos);
        return new ObjectMapper().readValue(string, Object.class);
    }

    //Quita un comprobante de la tabla y la planilla
    public int quitarComprobante(int id) {
        int idrp = elementoDAO.findById(id).get().getRetiroDeposito().getId();
        elementoDAO.deleteById(id);
        return idrp;
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public RetiroDepositoComprobante agregar(RetiroDepositoComprobante c) {
        RetiroDeposito retiroDeposito = new RetiroDeposito();
        SeguimientoVentaComprobante svCte = new SeguimientoVentaComprobante();
        SeguimientoViajeRemito svRemito = new SeguimientoViajeRemito();
        SeguimientoEstado se = seguimientoEstadoDAO.findById(11).get();
        SeguimientoSituacion ss = seguimientoSituacionDAO.findById(1).get();
        Sucursal sucursal = sucursalDAO.findById(retiroDeposito.getSucursal().getId()).get();
        if (c.getVentaComprobante() != null) {
            svCte.setSeguimientoEstado(se);
            svCte.setSeguimientoSituacion(ss);
            svCte.setFecha(new Timestamp(new java.util.Date().getTime()));
            svCte.setSucursal(sucursal);
            seguimientoVentaComprobanteDAO.saveAndFlush(svCte);
        } else if (c.getViajeRemito() != null) {
            svRemito.setSeguimientoEstado(se);
            svRemito.setSeguimientoSituacion(ss);
            svRemito.setFecha(new Timestamp(new java.util.Date().getTime()));
            svRemito.setSucursal(sucursal);
            seguimientoViajeRemitoDAO.saveAndFlush(svRemito);
        } else {
            throw new DataIntegrityViolationException(MensajeRespuesta.SIN_COMPROBANTES);
        }
        return elementoDAO.saveAndFlush(c);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public RetiroDepositoComprobante actualizar(RetiroDepositoComprobante elemento) {
        return elementoDAO.save(elemento);
    }

    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        RetiroDepositoComprobante rdCte = elementoDAO.findById(elemento).get();
        if (rdCte.getVentaComprobante() != null) {
            seguimientoVentaComprobanteDAO.deleteByVentaComprobante(
                    ventaComprobanteDAO.findById(rdCte.getVentaComprobante().getId()).get());
        } else {
            seguimientoViajeRemitoDAO.deleteByViajeRemito(viajeRemitoDAO.findById(
                    rdCte.getViajeRemito().getId()).get());
        }
        elementoDAO.deleteById(elemento);
    }

}