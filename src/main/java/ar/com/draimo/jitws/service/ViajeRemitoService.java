package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.ISucursalDAO;
import ar.com.draimo.jitws.dao.IViajeRemitoDAO;
import ar.com.draimo.jitws.dto.ViajeRemitoDTO;
import ar.com.draimo.jitws.model.Sucursal;
import ar.com.draimo.jitws.model.ViajeRemito;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ar.com.draimo.jitws.dao.IViajeTramoDAO;
import ar.com.draimo.jitws.dao.IViajeTramoRemitoDAO;
import ar.com.draimo.jitws.model.ViajeTramo;
import ar.com.draimo.jitws.model.ViajeTramoRemito;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import java.io.IOException;
import org.springframework.dao.DataIntegrityViolationException;

/**
 * Servicio de ViajePropio
 *
 * @author blas
 */
@Service
public class ViajeRemitoService {

    //Define la referencia al dao
    @Autowired
    IViajeRemitoDAO elementoDAO;

    //Define la referencia al dao sucursal
    @Autowired
    ISucursalDAO sucursalDAO;

    //Define la referencia al dao viaje propio tramo
    @Autowired
    IViajeTramoDAO viajeTramoDAO;

    //Define la referencia al dao viaje remito tramo
    @Autowired
    IViajeTramoRemitoDAO viajeTramoRemitoDAO;

    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        ViajeRemito elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId() + 1 : 1;
    }

    //Obtiene el listado completo
    public Object listar() throws IOException {
        List<ViajeRemito> remitos = elementoDAO.findAll();
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("ordenesVentas");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("clientefiltro", theFilter);
        String string = mapper.writer(filters).writeValueAsString(remitos);
        return mapper.readValue(string, Object.class);
    }

    //Obtiene el listado de remitos disponibles
    public Object listarRemitosDisponibles() throws IOException {
        List<ViajeRemito> remitos = elementoDAO.listarRemitosDisponibles();
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("ordenesVentas");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("clientefiltro", theFilter);
        String string = mapper.writer(filters).writeValueAsString(remitos);
        return mapper.readValue(string, Object.class);
    }

    //Obtiene una lista por alias
    public Object listarPorAlias(String alias) throws IOException {
        List<ViajeRemito> remitos;
        if (alias.equals("***")) {
            remitos = elementoDAO.findAll();
        } else {
            remitos = elementoDAO.findByAliasContaining(alias);
        }
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("ordenesVentas");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("clientefiltro", theFilter);
        String string = mapper.writer(filters).writeValueAsString(remitos);
        return mapper.readValue(string, Object.class);
    }

    //Obtiene un listado por numero de comprobante
    public Object listarPorNumero(int numero) throws IOException {
        List<ViajeRemito> remitos = elementoDAO.findByNumero(numero);
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("ordenesVentas");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("clientefiltro", theFilter);
        String string = mapper.writer(filters).writeValueAsString(remitos);
        return mapper.readValue(string, Object.class);
    }

    //Obtiene un listado de no pendientes por viajeTramo
    public Object listarAsignadosPorViajeTramo(int idViajeTramo) throws IOException {
        List<ViajeRemito> remitos = elementoDAO.listarAsignadosPorViajeTramo(idViajeTramo);
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("ordenesVentas");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("clientefiltro", theFilter);
        String string = mapper.writer(filters).writeValueAsString(remitos);
        return mapper.readValue(string, Object.class);
    }

    //Obtiene un listado de pendientes por sucursal
    public Object listarPendientesPorSucursal(int idSucursal) throws IOException {
        //Obtiene la sucursal por id
        Optional<Sucursal> sucursal = sucursalDAO.findById(idSucursal);
        List<ViajeRemito> remitos = elementoDAO.findBySucursalIngresoAndEstaPendienteFalse(sucursal);
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("ordenesVentas");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("clientefiltro", theFilter);
        String string = mapper.writer(filters).writeValueAsString(remitos);
        return mapper.readValue(string, Object.class);
    }

    //Obtiene un listado de pendientes por filtro
    public Object listarPendientesPorFiltro(int idSucursal, int idSucursalDestino, short numeroCamion) throws IOException {
        //Obtiene la sucursal por id
        Optional<Sucursal> sucursal = sucursalDAO.findById(idSucursal);
        //Obtiene la sucursal destino por id
        Optional<Sucursal> sucursalDestino = sucursalDAO.findById(idSucursalDestino);
        //Retorna los datos
        List<ViajeRemito> remitos = elementoDAO.findBySucursalIngresoAndSucursalDestinoAndNumeroCamionAndEstaPendienteTrue(sucursal, sucursalDestino, numeroCamion);
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept();
        FilterProvider filters = new SimpleFilterProvider();
        String string = mapper.writer(filters).writeValueAsString(remitos);
        return mapper.readValue(string, Object.class);
    }
    
    //Obtiene un listado de asignados por filtro
    public Object listarAsignadosPorFiltro(int idSucursal, int idSucursalDestino, short numeroCamion) throws IOException {
        //Obtiene la sucursal por id
        Optional<Sucursal> sucursal = sucursalDAO.findById(idSucursal);
        //Obtiene la sucursal destino por id
        Optional<Sucursal> sucursalDestino = sucursalDAO.findById(idSucursalDestino);
        //Retorna los datos
        List<ViajeRemito> remitos = elementoDAO.findBySucursalIngresoAndSucursalDestinoAndNumeroCamionAndEstaPendienteFalse(sucursal, sucursalDestino, numeroCamion);
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept();
        FilterProvider filters = new SimpleFilterProvider();
        String string = mapper.writer(filters).writeValueAsString(remitos);
        return mapper.readValue(string, Object.class);
    }

    //Obtiene un listado por filtro
    public Object listarPorFiltros(ViajeRemitoDTO viajeRemito) throws IOException {
        List<ViajeRemito> remitos;
        //Retorna los datos
        remitos = elementoDAO.listarPorFiltros(viajeRemito.getFechaDesde(),
                viajeRemito.getFechaHasta(), viajeRemito.getIdSucursalIngreso(),
                viajeRemito.getIdSucursalDestino(), viajeRemito.getIdClienteRemitente(),
                viajeRemito.getIdClienteDestinatario(), viajeRemito.getNumeroCamion());
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("ordenesVentas");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("clientefiltro", theFilter);
        String string = mapper.writer(filters).writeValueAsString(remitos);
        return mapper.readValue(string, Object.class);
    }
    
    //Obtiene una lista de letras
    public List<String> listarLetras() {
        return elementoDAO.listarLetras();
    }
    
    //Obtiene un registro por puntoVenta, letra y numero
    public Object obtener(int puntoVenta, String letra, int numero) throws IOException {
        ViajeRemito remitos = elementoDAO.findByPuntoVentaAndLetraAndNumero(puntoVenta, letra, numero);
        if (remitos==null) {
            throw new DataIntegrityViolationException("Registro no existente");
        }
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("ordenesVentas");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("clientefiltro", theFilter);
        String string = mapper.writer(filters).writeValueAsString(remitos);
        return mapper.readValue(string, Object.class);
    }

    //Asigna los remitos
    @Transactional(rollbackFor = Exception.class)
    public void asignar(String elementosString, String idViajeTramo) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        List<ViajeRemito> elementos = mapper.readValue(elementosString, mapper.getTypeFactory().constructCollectionType(List.class, ViajeRemito.class));
        //Obtiene el viajeRemito
        ViajeTramo viajeTramo = viajeTramoDAO.findById(Integer.valueOf(idViajeTramo)).get();
        ViajeTramoRemito tramoRemito;
        //Recorre la lista de remitos
        for (ViajeRemito viajeRemito : elementos) {
            if(!viajeRemito.getEstaPendiente()) {
                tramoRemito = new ViajeTramoRemito();
                tramoRemito.setViajeRemito(viajeRemito);
                tramoRemito.setViajeTramo(viajeTramo);
                viajeTramoRemitoDAO.saveAndFlush(tramoRemito);
                elementoDAO.save(viajeRemito);
            }
        }
    }

    //Quita los remitos
    @Transactional(rollbackFor = Exception.class)
    public void quitar(String elementosString, String idViajeTramo) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        List<ViajeRemito> elementos = mapper.readValue(elementosString, mapper.getTypeFactory().constructCollectionType(List.class, ViajeRemito.class));
        //Obtiene el viaje tramo
        ViajeTramo viajeTramo = viajeTramoDAO.findById(Integer.valueOf(idViajeTramo)).get();
        //Recorre la lista de remitos
        for (ViajeRemito viajeRemito : elementos) {
            if(viajeRemito.getEstaPendiente()) {
                //Elimina el viaje tramo remito
                viajeTramoRemitoDAO.deleteByViajeRemitoAndViajeTramo(viajeRemito, viajeTramo);
                //Actualiza el remito
                elementoDAO.save(viajeRemito);
            }
        }
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public ViajeRemito agregar(ViajeRemito elemento) throws Exception {
        //Establece valores por defecto
        elemento.setEstaPendiente(true);
        elemento.setEstaFacturado(false);
        elemento.setEstaEnReparto(false);
        //Obtiene longitud de numrtoCamion, si supera 3 retorna error
        String numCamion = String.valueOf(elemento.getNumeroCamion());
        if (numCamion.length() > 3) {
            throw new DataIntegrityViolationException("Cantidad caracteres excedida en NUMERO CAMION");
        }
        //Obtiene longitud de bultos, si supera 6 retorna error
        String bultos = String.valueOf(elemento.getBultos());
        if (bultos.length() > 6) {
            throw new DataIntegrityViolationException("Cantidad caracteres excedida en BULTOS");
        }
        //Formatea los strings
        elemento = formatearStrings(elemento);
        return elementoDAO.saveAndFlush(elemento);
    }

    //Establece el alias de un registro
    @Transactional(rollbackFor = Exception.class)
    public void establecerAlias(ViajeRemito elemento) {
        elemento.setAlias(elemento.getNumero() + " - (R: " + elemento.getClienteRemitente().getAlias() + ") - "
                + "(D: " + elemento.getClienteDestinatario().getAlias() + ")");
        elementoDAO.save(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(ViajeRemito elemento) throws Exception {
        elemento = formatearStrings(elemento);
        //Obtiene longitud de numrtoCamion, si supera 3 retorna error
        String numCamion = String.valueOf(elemento.getNumeroCamion());
        if (numCamion.length() > 3) {
            throw new DataIntegrityViolationException("Cantidad caracteres excedida en NUMERO CAMION");
        }
        //Obtiene longitud de bultos, si supera 6 retorna error
        String bultos = String.valueOf(elemento.getBultos());
        if (bultos.length() > 6) {
            throw new DataIntegrityViolationException("Cantidad caracteres excedida en BULTOS");
        }
        elemento.setAlias(elemento.getNumero() + " - (R: " + elemento.getClienteRemitente().getAlias() + ") - "
                + "(D: " + elemento.getClienteDestinatario().getAlias() + ")");
        elementoDAO.save(elemento);
    }

    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }

    //Formatea los strings
    private ViajeRemito formatearStrings(ViajeRemito elemento) {
        elemento.setLetra(elemento.getLetra().trim());
        if (elemento.getObservaciones() != null) {
            elemento.setObservaciones(elemento.getObservaciones().trim());
        }
        return elemento;
    }

}