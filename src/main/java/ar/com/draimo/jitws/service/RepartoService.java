package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IPersonalDAO;
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
import ar.com.draimo.jitws.dao.ITipoComprobanteDAO;
import ar.com.draimo.jitws.dao.IViajeCombustibleDAO;
import ar.com.draimo.jitws.dao.IViajeEfectivoDAO;
import ar.com.draimo.jitws.model.OrdenRecoleccion;
import ar.com.draimo.jitws.model.SeguimientoEstado;
import ar.com.draimo.jitws.model.SeguimientoOrdenRecoleccion;
import ar.com.draimo.jitws.model.SeguimientoSituacion;
import ar.com.draimo.jitws.model.SeguimientoVentaComprobante;
import ar.com.draimo.jitws.model.SeguimientoViajeRemito;
import ar.com.draimo.jitws.model.ViajeCombustible;
import ar.com.draimo.jitws.model.ViajeEfectivo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDateTime;
import org.springframework.dao.DataIntegrityViolationException;

/**
 * Servicio RepartoPropio
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
    
    //define la referencia al dao de RepartoPropioComprobante
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
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        Reparto elemento = elementoDAO.findTopByOrderByIdDesc();
        return (elemento!=null? elemento.getId()+1 : 1);
    }
    
    //Obtiene la lista completa
    public Object listar() throws IOException {
         List<Reparto> elementos = elementoDAO.findAll();
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("datos","hijos");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("filtroPdf", theFilter)
                .addFilter("filtroPlanCuenta", theFilter)
                .addFilter("filtroFoto", theFilter);
        String string = mapper.writer(filters).writeValueAsString(elementos);
        return mapper.readValue(string, Object.class);
    }
    
    
    //Obtiene la lista de registros propios abiertos
    public Object listarAbiertosPropios() throws IOException {
         List<Reparto> elementos = elementoDAO.listarPorEstaCerradaYReparto(false, true);
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("datos","hijos");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("filtroPdf", theFilter)
                .addFilter("filtroPlanCuenta", theFilter)
                .addFilter("filtroFoto", theFilter);
        String string = mapper.writer(filters).writeValueAsString(elementos);
        return mapper.readValue(string, Object.class);
    }
    
    
    //Obtiene la lista de registros terceros abiertos
    public Object listarAbiertosTerceros() throws IOException {
        List<Reparto> elementos = elementoDAO.listarPorEstaCerradaYReparto(false, false);
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("datos","hijos");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("filtroPdf", theFilter)
                .addFilter("filtroPlanCuenta", theFilter)
                .addFilter("filtroFoto", theFilter);
        String string = mapper.writer(filters).writeValueAsString(elementos);
        return mapper.readValue(string, Object.class);
    }
    
    //Obtiene la lista por EstaCerrada 
    public Object listarPorEstaCerrada(boolean estaCerrada) throws IOException {
        List<Reparto> elementos = elementoDAO.listarPorEstaCerrada(estaCerrada);
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("datos","hijos");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("filtroPdf", theFilter)
                .addFilter("filtroPlanCuenta", theFilter)
                .addFilter("filtroFoto", theFilter);
        String string = mapper.writer(filters).writeValueAsString(elementos);
        return mapper.readValue(string, Object.class);
    }
    
    //Obtiene la lista por EstaCerrada 
    public List<Reparto> listarPorEstaCerradaYEmpresa(boolean estaCerrada, int idEmpresa) {
        return elementoDAO.listarPorEstaCerradaYEmpresa(estaCerrada, idEmpresa);
    }
    
    //Obtiene la lista por filtros
    public Object listarPorFiltros(int idEmpresa, boolean tipoViaje, 
            Date fechaDesde, Date fechaHasta, int idChofer,boolean estaCerrada) throws IOException {
        List<Reparto> elementos = elementoDAO.listarPorFiltros(tipoViaje,fechaDesde,fechaHasta,idChofer,estaCerrada,idEmpresa);
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("datos","hijos");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("filtroPdf", theFilter)
                .addFilter("filtroPlanCuenta", theFilter)
                .addFilter("filtroFoto", theFilter);
        String string = mapper.writer(filters).writeValueAsString(elementos);
        return mapper.readValue(string, Object.class);
    }
    
    //Cierra un reparto
    public boolean cerrarReparto(int idReparto) {
        Reparto r = elementoDAO.findById(idReparto).get();
        List<RepartoComprobante> c = repartoComprobanteDAO.findByReparto(r);
        if (c.isEmpty()) {
            return false;
        }else {
            SeguimientoEstado se = seguimientoEstadoDAO.findById(4).get();
            SeguimientoSituacion ss = seguimientoSituacionDAO.findById(1).get();
            r.setEstaCerrada(true);
            LocalDateTime fecha = LocalDateTime.now();
            for (RepartoComprobante rtoCte : c) {
                if (rtoCte.getOrdenRecoleccion()!=null) {
                    SeguimientoOrdenRecoleccion sor = new SeguimientoOrdenRecoleccion();
                    sor.getOrdenRecoleccion().setId(rtoCte.getOrdenRecoleccion().getId());
                    sor.setSeguimientoEstado(se);
                    sor.setSeguimientoSituacion(ss);
                    sor.setFecha(fecha);
                    sor.setSucursal(r.getSucursal());
                    seguimientoOrdenRecDAO.saveAndFlush(sor);
                }else if(rtoCte.getVentaComprobante()!=null){
                    SeguimientoVentaComprobante svc = new SeguimientoVentaComprobante();
                    svc.getVentaComprobante().setId(rtoCte.getVentaComprobante().getId());
                    svc.setSeguimientoEstado(se);
                    svc.setSeguimientoSituacion(ss);
                    svc.setFecha(fecha);
                    svc.setSucursal(r.getSucursal());
                    seguimientoVtaCteDAO.saveAndFlush(svc);
                }else if(rtoCte.getViajeRemito()!=null) {
                    SeguimientoViajeRemito svr = new SeguimientoViajeRemito();
                    svr.getViajeRemito().setId(rtoCte.getViajeRemito().getId());
                    svr.setSeguimientoEstado(se);
                    svr.setSeguimientoSituacion(ss);
                    svr.setFecha(fecha);
                    svr.setSucursal(r.getSucursal());
                    seguimientoViajeRtoDAO.saveAndFlush(svr);
                }else {
                    throw new DataIntegrityViolationException("No contiene comprobante/s");
                }
            }
            elementoDAO.save(r);
            return true;
        }
    }
    
    //Abre un reparto
    public boolean abrirReparto(int idReparto) {
        Reparto r = elementoDAO.findById(idReparto).get();
        List<RepartoComprobante> c = repartoComprobanteDAO.findByReparto(r);
            r.setEstaCerrada(false);
            SeguimientoEstado se = seguimientoEstadoDAO.findById(3).get();
            SeguimientoSituacion ss = seguimientoSituacionDAO.findById(1).get();
            r.setEstaCerrada(true);
            LocalDateTime fecha = LocalDateTime.now();
            for (RepartoComprobante rtoCte : c) {
                if (rtoCte.getOrdenRecoleccion()!=null) {
                    SeguimientoOrdenRecoleccion sor = new SeguimientoOrdenRecoleccion();
                    sor.getOrdenRecoleccion().setId(rtoCte.getOrdenRecoleccion().getId());
                    sor.setSeguimientoEstado(se);
                    sor.setSeguimientoSituacion(ss);
                    sor.setFecha(fecha);
                    sor.setSucursal(r.getSucursal());
                    seguimientoOrdenRecDAO.saveAndFlush(sor);
                }else if(rtoCte.getVentaComprobante()!=null){
                    SeguimientoVentaComprobante svc = new SeguimientoVentaComprobante();
                    svc.getVentaComprobante().setId(rtoCte.getVentaComprobante().getId());
                    svc.setSeguimientoEstado(se);
                    svc.setSeguimientoSituacion(ss);
                    svc.setFecha(fecha);
                    svc.setSucursal(r.getSucursal());
                    seguimientoVtaCteDAO.saveAndFlush(svc);
                }else if(rtoCte.getViajeRemito()!=null) {
                    SeguimientoViajeRemito svr = new SeguimientoViajeRemito();
                    svr.getViajeRemito().setId(rtoCte.getViajeRemito().getId());
                    svr.setSeguimientoEstado(se);
                    svr.setSeguimientoSituacion(ss);
                    svr.setFecha(fecha);
                    svr.setSucursal(r.getSucursal());
                    seguimientoViajeRtoDAO.saveAndFlush(svr);
                }else {
                    throw new DataIntegrityViolationException("No contiene comprobante/s");
                }
            }
            elementoDAO.save(r);
            return true;
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public int agregar(Reparto elemento) throws IOException {
        Timestamp fecha = new Timestamp(new java.util.Date().getTime());
        elemento.setFechaRegistracion(fecha);
        elemento.setEstaCerrada(false);
        elemento.setTipoComprobante(tipoComprobanteDAO.findById(12).get());
        if(elemento.getChoferProveedor()!=null){
            elemento.setProveedor(elemento.getChoferProveedor().getProveedor());
            elemento.setAfipCondicionIvaProveedor(elemento.getChoferProveedor().getProveedor().getAfipCondicionIva());
        }
        elementoDAO.saveAndFlush(elemento);
        if (!elemento.getAcompaniantes().isEmpty()) {
            for (RepartoPersonal acompaniante : elemento.getAcompaniantes()) {
                acompaniante.setPersonal(personalDAO.findById(acompaniante.getId()).get());
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
        Reparto r = elementoDAO.findById(elemento).get();
        List<RepartoComprobante> rctes = repartoComprobanteDAO.findByReparto(r);
        List<ViajeEfectivo> vefectivos = viajeEfectivoDAO.findByReparto(r);
        List<ViajeCombustible> vCbles = viajeCombustibleDAO.findByReparto(r);
        if(rctes.isEmpty()){
            elementoDAO.deleteById(elemento);
            return true;
        }else {
            return false;
        }
    }

}
