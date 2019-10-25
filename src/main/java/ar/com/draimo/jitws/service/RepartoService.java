//Paquete al que pertenece la clase
package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IPersonalDAO;
import ar.com.draimo.jitws.dao.IProveedorDAO;
import ar.com.draimo.jitws.model.Reparto;
import ar.com.draimo.jitws.model.RepartoComprobante;
import ar.com.draimo.jitws.model.RepartoPersonal;
import java.sql.Timestamp;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ar.com.draimo.jitws.dao.IRepartoComprobanteDAO;
import ar.com.draimo.jitws.dao.IRepartoDAO;
import ar.com.draimo.jitws.dao.IRepartoPersonalDAO;
import ar.com.draimo.jitws.dao.ISeguimientoEstadoDAO;
import ar.com.draimo.jitws.dao.ISeguimientoOrdenRecoleccionDAO;
import ar.com.draimo.jitws.dao.ISeguimientoSituacionDAO;
import ar.com.draimo.jitws.dao.ISeguimientoVentaComprobanteDAO;
import ar.com.draimo.jitws.dao.ISeguimientoViajeRemitoDAO;
import ar.com.draimo.jitws.dao.ISucursalDAO;
import ar.com.draimo.jitws.dao.ITipoComprobanteDAO;
import ar.com.draimo.jitws.dao.IViajeCombustibleDAO;
import ar.com.draimo.jitws.dao.IViajeEfectivoDAO;
import ar.com.draimo.jitws.dto.elementoDTO;
import ar.com.draimo.jitws.exception.CodigoRespuesta;
import ar.com.draimo.jitws.model.Proveedor;
import ar.com.draimo.jitws.model.SeguimientoEstado;
import ar.com.draimo.jitws.model.SeguimientoOrdenRecoleccion;
import ar.com.draimo.jitws.model.SeguimientoSituacion;
import ar.com.draimo.jitws.model.SeguimientoVentaComprobante;
import ar.com.draimo.jitws.model.SeguimientoViajeRemito;
import ar.com.draimo.jitws.model.Sucursal;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import java.io.IOException;
import org.springframework.dao.DataIntegrityViolationException;

/**
 * Servicio RepartoPropio
 *
 * @author blas
 */
@Service
public class RepartoService {

    //Define la referencia al dao
    @Autowired
    IRepartoDAO elementoDAO;

    //define la referencia al dao de RepartoPersonal
    @Autowired
    IRepartoPersonalDAO repartoPersonalDAO;

    //define la referencia al dao de Personal
    @Autowired
    IPersonalDAO personalDAO;

    //define la referencia al dao de proveedor
    @Autowired
    IProveedorDAO proveedorDAO;

    //define la referencia al dao de RepartoComprobante
    @Autowired
    IRepartoComprobanteDAO repartoComprobanteDAO;

    //define la referencia al dao de tipoComprobante
    @Autowired
    ITipoComprobanteDAO tipoComprobanteDAO;

    //define la referencia al dao de seguimientoVentaCte
    @Autowired
    ISeguimientoVentaComprobanteDAO seguimientoVtaCteDAO;

    //define la referencia al dao de seguimientoOrdenRecoleccion
    @Autowired
    ISeguimientoOrdenRecoleccionDAO seguimientoOrdenRecDAO;

    //define la referencia al dao de seguimientoViajeRto
    @Autowired
    ISeguimientoViajeRemitoDAO seguimientoViajeRtoDAO;

    //define la referencia al dao de seguimientoEstado
    @Autowired
    ISeguimientoEstadoDAO seguimientoEstadoDAO;

    //define la referencia al dao de seguimientoSituacion
    @Autowired
    ISeguimientoSituacionDAO seguimientoSituacionDAO;

    //define la referencia al dao de viajeCombustible
    @Autowired
    IViajeCombustibleDAO viajeCombustibleDAO;

    //define la referencia al dao de viajeEfectivo
    @Autowired
    IViajeEfectivoDAO viajeEfectivoDAO;

    //define la referencia al dao de sucursal
    @Autowired
    ISucursalDAO sucursalDAO;

    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        Reparto elemento = elementoDAO.findTopByOrderByIdDesc();
        return (elemento != null ? elemento.getId() + 1 : 1);
    }

    //Obtiene la lista completa
    public Object listar() throws IOException {
        List<Reparto> elementos = elementoDAO.findAll();
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("datos", "hijos", "ventaComprobante");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("filtroPdf", theFilter)
                .addFilter("filtroPlanCuenta", theFilter)
                .addFilter("filtroFoto", theFilter)
                .addFilter("filtroVentaComprobanteItemCR", theFilter);
        String string = mapper.writer(filters).writeValueAsString(elementos);
        return mapper.readValue(string, Object.class);
    }

    //Obtiene la lista completa
    public Object obtenerPorId(int id) throws IOException {
        Reparto elemento = elementoDAO.obtenerPorId(id);
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("datos", "hijos", "ventaComprobante");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("filtroPdf", theFilter)
                .addFilter("filtroPlanCuenta", theFilter)
                .addFilter("filtroFoto", theFilter)
                .addFilter("filtroVentaComprobanteItemCR", theFilter);
        String string = mapper.writer(filters).writeValueAsString(elemento);
        return mapper.readValue(string, Object.class);
    }

    //Obtiene la lista de registros propios abiertos
    public Object listarAbiertosPropios() throws IOException {
        List<Reparto> elementos = elementoDAO.listarPorEstaCerradaYReparto(false, true);
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("datos", "hijos", "ventaComprobante");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("filtroPdf", theFilter)
                .addFilter("filtroPlanCuenta", theFilter)
                .addFilter("filtroFoto", theFilter)
                .addFilter("filtroVentaComprobanteItemCR", theFilter);
        String string = mapper.writer(filters).writeValueAsString(elementos);
        return mapper.readValue(string, Object.class);
    }

    //Obtiene la lista de registros terceros abiertos
    public Object listarAbiertosTerceros() throws IOException {
        List<Reparto> elementos = elementoDAO.listarPorEstaCerradaYReparto(false, false);
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("datos", "hijos", "ventaComprobante");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("filtroPdf", theFilter)
                .addFilter("filtroPlanCuenta", theFilter)
                .addFilter("filtroFoto", theFilter)
                .addFilter("filtroVentaComprobanteItemCR", theFilter);
        String string = mapper.writer(filters).writeValueAsString(elementos);
        return mapper.readValue(string, Object.class);
    }

    //Obtiene la lista por EstaCerrada 
    public Object listarPorEstaCerrada(boolean estaCerrada) throws IOException {
        List<Reparto> elementos = elementoDAO.listarPorEstaCerradaYEmpresa(estaCerrada, 0);
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("datos", "hijos", "ventaComprobante");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("filtroPdf", theFilter)
                .addFilter("filtroPlanCuenta", theFilter)
                .addFilter("filtroFoto", theFilter)
                .addFilter("filtroVentaComprobanteItemCR", theFilter);
        String string = mapper.writer(filters).writeValueAsString(elementos);
        return mapper.readValue(string, Object.class);
    }

    //Obtiene la lista por EstaCerrada y empresa
    public Object listarPorEstaCerradaYEmpresa(boolean estaCerrada, int idEmpresa) throws IOException {
        List<Reparto> elementos = elementoDAO.listarPorEstaCerradaYEmpresa(estaCerrada, idEmpresa);
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("datos", "hijos", "ventaComprobante");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("filtroPdf", theFilter)
                .addFilter("filtroPlanCuenta", theFilter)
                .addFilter("filtroFoto", theFilter)
                .addFilter("filtroVentaComprobanteItemCR", theFilter);
        String string = mapper.writer(filters).writeValueAsString(elementos);
        return mapper.readValue(string, Object.class);
    }

    //Obtiene la lista por filtros
    public Object listarPorFiltros(elementoDTO repartoDto) throws IOException {
        List<Reparto> elementos = elementoDAO.listarPorFiltros(
                repartoDto.isEsRepartoPropio(), repartoDto.getFechaDesde(),
                repartoDto.getFechaHasta(), repartoDto.getIdChofer(),
                repartoDto.isEstaCerrada(), repartoDto.getIdEmpresa());
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("datos", "hijos", "ventaComprobante");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("filtroPdf", theFilter)
                .addFilter("filtroPlanCuenta", theFilter)
                .addFilter("filtroFoto", theFilter)
                .addFilter("filtroVentaComprobanteItemCR", theFilter);
        String string = mapper.writer(filters).writeValueAsString(elementos);
        return mapper.readValue(string, Object.class);
    }

    //Cierra un reparto
    @Transactional(rollbackFor = Exception.class)
    public boolean cerrarReparto(Reparto reparto) {
        if (reparto.getRepartoComprobantes().isEmpty()) {
            return false;
        } else {
            Sucursal sucursal = sucursalDAO.findById(reparto.getSucursal().getId()).get();
            SeguimientoEstado se = seguimientoEstadoDAO.findById(4).get();
            SeguimientoSituacion ss = seguimientoSituacionDAO.findById(1).get();
            Timestamp fecha = new Timestamp(new java.util.Date().getTime());
            for (RepartoComprobante rtoCte : reparto.getRepartoComprobantes()) {
                if (rtoCte.getOrdenRecoleccion() != null) {
                    SeguimientoOrdenRecoleccion sor = new SeguimientoOrdenRecoleccion();
                    sor.setOrdenRecoleccion(rtoCte.getOrdenRecoleccion());
                    sor.setSeguimientoEstado(se);
                    sor.setSeguimientoSituacion(ss);
                    sor.setFecha(fecha);
                    sor.setSucursal(sucursal);
                    seguimientoOrdenRecDAO.saveAndFlush(sor);
                } else if (rtoCte.getVentaComprobante() != null) {
                    SeguimientoVentaComprobante svc = new SeguimientoVentaComprobante();
                    svc.setVentaComprobante(rtoCte.getVentaComprobante());
                    svc.setSeguimientoEstado(se);
                    svc.setSeguimientoSituacion(ss);
                    svc.setFecha(fecha);
                    svc.setSucursal(sucursal);
                    seguimientoVtaCteDAO.saveAndFlush(svc);
                } else if (rtoCte.getViajeRemito() != null) {
                    SeguimientoViajeRemito svr = new SeguimientoViajeRemito();
                    svr.setViajeRemito(rtoCte.getViajeRemito());
                    svr.setSeguimientoEstado(se);
                    svr.setSeguimientoSituacion(ss);
                    svr.setFecha(fecha);
                    svr.setSucursal(sucursal);
                    seguimientoViajeRtoDAO.saveAndFlush(svr);
                } else {
                    throw new DataIntegrityViolationException(String.valueOf(CodigoRespuesta.SIN_COMPROBANTES));
                }
            }
            Reparto r = new Reparto();
            r = reparto;
            r.setEstaCerrada(true);
            elementoDAO.save(r);
            return true;
        }
    }

    //Abre un reparto
    @Transactional(rollbackFor = Exception.class)
    public boolean abrirReparto(int idReparto) {
        Reparto reparto = elementoDAO.obtenerPorId(idReparto);
        if (reparto.getRepartoComprobantes().isEmpty()) {
            return false;
        } else {
            Sucursal sucursal = sucursalDAO.findById(reparto.getSucursal().getId()).get();
            SeguimientoEstado se = seguimientoEstadoDAO.findById(3).get();
            SeguimientoSituacion ss = seguimientoSituacionDAO.findById(1).get();
            Timestamp fecha = new Timestamp(new java.util.Date().getTime());
            for (RepartoComprobante rtoCte : reparto.getRepartoComprobantes()) {
                if (rtoCte.getOrdenRecoleccion() != null) {
                    SeguimientoOrdenRecoleccion sor = new SeguimientoOrdenRecoleccion();
                    sor.setOrdenRecoleccion(rtoCte.getOrdenRecoleccion());
                    sor.setSeguimientoEstado(se);
                    sor.setSeguimientoSituacion(ss);
                    sor.setFecha(fecha);
                    sor.setSucursal(sucursal);
                    seguimientoOrdenRecDAO.saveAndFlush(sor);
                } else if (rtoCte.getVentaComprobante() != null) {
                    SeguimientoVentaComprobante svc = new SeguimientoVentaComprobante();
                    svc.setVentaComprobante(rtoCte.getVentaComprobante());
                    svc.setSeguimientoEstado(se);
                    svc.setSeguimientoSituacion(ss);
                    svc.setFecha(fecha);
                    svc.setSucursal(sucursal);
                    seguimientoVtaCteDAO.saveAndFlush(svc);
                } else if (rtoCte.getViajeRemito() != null) {
                    SeguimientoViajeRemito svr = new SeguimientoViajeRemito();
                    svr.setViajeRemito(rtoCte.getViajeRemito());
                    svr.setSeguimientoEstado(se);
                    svr.setSeguimientoSituacion(ss);
                    svr.setFecha(fecha);
                    svr.setSucursal(sucursal);
                    seguimientoViajeRtoDAO.saveAndFlush(svr);
                } else {
                    throw new DataIntegrityViolationException(String.valueOf(CodigoRespuesta.SIN_COMPROBANTES));
                }
            }
            Reparto r = new Reparto();
            r = reparto;
            r.setEstaCerrada(false);
            elementoDAO.save(r);
            return true;
        }
    }

    //Recibe un reparto
    public Reparto recibirReparto(Reparto elemento) {
        elemento.setEstaCerrada(false);
        return elementoDAO.save(elemento);
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public int agregar(Reparto elemento) throws IOException {
        Timestamp fecha = new Timestamp(new java.util.Date().getTime());
        elemento.setFechaRegistracion(fecha);
        elemento.setEstaCerrada(false);
        elemento.setTipoComprobante(tipoComprobanteDAO.findById(12).get());
        if (elemento.getVehiculoProveedor() != null) {
            Proveedor p = proveedorDAO.findById(elemento.getVehiculoProveedor().getProveedor().getId()).get();
            elemento.setProveedor(p);
            elemento.setAfipCondicionIvaProveedor(p.getAfipCondicionIva());
        }
        elementoDAO.saveAndFlush(elemento);
        if (!elemento.getAcompaniantes().isEmpty()) {
            for (RepartoPersonal acompaniante : elemento.getAcompaniantes()) {
                acompaniante.setPersonal(personalDAO.obtenerPorId(acompaniante.getPersonal().getId()));
                acompaniante.setReparto(elemento);
                repartoPersonalDAO.saveAndFlush(acompaniante);
            }
        }
        return elemento.getId();
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(Reparto elemento) {
        elementoDAO.save(elemento);
    }

    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public boolean eliminar(int elemento) {
        Reparto r = elementoDAO.obtenerPorId(elemento);
        if (r.getRepartoComprobantes().isEmpty()) {
            viajeEfectivoDAO.deleteByReparto(r);
            viajeCombustibleDAO.deleteByReparto(r);
            repartoPersonalDAO.deleteByReparto(r);
            elementoDAO.deleteById(elemento);
            return true;
        }else{
            return false;
        }
    }
}
