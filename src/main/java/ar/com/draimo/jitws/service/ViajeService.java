//Paquete al que petenece el servicio
package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IEmpresaDAO;
import ar.com.draimo.jitws.dao.IPersonalDAO;
import ar.com.draimo.jitws.dao.ISucursalDAO;
import ar.com.draimo.jitws.model.Viaje;
import ar.com.draimo.jitws.model.ViajeTramo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ar.com.draimo.jitws.dao.IViajeCombustibleDAO;
import ar.com.draimo.jitws.dao.IViajeDAO;
import ar.com.draimo.jitws.dao.IViajeEfectivoDAO;
import ar.com.draimo.jitws.dao.IViajeGastoDAO;
import ar.com.draimo.jitws.dao.IViajeInsumoDAO;
import ar.com.draimo.jitws.dao.IViajePeajeDAO;
import ar.com.draimo.jitws.dao.IViajeTramoClienteDAO;
import ar.com.draimo.jitws.dao.IViajeTramoDAO;
import ar.com.draimo.jitws.dto.InitViajeDTO;
import ar.com.draimo.jitws.dto.ViajeFiltroDTO;
import ar.com.draimo.jitws.model.Empresa;
import ar.com.draimo.jitws.model.Personal;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import java.io.IOException;

/**
 * Servicio Viaje
 *
 * @author blas
 */
@Service
public class ViajeService {

    //Define la referencia al dao
    @Autowired
    IViajeDAO elementoDAO;

    //Define la referencia al dao personal
    @Autowired
    IPersonalDAO personalDAO;

    //Define la referencia al dao empresa
    @Autowired
    IEmpresaDAO empresaDAO;

    //Define la referencia al dao viaje tramo
    @Autowired
    IViajeTramoDAO viajeTramoDAO;

    //Define la referencia al dao viaje tramo cliente
    @Autowired
    IViajeTramoClienteDAO viajeTramoClienteDAO;

    //Define la referencia al dao viaje combustible
    @Autowired
    IViajeCombustibleDAO viajeCombustibleDAO;

    //Define la referencia al dao viaje efectivo
    @Autowired
    IViajeEfectivoDAO viajeEfectivoDAO;

    //Define la referencia al dao viaje insumo
    @Autowired
    IViajeInsumoDAO viajeInsumoDAO;

    //Define la referencia al dao viaje gasto
    @Autowired
    IViajeGastoDAO viajeGastoDAO;

    //Define la referencia al dao viaje peaje
    @Autowired
    IViajePeajeDAO viajePeajeDAO;

    //Define la referencia al dao sucursal
    @Autowired
    ISucursalDAO sucursalDAO;

    //Define la referencia al service de ViajeTramo
    @Autowired
    ViajeTramoService viajeTramoService;
    
    //Referencia al service de subopcionpestania
    @Autowired
    SubopcionPestaniaService subopcionPestaniaService;
    
    //Obtiene listas necesarias para inicializar el componente (front)
    public InitViajeDTO inicializar(int idRol, int idSubopcion) {
        InitViajeDTO elemento = new InitViajeDTO();
        elemento.setPestanias(subopcionPestaniaService.listarPestaniasPorRolYSubopcion(idRol, idSubopcion));
        elemento.setUltimoId(obtenerSiguienteId());
        elemento.setSucursales(sucursalDAO.findAll());
        return elemento;
    }

    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        Viaje elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId() + 1 : 1;
    }

    //Obtiene la lista completa
    public Object listar() throws IOException {
        List<Viaje> viajes = elementoDAO.obtenerTodos();
        return retornarObjeto(viajes, null);
    }

    //Obtiene por id
    public Object obtenerPorId(int id) throws IOException {
        //Obtiene un viaje propio por id
        Viaje viaje = elementoDAO.obtenerViaje(id);
//        //Obtiene la lista de tramos del viaje
//        List<ViajeTramo> viajePropioTramos = viajeTramoDAO.findByViaje(viaje);
//        viaje.setViajeTramos(viajePropioTramos);
//        //Obtiene la lista de ordenes de combustible del viaje
//        List<ViajeCombustible> viajePropioCombustibles = viajeCombustibleDAO.findByViaje(viaje);
//        viaje.setViajeCombustibles(viajePropioCombustibles);
//        //Obtiene la lista de adelantos de efectivo del viaje
//        List<ViajeEfectivo> viajePropioEfectivos = viajeEfectivoDAO.findByViaje(viaje);
//        viaje.setViajeEfectivos(viajePropioEfectivos);
//        //Obtiene la lista de ordenes de insumo del viaje
//        List<ViajeInsumo> viajePropioInsumos = viajeInsumoDAO.findByViaje(viaje);
//        viaje.setViajeInsumos(viajePropioInsumos);
//        //Obtiene la lista de gastos del viaje
//        List<ViajeGasto> viajePropioGasto = viajeGastoDAO.findByViaje(viaje);
//        viaje.setViajeGastos(viajePropioGasto);
//        //Obtiene la lista de peajes del viaje
//        List<ViajePeaje> viajePropioPeaje = viajePeajeDAO.findByViaje(viaje);
//        viaje.setViajePeajes(viajePropioPeaje);
        //Retorna los datos
        return retornarObjeto(null, viaje);
    }

    //Obtiene una lista de registros por filtros
    public Object listarPorFiltros(ViajeFiltroDTO dto) throws IOException {
        List<Viaje> viajes = elementoDAO.listarPorFiltros(dto.getIdViaje(), dto.getFechaDesde(),
                dto.getFechaHasta(), dto.getIdPersonal(), dto.getIdProveedor());
        return retornarObjeto(viajes, null);
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public Object agregar(Viaje elemento) throws IOException {
        ViajeTramo vTramo;
        elemento = formatearStrings(elemento);
        elemento = elementoDAO.saveAndFlush(elemento);
        List<ViajeTramo> viajeTramos = elemento.getViajeTramos();
        for (ViajeTramo viajeTramo : viajeTramos) {
            if (viajeTramo != null) {
                if (viajeTramo.getId() == 0) {
                    vTramo = viajeTramoService.formatearStrings(viajeTramo);
                    vTramo.setViaje(elemento);
                    vTramo = viajeTramoDAO.saveAndFlush(vTramo);
                }
            }
        }
        return retornarObjeto(null, elemento);
    }

    //Establece el alias de un registro
    @Transactional(rollbackFor = Exception.class)
    public Viaje establecerAlias(Viaje elemento) {
        Empresa e = empresaDAO.findById(elemento.getEmpresaEmision().getId()).get();
        Personal p = personalDAO.findById(elemento.getPersonal().getId()).get();
        elemento.setAlias(elemento.getId() + " - " + elemento.getFecha()
                + " - " + e.getRazonSocial() + " - " + p.getNombreCompleto());
        return elementoDAO.save(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public Object actualizar(Viaje viaje) throws IOException {
        //Formatea los strings, establece el alias y actualiza
        viaje = establecerAlias(formatearStrings(viaje));
        return retornarObjeto(null, viaje);
    }

    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }

    //Formatea los strings
    public Viaje formatearStrings(Viaje elemento) {
        if (elemento.getObservacionVehiculo() != null) {
            elemento.setObservacionVehiculo(elemento.getObservacionVehiculo().trim());
        }
        if (elemento.getObservacionVehiculoRemolque() != null) {
            elemento.setObservacionVehiculoRemolque(elemento.getObservacionVehiculoRemolque().trim());
        }
        if (elemento.getObservacionChofer() != null) {
            elemento.setObservacionChofer(elemento.getObservacionChofer().trim());
        }
        if (elemento.getObservaciones() != null) {
            elemento.setObservaciones(elemento.getObservaciones().trim());
        }
        return elemento;
    }
     
    //Convierte una lista o un elemento a object para retornar con filtros aplicados
    private Object retornarObjeto(List<Viaje> elementos, Viaje elemento) throws IOException {
    ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("cliente", "viajeTramo", "datos", "hijos", "viaje", "ventaComprobante");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("viajetramofiltro", theFilter)
                .addFilter("viajefiltro", theFilter)
                .addFilter("filtroPdf", theFilter).addFilter("filtroFoto", theFilter)
                .addFilter("viajetramoclientefiltro", theFilter)
                .addFilter("clientefiltro", theFilter)
                .addFilter("filtroVentaComprobanteItemCR", theFilter)
                .addFilter("filtroPlanCuenta", theFilter);
        String string = mapper.writer(filters).writeValueAsString(elementos!=null ? elementos : elemento);
        return mapper.readValue(string, Object.class);
    }

}
