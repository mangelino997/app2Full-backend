package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IOrdenRecoleccionDAO;
import ar.com.draimo.jitws.dao.IVentaComprobanteDAO;
import ar.com.draimo.jitws.dao.IViajeRemitoDAO;
import ar.com.draimo.jitws.model.RepartoComprobante;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ar.com.draimo.jitws.dao.IRepartoComprobanteDAO;
import ar.com.draimo.jitws.dao.IRepartoDAO;
import ar.com.draimo.jitws.dao.ISeguimientoEstadoDAO;
import ar.com.draimo.jitws.dao.ISucursalDAO;
import ar.com.draimo.jitws.model.Sucursal;
import ar.com.draimo.jitws.model.VentaComprobante;
import ar.com.draimo.jitws.model.ViajeRemito;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Servicio RepartoComprobante
 *
 * @author blas
 */
@Service
public class RepartoComprobanteService {

    //Define la referencia al dao
    @Autowired
    IRepartoComprobanteDAO elementoDAO;

    //Define la referencia al dao de RepartoPropio
    @Autowired
    IRepartoDAO repartoDAO;

    //Define la referencia al dao de sucursal
    @Autowired
    ISucursalDAO sucursalDAO;

    //Define la referencia al dao de venta comprobante
    @Autowired
    IVentaComprobanteDAO ventaComprobanteDAO;

    //Define la referencia al dao de viaje remito
    @Autowired
    IViajeRemitoDAO viajeRemitoDAO;

    //Define la referencia al dao de orden recoleccion
    @Autowired
    IOrdenRecoleccionDAO ordenRecoleccionDAO;

    //define la referencia al dao de seguimientoEstado
    @Autowired
    ISeguimientoEstadoDAO seguimientoEstadoDAO;

    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        RepartoComprobante elemento = elementoDAO.findTopByOrderByIdDesc();
        return (elemento!=null?elemento.getId()+1:1);
    }

    //Obtiene la lista completa
    public Object listar() throws IOException {
        List<RepartoComprobante> ventasComprobantes = elementoDAO.findAll();
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
        String string = mapper.writer(filters).writeValueAsString(ventasComprobantes);
        return new ObjectMapper().readValue(string, Object.class);
    }

    //Obtiene la lista por repartoPropio
    public Object listarComprobantes(int idReparto) throws IOException {
        List<RepartoComprobante> comprobantes = elementoDAO.findByReparto(repartoDAO.findById(idReparto).get());
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
        String string = mapper.writer(filters).writeValueAsString(comprobantes);
        return new ObjectMapper().readValue(string, Object.class);
    }

    //Quita un comprobante de la tabla y la planilla
    public int quitarComprobante(int id) {
        int idrp = elementoDAO.findById(id).get().getReparto().getId();
        elementoDAO.deleteById(id);
        return idrp;
    }

    //Agrega un listado
    @Transactional(rollbackFor = Exception.class)
    public List<RepartoComprobante> agregarComprobantes(List<RepartoComprobante> ctes) {
//        Timestamp fecha = new Timestamp(new java.util.Date().getTime());
//        Seguimiento seguimiento = new Seguimiento();
        List<RepartoComprobante> repartoCtes = new ArrayList<>();
//        seguimiento.setFecha(LocalDateTime.now());
//        seguimiento.setSeguimientoEstado(seguimientoEstadoDAO.findById(3).get());
//        seguimiento.setSucursal(ctes.get(0).getReparto().getSucursal());
//        for (RepartoComprobante cte : ctes) {
//            if (cte.getVentaComprobante() != null) {
//                VentaComprobante v = ventaComprobanteDAO.findByPuntoVentaAndLetraAndNumero(
//                        cte.getVentaComprobante().getPuntoVenta(), cte.getVentaComprobante().getLetra(),
//                        cte.getVentaComprobante().getNumero());
//                if(v!=null){
//                    cte.setVentaComprobante(v);
//                seguimiento.setVentaComprobante(cte.getVentaComprobante());
//                }
//            } else if (cte.getViajeRemito() != null) {
//                ViajeRemito r =viajeRemitoDAO.findByPuntoVentaAndLetraAndNumero(
//                        cte.getViajeRemito().getPuntoVenta(), cte.getViajeRemito().getLetra(),
//                        cte.getViajeRemito().getNumero());
//                if(r!=null) {
//                    seguimiento.setViajeRemito(cte.getViajeRemito());
//                    cte.setViajeRemito(r);
//                }
//                
//            } else if (cte.getOrdenRecoleccion() != null) {
//                seguimiento.setOrdenRecoleccion(cte.getOrdenRecoleccion());
//            }
//            RepartoComprobante rc = (cte.getOrdenRecoleccion() == null && cte.getViajeRemito() == null
//                    && cte.getVentaComprobante() == null ? null : elementoDAO.saveAndFlush(cte));
//            if (rc != null) {
//                seguimientoDAO.saveAndFlush(seguimiento);
//                repartoCtes.add(rc);
//            }
//        }
        return repartoCtes;
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public int agregar(RepartoComprobante c) {
//        Timestamp fecha = new Timestamp(new java.util.Date().getTime());
//        Seguimiento seguimiento = new Seguimiento();
//        seguimiento.setFecha(LocalDateTime.now());
//        seguimiento.setSeguimientoEstado(seguimientoEstadoDAO.findById(3).get());
//        if (c.getVentaComprobante() != null) {
//            c.setVentaComprobante(ventaComprobanteDAO.findByPuntoVentaAndLetraAndNumero(
//                    c.getVentaComprobante().getPuntoVenta(), c.getVentaComprobante().getLetra(),
//                    c.getVentaComprobante().getNumero()));
//            seguimiento.setVentaComprobante(c.getVentaComprobante());
//        } else if (c.getViajeRemito() != null) {
//            c.setViajeRemito(viajeRemitoDAO.findByPuntoVentaAndLetraAndNumero(
//                    c.getViajeRemito().getPuntoVenta(), c.getViajeRemito().getLetra(),
//                    c.getViajeRemito().getNumero()));
//            seguimiento.setViajeRemito(c.getViajeRemito());
//        } else if (c.getOrdenRecoleccion() != null) {
//            seguimiento.setOrdenRecoleccion(c.getOrdenRecoleccion());
//        }
//        Sucursal s = sucursalDAO.obtenerPorReparto(c.getReparto().getId());
//        seguimiento.setSucursal(s);
//        RepartoComprobante rc = (c.getOrdenRecoleccion() == null && c.getViajeRemito() == null
//                && c.getVentaComprobante() == null ? null : elementoDAO.saveAndFlush(c));
//        if (rc != null) {
//            seguimientoDAO.saveAndFlush(seguimiento);
//        }
//        return rc.getId();
        return 1;
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public RepartoComprobante actualizar(RepartoComprobante elemento) {
        return elementoDAO.save(elemento);
    }

    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }

}
