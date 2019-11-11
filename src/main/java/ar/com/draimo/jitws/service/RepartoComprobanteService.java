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
import ar.com.draimo.jitws.model.OrdenRecoleccion;
import ar.com.draimo.jitws.model.Reparto;
import ar.com.draimo.jitws.model.SeguimientoEstado;
import ar.com.draimo.jitws.model.SeguimientoSituacion;
import ar.com.draimo.jitws.model.Sucursal;
import java.sql.Date;
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
        List<RepartoComprobante> elementos = elementoDAO.findAll();
        return aplicarFiltros(elementos);
    }

    //Obtiene la lista por repartoPropio
    public Object listarComprobantes(int idReparto) throws IOException {
        List<RepartoComprobante> elementos = elementoDAO.findByReparto(repartoDAO.obtenerPorId(idReparto));
        for(RepartoComprobante comprobante : elementos) {
            if(comprobante.getVentaComprobante()!=null) {
                comprobante.getVentaComprobante().setVentaComprobanteItemFAs(
                    ventaComprobanteItemFADAO.listarPorVentaComprobante(comprobante.getVentaComprobante().getId()));
            }
        }
        return aplicarFiltros(elementos);
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
        List<RepartoComprobante> repartoCtes = new ArrayList<>();
        //Recorre la lista de reparto comprobante
        for (RepartoComprobante cte : reparto.getRepartoComprobantes()) {
            if (cte.getId() != 0) {
                cte = conformarComprobante(cte);
                repartoCtes.add(cte);
            }
        }
        return repartoCtes;
    }

    //Conforma un comprobante
    @Transactional(rollbackFor = Exception.class)
    public RepartoComprobante conformarComprobante(RepartoComprobante repartoCte) {
        SeguimientoEstado seguimientoEstado = seguimientoEstadoDAO.findById(6).get();
        SeguimientoSituacion seguimientoSituacion = seguimientoSituacionDAO.findById(1).get();
        SeguimientoOrdenRecoleccion ordenSeguimiento = new SeguimientoOrdenRecoleccion();
        SeguimientoViajeRemito viajeSeguimiento = new SeguimientoViajeRemito();
        SeguimientoVentaComprobante ventaSeguimiento = new SeguimientoVentaComprobante();
        Timestamp fecha = new Timestamp(new java.util.Date().getTime());
        //Recorre la lista de reparto comprobante
        //Si no esta vacio, guarda el seguimiento para aquel comprobante que contenga el repartoComprobante
        if (repartoCte.getOrdenRecoleccion() != null) {
            ordenSeguimiento.setOrdenRecoleccion(repartoCte.getOrdenRecoleccion());
            ordenSeguimiento.setFecha(fecha);
            ordenSeguimiento.setSeguimientoEstado(seguimientoEstado);
            ordenSeguimiento.setSeguimientoSituacion(seguimientoSituacion);
            ordenSeguimiento.setSucursal(repartoCte.getReparto().getSucursal());
            seguimientoRecoleccionDAO.saveAndFlush(ordenSeguimiento);
        } else if (repartoCte.getViajeRemito() != null) {
            viajeSeguimiento.setViajeRemito(repartoCte.getViajeRemito());
            viajeSeguimiento.setFecha(fecha);
            viajeSeguimiento.setSeguimientoEstado(seguimientoEstado);
            viajeSeguimiento.setSeguimientoSituacion(seguimientoSituacion);
            viajeSeguimiento.setSucursal(repartoCte.getReparto().getSucursal());
            seguimientoRemitoDAO.saveAndFlush(viajeSeguimiento);
        } else {
            ventaSeguimiento.setVentaComprobante(repartoCte.getVentaComprobante());
            ventaSeguimiento.setFecha(fecha);
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
        SeguimientoEstado segEstado = seguimientoEstadoDAO.findById(3).get();
        Timestamp fecha = new Timestamp(new java.util.Date().getTime());
        Sucursal suc = ctes.get(0).getReparto().getSucursal();
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
                    ventaSeguimiento.setFecha(fecha);
                    ventaSeguimiento.setSeguimientoEstado(segEstado);
                    ventaSeguimiento.setSucursal(suc);
                    seguimientoComprobanteDAO.saveAndFlush(ventaSeguimiento);
                }
                //Consulta si viajeRemito es nulo para establecer el seguimiento y guardarlo
            } else if (cte.getViajeRemito() != null) {
                ViajeRemito r = viajeRemitoDAO.obtenerParaReparto(cte.getViajeRemito().getNumero(),
                        cte.getViajeRemito().getPuntoVenta(), cte.getViajeRemito().getLetra());
                if (r != null) {
                    viajeSeguimiento.setViajeRemito(cte.getViajeRemito());
                    cte.setViajeRemito(r);
                    viajeSeguimiento.setFecha(fecha);
                    viajeSeguimiento.setSeguimientoEstado(segEstado);
                    viajeSeguimiento.setSucursal(suc);
                    seguimientoRemitoDAO.saveAndFlush(viajeSeguimiento);
                }
                //Consulta si ordenRecoleccion es nulo para establecer el seguimiento y guardarlo
            } else if (cte.getOrdenRecoleccion() != null) {
                ordenSeguimiento.setOrdenRecoleccion(cte.getOrdenRecoleccion());
                ordenSeguimiento.setFecha(fecha);
                ordenSeguimiento.setSeguimientoEstado(segEstado);
                ordenSeguimiento.setSucursal(suc);
                seguimientoRecoleccionDAO.saveAndFlush(ordenSeguimiento);
            }
        }
        return repartoCtes;
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public int agregar(RepartoComprobante cte) {
        VentaComprobante v = null;
        ViajeRemito r = null;
        OrdenRecoleccion o = null;
        //Consulta si el repartocomprobante esta vacio, para no guardar un registro sin ninguno de los tres comprobantes
        RepartoComprobante rc = (cte.getOrdenRecoleccion() == null && cte.getViajeRemito() == null
                && cte.getVentaComprobante() == null ? null : cte);
        if(rc!=null) {
        //Consulta si ventaComprobante es nulo para establecer el seguimiento y guardarlo
        if (cte.getVentaComprobante() != null) {
            TipoComprobante tc = tipoComprobanteDAO.findById(cte.getVentaComprobante().getTipoComprobante().getId()).get();
            v = ventaComprobanteDAO.findByPuntoVentaAndLetraAndNumeroAndTipoComprobante(
                    cte.getVentaComprobante().getPuntoVenta(), cte.getVentaComprobante().getLetra(),
                    cte.getVentaComprobante().getNumero(), tc);
                cte.setVentaComprobante(v);
            //Consulta si viajeRemito es nulo para establecer el seguimiento y guardarlo
        } else if (cte.getViajeRemito() != null) {
            r = viajeRemitoDAO.obtenerPorPuntoVentaLetraYNumero(cte.getViajeRemito().getNumero(),
                        cte.getViajeRemito().getPuntoVenta(), cte.getViajeRemito().getLetra());
                cte.setViajeRemito(r);
            //Consulta si ordenRecoleccion es nulo para establecer el seguimiento y guardarlo
        } else {
            cte.setOrdenRecoleccion(cte.getOrdenRecoleccion());
            o = cte.getOrdenRecoleccion();
        }
        establecerSeguimiento(3, cte.getReparto().getSucursal().getId(), o, v, r);
        return elementoDAO.saveAndFlush(cte).getId();
        }
        return 0;
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
    
    //Establece el seguimiento
    private void establecerSeguimiento(int idSegEstado, int idSucursal, OrdenRecoleccion o,
            VentaComprobante v, ViajeRemito r) {
        SeguimientoOrdenRecoleccion ordenSeguimiento = new SeguimientoOrdenRecoleccion();
        SeguimientoViajeRemito viajeSeguimiento = new SeguimientoViajeRemito();
        SeguimientoVentaComprobante ventaSeguimiento = new SeguimientoVentaComprobante();
        SeguimientoEstado seguimiento = seguimientoEstadoDAO.findById(idSegEstado).get();
        Sucursal sucursal = sucursalDAO.findById(idSucursal).get();
        Timestamp fecha = new Timestamp(new java.util.Date().getTime());
        if (v != null) {
                ventaSeguimiento.setVentaComprobante(v);
                ventaSeguimiento.setFecha(fecha);
                ventaSeguimiento.setSeguimientoEstado(seguimiento);
                ventaSeguimiento.setSucursal(sucursal);
                seguimientoComprobanteDAO.saveAndFlush(ventaSeguimiento);
            //Consulta si viajeRemito es nulo para establecer el seguimiento y guardarlo
        } else if (r != null) {
                viajeSeguimiento.setViajeRemito(r);
                viajeSeguimiento.setFecha(fecha);
                viajeSeguimiento.setSeguimientoEstado(seguimiento);
                viajeSeguimiento.setSucursal(sucursal);
                seguimientoRemitoDAO.saveAndFlush(viajeSeguimiento);
            //Consulta si ordenRecoleccion es nulo para establecer el seguimiento y guardarlo
        } else if (o != null) {
            ordenSeguimiento.setOrdenRecoleccion(o);
            ordenSeguimiento.setFecha(fecha);
            ordenSeguimiento.setSeguimientoEstado(seguimiento);
            ordenSeguimiento.setSucursal(sucursal);
            seguimientoRecoleccionDAO.saveAndFlush(ordenSeguimiento);
        }
    }
    
    //Retorna un object aplicando los filtros
    private Object aplicarFiltros(List<RepartoComprobante> elementos) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
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
        String string = mapper.writer(filters).writeValueAsString(elementos);
        return new ObjectMapper().readValue(string, Object.class);
    }

}
