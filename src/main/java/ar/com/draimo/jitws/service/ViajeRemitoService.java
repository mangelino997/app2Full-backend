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

/**
 * Servicio de ViajePropio
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
    IViajeTramoDAO viajePropioTramoDAO;
    
    //Define la referencia al dao viaje remito tramo
    @Autowired
    IViajeTramoRemitoDAO viajeRemitoTramoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        ViajeRemito elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId()+1 : 1;
    }
    
    //Obtiene el listado completo
    public Object listar() throws IOException {
        List<ViajeRemito> remitos=elementoDAO.findAll();
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("cliente");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("clienteordenventafiltro", theFilter);
        String string = mapper.writer(filters).writeValueAsString(remitos);
        return mapper.readValue(string, Object.class);
    }
    
    //Obtiene una lista por alias
    public Object listarPorAlias(String alias) throws IOException {
        List<ViajeRemito> remitos;
        if(alias.equals("***")) {
            remitos =elementoDAO.findAll();
        }else {
            remitos= elementoDAO.findByAliasContaining(alias);
        }
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("cliente");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("clienteordenventafiltro", theFilter);
        String string = mapper.writer(filters).writeValueAsString(remitos);
        return mapper.readValue(string, Object.class);
    }
    
    //Obtiene un listado por numero de comprobante
    public Object listarPorNumero(int numero) throws IOException {
        List<ViajeRemito> remitos= elementoDAO.findByNumero(numero);
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("cliente");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("clienteordenventafiltro", theFilter);
        String string = mapper.writer(filters).writeValueAsString(remitos);
        return mapper.readValue(string, Object.class);
    }
    
    //Obtiene un listado de no pendientes por viajeTramo
    public Object listarAsignadosPorViajeTramo(int idViajeTramo) throws IOException {
        List<ViajeRemito> remitos =elementoDAO.listarAsignadosPorViajeTramo(idViajeTramo);
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("cliente");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("clienteordenventafiltro", theFilter);
        String string = mapper.writer(filters).writeValueAsString(remitos);
        return mapper.readValue(string, Object.class);
    }
    
    //Obtiene un listado de pendientes por sucursal
    public Object listarPendientesPorSucursal(int idSucursal) throws IOException {
        //Obtiene la sucursal por id
        Optional<Sucursal> sucursal = sucursalDAO.findById(idSucursal);
        List<ViajeRemito> remitos=elementoDAO.findBySucursalIngresoAndEstaPendienteFalse(sucursal);
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("cliente");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("clienteordenventafiltro", theFilter);
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
        List<ViajeRemito> remitos =elementoDAO.findBySucursalIngresoAndSucursalDestinoAndNumeroCamionAndEstaPendienteTrue(sucursal, sucursalDestino, numeroCamion);
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("cliente");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("clienteordenventafiltro", theFilter);
        String string = mapper.writer(filters).writeValueAsString(remitos);
        return mapper.readValue(string, Object.class);
    }
    
//    //Obtiene un listado de asignados por filtro
//    public List<ViajeRemito> listarAsignadosPorFiltro(int idSucursal, int idSucursalDestino, short numeroCamion, int idViajePropioTramo) {
//        //Obtiene la sucursal por id
//        Optional<Sucursal> sucursal = sucursalDAO.findById(idSucursal);
//        //Obtiene la sucursal destino por id
//        Optional<Sucursal> sucursalDestino = sucursalDAO.findById(idSucursalDestino);
//        //Obtiene el viaje propio tramo por id
//        Optional<ViajePropioTramo> viajePropioTramo = viajePropioTramoDAO.findById(idViajePropioTramo);
//        //Retorna los datos
//        return elementoDAO.findBySucursalIngresoAndSucursalDestinoAndNumeroCamionAndViajePropioTramoAndEstaPendienteFalse(
//                sucursal, sucursalDestino, numeroCamion, viajePropioTramo);
//    }
    
    //Obtiene un listado por filtro
    public Object listarPorFiltros(ViajeRemitoDTO viajeRemito) throws IOException {
        List<ViajeRemito> remitos;
            //Retorna los datos
        remitos= elementoDAO.listarPorFiltros(viajeRemito.getFechaDesde(), 
                viajeRemito.getFechaHasta(),viajeRemito.getIdSucursalIngreso(),
                viajeRemito.getIdSucursalDestino(),viajeRemito.getIdClienteRemitente(),
                viajeRemito.getIdClienteDestinatario(), viajeRemito.getNumeroCamion());
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("cliente");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("clienteordenventafiltro", theFilter);
        String string = mapper.writer(filters).writeValueAsString(remitos);
        return mapper.readValue(string, Object.class);
    }
    
//    //Obtiene un listado de remitos por viajePropio
//    public List<ViajeRemito> listarRemitos(int idViajeTramo, int item) {
//        List<ViajeRemito> viajeRemitos = new ArrayList<>();
//        if (item==1) { //ViajePropioTramo
//            viajeRemitos = elementoDAO.findByViajePropioTramoAndEstaFacturadoFalse
//        (viajePropioTramoDAO.findById(idViajeTramo));
//        } else if (item ==2){ //ViajeTerceroTramo
//            viajeRemitos = elementoDAO.findByViajeTerceroTramoAndEstaFacturadoFalse
//        (viajeTerceroTramoDAO.findById(idViajeTramo));
//        }
//        return viajeRemitos;
//    }
    
    //Obtiene un registro por puntoVenta, letra y numero
    public Object obtener(int puntoVenta, String letra, int numero) throws IOException {
        ViajeRemito remitos=elementoDAO.findByPuntoVentaAndLetraAndNumero(puntoVenta, letra, numero);
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("cliente");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("clienteordenventafiltro", theFilter);
        String string = mapper.writer(filters).writeValueAsString(remitos);
        return mapper.readValue(string, Object.class);
    }
    
    //Asigna los remitos
    @Transactional(rollbackFor = Exception.class)
    public void asignar(String elementosString, String idViajeTramo) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        List<ViajeRemito> elementos =mapper.readValue(elementosString , mapper.getTypeFactory().constructCollectionType(List.class, ViajeRemito.class));
        //Obtiene el viajeRemito
        ViajeTramo viajeTramo = viajePropioTramoDAO.findById(Integer.valueOf(idViajeTramo)).get();
        ViajeTramoRemito tramoRemito ;
        //Recorre la lista de remitos
        for(ViajeRemito viajeRemito : elementos) {
            viajeRemito.setEstaPendiente(false);
            tramoRemito = new ViajeTramoRemito();
            tramoRemito.setViajeRemito(viajeRemito);
            tramoRemito.setViajeTramo(viajeTramo);
            viajeRemitoTramoDAO.saveAndFlush(tramoRemito);
            elementoDAO.save(viajeRemito);
        }
    }
    
    //Quita los remitos
    @Transactional(rollbackFor = Exception.class)
    public void quitar(int idViajeRemito, int idViajeTramo) {
        //Obtiene el viajeTramo
        ViajeTramo viajeTramo = viajePropioTramoDAO.findById(idViajeTramo).get();
        //Obtiene el viajeRemito
        ViajeRemito viajeRemito = elementoDAO.findById(idViajeRemito).get();
        //Obtiene el viajeTramoRemito por Tramo y remito
        ViajeTramoRemito tramoRemito = viajeRemitoTramoDAO.findByViajeRemitoAndViajeTramo(viajeRemito, viajeTramo);
        //Elimina el viajeTramoRemito por tramo y remito
        viajeRemitoTramoDAO.delete(tramoRemito);
        viajeRemito.setEstaPendiente(true);
        elementoDAO.save(viajeRemito);
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public ViajeRemito agregar(ViajeRemito elemento) {
        //Establece valores por defecto
        elemento.setEstaPendiente(true);
        elemento.setEstaFacturado(false);
        elemento.setEstaEnReparto(false);
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
    public void actualizar(ViajeRemito elemento) {
        elemento = formatearStrings(elemento);
        elemento.setAlias(elemento.getNumero() + " - (R: " + elemento.getClienteRemitente().getAlias() + ") - " 
                + "(D: " + elemento.getClienteDestinatario().getAlias() + ")");
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(ViajeRemito elemento) {
        elementoDAO.delete(elemento);
    }
    
    //Formatea los strings
    private ViajeRemito formatearStrings(ViajeRemito elemento) {
        elemento.setLetra(elemento.getLetra().trim());
        if(elemento.getObservaciones() != null) {
            elemento.setObservaciones(elemento.getObservaciones().trim());
        }
        return elemento;
    }
    
}
