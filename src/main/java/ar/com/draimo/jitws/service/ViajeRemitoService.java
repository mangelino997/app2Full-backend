//Paquete al que pertenece el servicio
package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IClienteDAO;
import ar.com.draimo.jitws.dao.ISucursalDAO;
import ar.com.draimo.jitws.dao.ITipoComprobanteDAO;
import ar.com.draimo.jitws.dao.IViajeRemitoDAO;
import ar.com.draimo.jitws.dto.ViajeRemitoDTO;
import ar.com.draimo.jitws.model.Sucursal;
import ar.com.draimo.jitws.model.ViajeRemito;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ar.com.draimo.jitws.dao.IViajeTramoDAO;
import ar.com.draimo.jitws.dao.IViajeTramoRemitoDAO;
import ar.com.draimo.jitws.dto.GenericoDTO;
import ar.com.draimo.jitws.dto.InitViajeRemitoDTO;
import ar.com.draimo.jitws.exception.MensajeRespuesta;
import ar.com.draimo.jitws.model.Cliente;
import ar.com.draimo.jitws.model.ViajeTramo;
import ar.com.draimo.jitws.model.ViajeTramoRemito;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import org.springframework.dao.DataIntegrityViolationException;

/**
 * Servicio de ViajeRemito
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

    //Define la referencia al dao cliente
    @Autowired
    IClienteDAO clienteDAO;

    //Define la referencia al dao viaje tramo
    @Autowired
    IViajeTramoDAO viajeTramoDAO;

    //Define la referencia al dao tipoComprobante
    @Autowired
    ITipoComprobanteDAO tipoComprobanteDAO;

    //Define la referencia al dao viaje tramo remito
    @Autowired
    IViajeTramoRemitoDAO viajeTramoRemitoDAO;
    
    //Referencia al service de subopcionpestania
    @Autowired
    SubopcionPestaniaService subopcionPestaniaService;
    
    //Obtiene listas necesarias para inicializar el componente (front)
    public InitViajeRemitoDTO inicializar(int idRol, int idSubopcion) {
        InitViajeRemitoDTO elemento = new InitViajeRemitoDTO();
        elemento.setPestanias(subopcionPestaniaService.listarPestaniasPorRolYSubopcion(idRol, idSubopcion));
        elemento.setSucursales(sucursalDAO.findAll());
        elemento.setTipoComprobantes(tipoComprobanteDAO.findByEstaActivoIngresoCargaTrue());
        elemento.setFecha(new Date(new java.util.Date().getTime()));
        return elemento;
    }

    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        ViajeRemito elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId() + 1 : 1;
    }

    //Obtiene el listado completo
    public Object listar() throws IOException {
        List<ViajeRemito> elementos = elementoDAO.findAll();
        return retornarObjeto(elementos, null);
    }

    //Obtiene el listado de remitos disponibles
    public Object listarRemitosDisponibles() throws IOException {
        List<ViajeRemito> elementos = elementoDAO.listarRemitosDisponibles();
        return retornarObjeto(elementos, null);
    }

    //Obtiene una lista por alias
    public Object listarPorAlias(String alias) throws IOException {
        List<ViajeRemito> elementos = alias.equals("*") ? elementoDAO.findAll()
                : elementoDAO.findByAliasContaining(alias);
        return retornarObjeto(elementos, null);
    }

    //Obtiene un listado por numero de comprobante
    public Object listarPorNumero(int numero) throws IOException {
        List<ViajeRemito> elementos = elementoDAO.findByNumero(numero);
        return retornarObjeto(elementos, null);
    }

    //Obtiene un listado por viaje y estado
    public Object listarPorViajeYEstado(ViajeRemitoDTO viajeRemitoDTO) throws IOException {
        List<ViajeRemito> elementos = elementoDAO.listarPorViajeYEstaFacturado(
        viajeRemitoDTO.getIdViaje(),viajeRemitoDTO.getIdRemito(), viajeRemitoDTO.getEstaFacturado()==1);
        return retornarObjeto(elementos, null);
    }

    //Obtiene un listado de no pendientes por viajeTramo
    public List<ViajeRemito> listarAsignadosPorViajeTramo(int idViajeTramo) {
        List<ViajeRemito> elementos = elementoDAO.listarAsignadosPorViajeTramo(idViajeTramo);
        return elementos;
    }

    //Obtiene un listado de pendientes por sucursal
    public Object listarPendientesPorSucursal(int idSucursal) throws IOException {
        List<ViajeRemito> elementos = elementoDAO.findBySucursalIngresoAndEstaPendienteFalse(
                sucursalDAO.findById(idSucursal));
        return retornarObjeto(elementos, null);
    }

    //Obtiene un listado de pendientes por filtro
    public List<ViajeRemito> listarPendientesPorFiltro(int idSucursal, int idSucursalDestino,
            short numeroCamion) {
        //Obtiene la sucursal por id
        Sucursal sucursal = sucursalDAO.findById(idSucursal).get();
        //Obtiene la sucursal destino por id
        Sucursal sucursalDestino = sucursalDAO.findById(idSucursalDestino).get();
        //Retorna los datos
        List<ViajeRemito> elementos = elementoDAO.findBySucursalIngresoAndSucursalDestinoAndNumeroCamionAndEstaPendienteTrue(
                sucursal, sucursalDestino, numeroCamion);
        return elementos;
    }

    //Obtiene un listado por filtro
    public Object listarPorFiltros(ViajeRemitoDTO viajeRemito) throws IOException {
        List<ViajeRemito> elementos = elementoDAO.listarPorFiltros(viajeRemito.getFechaDesde(),
                viajeRemito.getFechaHasta(), viajeRemito.getIdSucursalIngreso(),
                viajeRemito.getIdSucursalDestino(), viajeRemito.getIdClienteRemitente(),
                viajeRemito.getIdClienteDestinatario(), viajeRemito.getNumeroCamion(),
                viajeRemito.getEstaFacturado(), viajeRemito.getEstaPendiente());
        return retornarObjeto(elementos, null);
    }

    //Obtiene una lista de letras
    public List<String> listarLetras() {
        return elementoDAO.listarLetras();
    }

    //Obtiene un registro por puntoVenta, letra y numero si no esta asignado a un comprobante
    public Object obtenerParaReparto(int puntoVenta, String letra, int numero) throws IOException {
        List<ViajeRemito> remitos = elementoDAO.listarParaReparto(numero,puntoVenta, letra);
        return retornarObjeto(remitos, null);
    }

    //Obtiene un registro por puntoVenta, letra y numero
    public Object obtener(int puntoVenta, String letra, int numero) throws IOException {
        ViajeRemito remito = elementoDAO.obtenerPorPuntoVentaLetraYNumero(numero,puntoVenta, letra);
        return retornarObjeto(null, remito);
    }

    //Asigna los remitos
    @Transactional(rollbackFor = Exception.class)
    public void asignar(String elementosString, String idViajeTramo) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        List<ViajeRemito> elementos = mapper.readValue(elementosString,
                mapper.getTypeFactory().constructCollectionType(List.class, ViajeRemito.class));
        //Obtiene el viajeRemito
        ViajeTramo viajeTramo = viajeTramoDAO.findById(Integer.valueOf(idViajeTramo)).get();
        ViajeTramoRemito tramoRemito;
        //Recorre la lista de remitos
        for (ViajeRemito viajeRemito : elementos) {
            if (!viajeRemito.getEstaPendiente()) {
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
        List<ViajeRemito> elementos = mapper.readValue(elementosString,
                mapper.getTypeFactory().constructCollectionType(List.class, ViajeRemito.class));
        //Obtiene el viaje tramo
        ViajeTramo viajeTramo = viajeTramoDAO.findById(Integer.valueOf(idViajeTramo)).get();
        //Recorre la lista de remitos
        for (ViajeRemito viajeRemito : elementos) {
            if (viajeRemito.getEstaPendiente()) {
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
        elemento = controlarLongitudes(elemento);
        return elementoDAO.saveAndFlush(elemento);
    }

    //Establece el alias de un registro
    @Transactional(rollbackFor = Exception.class)
    public void establecerAlias(ViajeRemito elemento) {
        Cliente rem = clienteDAO.findById(elemento.getClienteRemitente().getId()).get();
        Cliente dest = clienteDAO.findById(elemento.getClienteDestinatario().getId()).get();
        elemento.setAlias(elemento.getNumero() + " - (R: " + rem.getAlias() + ") - "
                + "(D: " + dest.getAlias() + ")");
        elementoDAO.save(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(ViajeRemito elemento) throws Exception {
        elemento = formatearStrings(elemento);
        elemento = controlarLongitudes(elemento);
        establecerAlias(elemento);
    }

    private ViajeRemito controlarLongitudes(ViajeRemito elemento) {
        //Obtiene longitud de numrtoCamion, si supera 3 retorna error
        String numCamion = String.valueOf(elemento.getNumeroCamion());
        if (numCamion.length() > 3) {
            throw new DataIntegrityViolationException(MensajeRespuesta.LONGITUD + " NUMERO CAMION");
        }
        //Obtiene longitud de bultos, si supera 6 retorna error
        String bultos = String.valueOf(elemento.getBultos());
        if (bultos.length() > 6) {
            throw new DataIntegrityViolationException(MensajeRespuesta.LONGITUD + " BULTOS");
        }
        return formatearStrings(elemento);
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

    //Convierte una lista o un elemento a object para retornar con filtros aplicados
    private Object retornarObjeto(List<ViajeRemito> elementos, ViajeRemito elemento) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("ordenesVentas");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("clientefiltro", theFilter);
        String string = mapper.writer(filters).writeValueAsString(elementos!=null ? elementos : elemento);
        return mapper.readValue(string, Object.class);
    }
}
