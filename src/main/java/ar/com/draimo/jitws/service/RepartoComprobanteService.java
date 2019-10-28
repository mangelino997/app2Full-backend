//Paquete al que pertenece el servicio
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
import ar.com.draimo.jitws.dao.ITipoComprobanteDAO;
import ar.com.draimo.jitws.model.SeguimientoOrdenRecoleccion;
import ar.com.draimo.jitws.model.TipoComprobante;
import ar.com.draimo.jitws.model.VentaComprobante;
import ar.com.draimo.jitws.model.SeguimientoVentaComprobante;
import ar.com.draimo.jitws.model.ViajeRemito;
import ar.com.draimo.jitws.model.SeguimientoViajeRemito;
import java.util.ArrayList;
import ar.com.draimo.jitws.dao.ISeguimientoOrdenRecoleccionDAO;
import ar.com.draimo.jitws.dao.ISeguimientoSituacionDAO;
import ar.com.draimo.jitws.dao.ISeguimientoVentaComprobanteDAO;
import ar.com.draimo.jitws.dao.ISeguimientoViajeRemitoDAO;
import ar.com.draimo.jitws.dao.IVentaComprobanteItemFADAO;
import ar.com.draimo.jitws.model.Reparto;
import ar.com.draimo.jitws.model.SeguimientoEstado;
import ar.com.draimo.jitws.model.SeguimientoSituacion;
import java.sql.Timestamp;

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

    //Define la referencia al dao de Reparto
    @Autowired
    IRepartoDAO repartoDAO;

    //Define la referencia al dao de sucursal
    @Autowired
    ISucursalDAO sucursalDAO;

    //Define la referencia al dao de venta comprobante
    @Autowired
    IVentaComprobanteDAO ventaComprobanteDAO;

    //Define la referencia al dao de venta comprobante
    @Autowired
    IVentaComprobanteItemFADAO ventaComprobanteItemFADAO;

    //Define la referencia al dao de tipo comprobante
    @Autowired
    ITipoComprobanteDAO tipoComprobanteDAO;

    //Define la referencia al dao de viaje remito
    @Autowired
    IViajeRemitoDAO viajeRemitoDAO;

    //Define la referencia al dao de orden recoleccion
    @Autowired
    IOrdenRecoleccionDAO ordenRecoleccionDAO;

    //define la referencia al dao de seguimientoEstado
    @Autowired
    ISeguimientoEstadoDAO seguimientoEstadoDAO;

    //define la referencia al dao de seguimientoSituacion
    @Autowired
    ISeguimientoSituacionDAO seguimientoSituacionDAO;

    //define la referencia al dao de SeguimientoOrdenRecoleccion
    @Autowired
    ISeguimientoOrdenRecoleccionDAO seguimientoRecoleccionDAO;

    //define la referencia al dao de SeguimientoVentaComprobante
    @Autowired
    ISeguimientoVentaComprobanteDAO seguimientoComprobanteDAO;

    //define la referencia al dao de SeguimientoViajeRemito
    @Autowired
    ISeguimientoViajeRemitoDAO seguimientoRemitoDAO;

    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        RepartoComprobante elemento = elementoDAO.findTopByOrderByIdDesc();
        return (elemento != null ? elemento.getId() + 1 : 1);
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
        List<RepartoComprobante> comprobantes = elementoDAO.findByReparto(repartoDAO.obtenerPorId(idReparto));
        ObjectMapper mapper = new ObjectMapper();
        for(RepartoComprobante comprobante : comprobantes) {
            if(comprobante.getVentaComprobante()!=null) {
                comprobante.getVentaComprobante().setVentaComprobanteItemFAs(
                    ventaComprobanteItemFADAO.listarPorVentaComprobante(comprobante.getVentaComprobante().getId()));
            }
        }
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("ventaComprobante", "ordenVenta", "cliente", "datos");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("filtroVentaComprobanteItemFA", theFilter)
                .addFilter("filtroVentaComprobanteItemCR", theFilter)
                .addFilter("filtroVentaComprobanteItemNC", theFilter)
                .addFilter("filtroOrdenVentaEscala", theFilter)
                .addFilter("clienteordenventafiltro", theFilter)
                .addFilter("filtroOrdenVentaTramo", theFilter)
                .addFilter("filtroPdf", theFilter)
                .addFilter("filtroFoto", theFilter);
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
    public List<RepartoComprobante> conformarComprobantes(Reparto reparto) {
        //Recorre la lista de reparto comprobante
        for (RepartoComprobante cte : reparto.getRepartoComprobantes()) {
            if (cte.getId() != 0) {
                conformarComprobante(cte);
            }
        }
        return reparto.getRepartoComprobantes();
    }

    //Conforma un comprobante
    @Transactional(rollbackFor = Exception.class)
    public RepartoComprobante conformarComprobante(RepartoComprobante repartoCte) {
        SeguimientoEstado seguimientoEstado = seguimientoEstadoDAO.findById(6).get();
        SeguimientoSituacion seguimientoSituacion = seguimientoSituacionDAO.findById(1).get();
        SeguimientoOrdenRecoleccion ordenSeguimiento = new SeguimientoOrdenRecoleccion();
        SeguimientoViajeRemito viajeSeguimiento = new SeguimientoViajeRemito();
        SeguimientoVentaComprobante ventaSeguimiento = new SeguimientoVentaComprobante();
        //Recorre la lista de reparto comprobante
        //Si no esta vacio, guarda el seguimiento para aquel comprobante que contenga el repartoComprobante
        if (repartoCte.getOrdenRecoleccion() != null) {
            ordenSeguimiento.setOrdenRecoleccion(repartoCte.getOrdenRecoleccion());
            ordenSeguimiento.setFecha(new Timestamp(new java.util.Date().getTime()));
            ordenSeguimiento.setSeguimientoEstado(seguimientoEstado);
            ordenSeguimiento.setSeguimientoSituacion(seguimientoSituacion);
            ordenSeguimiento.setSucursal(repartoCte.getReparto().getSucursal());
            seguimientoRecoleccionDAO.saveAndFlush(ordenSeguimiento);
        } else if (repartoCte.getViajeRemito() != null) {
            viajeSeguimiento.setViajeRemito(repartoCte.getViajeRemito());
            viajeSeguimiento.setFecha(new Timestamp(new java.util.Date().getTime()));
            viajeSeguimiento.setSeguimientoEstado(seguimientoEstado);
            viajeSeguimiento.setSeguimientoSituacion(seguimientoSituacion);
            viajeSeguimiento.setSucursal(repartoCte.getReparto().getSucursal());
            seguimientoRemitoDAO.saveAndFlush(viajeSeguimiento);
        } else {
            ventaSeguimiento.setVentaComprobante(repartoCte.getVentaComprobante());
            ventaSeguimiento.setFecha(new Timestamp(new java.util.Date().getTime()));
            ventaSeguimiento.setSeguimientoEstado(seguimientoEstado);
            ventaSeguimiento.setSeguimientoSituacion(seguimientoSituacion);
            ventaSeguimiento.setSucursal(repartoCte.getReparto().getSucursal());
            seguimientoComprobanteDAO.saveAndFlush(ventaSeguimiento);
        }
        return repartoCte;
    }

    //Agrega un listado
    @Transactional(rollbackFor = Exception.class)
    public List<RepartoComprobante> agregarComprobantes(List<RepartoComprobante> ctes) {
        SeguimientoOrdenRecoleccion ordenSeguimiento = new SeguimientoOrdenRecoleccion();
        SeguimientoViajeRemito viajeSeguimiento = new SeguimientoViajeRemito();
        SeguimientoVentaComprobante ventaSeguimiento = new SeguimientoVentaComprobante();
        List<RepartoComprobante> repartoCtes = new ArrayList<>();
        //Recorre la lista de reparto comprobante
        for (RepartoComprobante cte : ctes) {
            //Consulta si el repartocomprobante esta vacio, para no guardar un registro sin ninguno de los tres comprobantes
            RepartoComprobante rc = (cte.getOrdenRecoleccion() == null && cte.getViajeRemito() == null
                    && cte.getVentaComprobante() == null ? null : cte);
            //Consulta si ventaComprobante es nulo para establecer el seguimiento y guardarlo
            if (cte.getVentaComprobante() != null) {
                TipoComprobante tc = tipoComprobanteDAO.findById(cte.getVentaComprobante().getTipoComprobante().getId()).get();
                VentaComprobante v = ventaComprobanteDAO.findByPuntoVentaAndLetraAndNumeroAndTipoComprobante(
                        cte.getVentaComprobante().getPuntoVenta(), cte.getVentaComprobante().getLetra(),
                        cte.getVentaComprobante().getNumero(), tc);
                if (v != null) {
                    cte.setVentaComprobante(v);
                    ventaSeguimiento.setVentaComprobante(cte.getVentaComprobante());
                    ventaSeguimiento.setFecha(new Timestamp(new java.util.Date().getTime()));
                    ventaSeguimiento.setSeguimientoEstado(seguimientoEstadoDAO.findById(3).get());
                    ventaSeguimiento.setSucursal(ctes.get(0).getReparto().getSucursal());
                    seguimientoComprobanteDAO.saveAndFlush(ventaSeguimiento);
                }
                //Consulta si viajeRemito es nulo para establecer el seguimiento y guardarlo
            } else if (cte.getViajeRemito() != null) {
                ViajeRemito r = viajeRemitoDAO.findByPuntoVentaAndLetraAndNumero(
                        cte.getViajeRemito().getPuntoVenta(), cte.getViajeRemito().getLetra(),
                        cte.getViajeRemito().getNumero());
                if (r != null) {
                    viajeSeguimiento.setViajeRemito(cte.getViajeRemito());
                    cte.setViajeRemito(r);
                    viajeSeguimiento.setFecha(new Timestamp(new java.util.Date().getTime()));
                    viajeSeguimiento.setSeguimientoEstado(seguimientoEstadoDAO.findById(3).get());
                    viajeSeguimiento.setSucursal(ctes.get(0).getReparto().getSucursal());
                    seguimientoRemitoDAO.saveAndFlush(viajeSeguimiento);
                }
                //Consulta si ordenRecoleccion es nulo para establecer el seguimiento y guardarlo
            } else if (cte.getOrdenRecoleccion() != null) {
                ordenSeguimiento.setOrdenRecoleccion(cte.getOrdenRecoleccion());
                ordenSeguimiento.setFecha(new Timestamp(new java.util.Date().getTime()));
                ordenSeguimiento.setSeguimientoEstado(seguimientoEstadoDAO.findById(3).get());
                ordenSeguimiento.setSucursal(ctes.get(0).getReparto().getSucursal());
                seguimientoRecoleccionDAO.saveAndFlush(ordenSeguimiento);
            }
        }
        return repartoCtes;
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public int agregar(RepartoComprobante cte) {
        SeguimientoOrdenRecoleccion ordenSeguimiento = new SeguimientoOrdenRecoleccion();
        SeguimientoViajeRemito viajeSeguimiento = new SeguimientoViajeRemito();
        SeguimientoVentaComprobante ventaSeguimiento = new SeguimientoVentaComprobante();
        //Consulta si el repartocomprobante esta vacio, para no guardar un registro sin ninguno de los tres comprobantes
        RepartoComprobante rc = (cte.getOrdenRecoleccion() == null && cte.getViajeRemito() == null
                && cte.getVentaComprobante() == null ? null : cte);
        //Consulta si ventaComprobante es nulo para establecer el seguimiento y guardarlo
        if (cte.getVentaComprobante() != null) {
            TipoComprobante tc = tipoComprobanteDAO.findById(cte.getVentaComprobante().getTipoComprobante().getId()).get();
            VentaComprobante v = ventaComprobanteDAO.findByPuntoVentaAndLetraAndNumeroAndTipoComprobante(
                    cte.getVentaComprobante().getPuntoVenta(), cte.getVentaComprobante().getLetra(),
                    cte.getVentaComprobante().getNumero(), tc);
            if (v != null) {
                cte.setVentaComprobante(v);
                ventaSeguimiento.setVentaComprobante(v);
                ventaSeguimiento.setFecha(new Timestamp(new java.util.Date().getTime()));
                ventaSeguimiento.setSeguimientoEstado(seguimientoEstadoDAO.findById(3).get());
                ventaSeguimiento.setSucursal(cte.getReparto().getSucursal());
                seguimientoComprobanteDAO.saveAndFlush(ventaSeguimiento);
            }
            //Consulta si viajeRemito es nulo para establecer el seguimiento y guardarlo
        } else if (cte.getViajeRemito() != null) {
            ViajeRemito r = viajeRemitoDAO.findByPuntoVentaAndLetraAndNumero(
                    cte.getViajeRemito().getPuntoVenta(), cte.getViajeRemito().getLetra(),
                    cte.getViajeRemito().getNumero());
            if (r != null) {
                viajeSeguimiento.setViajeRemito(r);
                cte.setViajeRemito(r);
                viajeSeguimiento.setFecha(new Timestamp(new java.util.Date().getTime()));
                viajeSeguimiento.setSeguimientoEstado(seguimientoEstadoDAO.findById(3).get());
                viajeSeguimiento.setSucursal(cte.getReparto().getSucursal());
                seguimientoRemitoDAO.saveAndFlush(viajeSeguimiento);
            }
            //Consulta si ordenRecoleccion es nulo para establecer el seguimiento y guardarlo
        } else if (cte.getOrdenRecoleccion() != null) {
            ordenSeguimiento.setOrdenRecoleccion(cte.getOrdenRecoleccion());
            ordenSeguimiento.setFecha(new Timestamp(new java.util.Date().getTime()));
            ordenSeguimiento.setSeguimientoEstado(seguimientoEstadoDAO.findById(3).get());
            ordenSeguimiento.setSucursal(cte.getReparto().getSucursal());
            seguimientoRecoleccionDAO.saveAndFlush(ordenSeguimiento);
        }
        return (rc != null ? elementoDAO.saveAndFlush(cte).getId() : 1);
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
