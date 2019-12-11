//Paquete al que pertenece el servicio
package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IBasicoCategoriaDAO;
import ar.com.draimo.jitws.dao.ICategoriaDAO;
import ar.com.draimo.jitws.dao.IPersonalAdelantoDAO;
import ar.com.draimo.jitws.dao.IPersonalDAO;
import ar.com.draimo.jitws.dao.ISucursalDAO;
import ar.com.draimo.jitws.dao.ITipoComprobanteDAO;
import ar.com.draimo.jitws.dao.IUsuarioDAO;
import ar.com.draimo.jitws.dto.InitPersonalAdelantoDTO;
import ar.com.draimo.jitws.dto.PersonalAdelantoLoteDTO;
import ar.com.draimo.jitws.exception.MensajeRespuesta;
import ar.com.draimo.jitws.model.BasicoCategoria;
import ar.com.draimo.jitws.model.Personal;
import ar.com.draimo.jitws.model.PersonalAdelanto;
import ar.com.draimo.jitws.model.Reparto;
import ar.com.draimo.jitws.model.Viaje;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Define PersonalAdelantoService
 *
 * @author blas
 */
@Service
public class PersonalAdelantoService {

    //Referencia al DAO 
    @Autowired
    IPersonalAdelantoDAO elementoDAO;

    //Referencia al DAO de sucursal
    @Autowired
    ISucursalDAO sucursalDAO;

    //Referencia al DAO de personal
    @Autowired
    IPersonalDAO personalDAO;

    //Referencia al DAO de usuario
    @Autowired
    IUsuarioDAO usuarioDAO;

    //Referencia al DAO de BasicoCategoria
    @Autowired
    IBasicoCategoriaDAO basicoCategoriaDAO;

    //Referencia al DAO de Categoria
    @Autowired
    ICategoriaDAO categoriaDAO;

    //Referencia al DAO de TipoComprobante
    @Autowired
    ITipoComprobanteDAO tipoComprobanteDAO;
    
    //Referencia al service de subopcionpestania
    @Autowired
    SubopcionPestaniaService subopcionPestaniaService;
    
    //Obtiene listas necesarias para inicializar el componente (front)
    public InitPersonalAdelantoDTO inicializar(int idRol, int idSubopcion) {
        InitPersonalAdelantoDTO elemento = new InitPersonalAdelantoDTO();
        elemento.setPestanias(subopcionPestaniaService.listarPestaniasPorRolYSubopcion(idRol, idSubopcion));
        elemento.setCategorias(categoriaDAO.findAll());
        elemento.setSucursales(sucursalDAO.findAll());
        elemento.setUltimoId(obtenerSiguienteId());
        return elemento;
    }

    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        PersonalAdelanto elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId() + 1 : 1;
    }

    //Obtiene la lista completa
    public Object listar() throws IOException {
        List<PersonalAdelanto> personalAdelantos = elementoDAO.findAll();
        String id;
        Viaje viaje;
        Reparto reparto;
        /*
        * Recorre la lista de personal adelantos para obtener el idViaje o idReparto de cada item
        * Esto se realiza para evitar error de inner join de mas de 61 tablas
         */
        for (PersonalAdelanto personalAdelanto : personalAdelantos) {
            id = elementoDAO.obtenerIdViaje(personalAdelanto.getId());
            if (id != null) {
                viaje = new Viaje();
                viaje.setId(Integer.parseInt(id));
                personalAdelanto.setViaje(viaje);
            } else {
                id = elementoDAO.obtenerIdReparto(personalAdelanto.getId());
                reparto = new Reparto();
                reparto.setId(Integer.parseInt(id));
                personalAdelanto.setReparto(reparto);
            }
        }
        return aplicarFiltros(null, personalAdelantos);
    }

    //Controla la diferencia entre el importe y los importes de la tabla generada por listarCuotas
    public BigDecimal obtenerDiferenciaImportes(List<PersonalAdelantoLoteDTO> listaCuotas) {
        BigDecimal total = new BigDecimal(0);
        for (PersonalAdelantoLoteDTO listaCuota : listaCuotas) {
            total = total.add(listaCuota.getImporte());
        }
        return total.subtract(listaCuotas.get(0).getImporteTotal());
    }

    //Genera la tabla de cuotas
    public Object listarCoutas(PersonalAdelanto personalAdelanto) throws IOException {
        List<PersonalAdelanto> cuotasAdelantos = new ArrayList<>();
        PersonalAdelanto cuota;
        BigDecimal total = new BigDecimal(0);
        //Establece la cantidad de dias
        short cantDias = 30;
        //Obtiene la fechaActual
        String fechaString = new Date(new java.util.Date().getTime()).toString();
        Date fecha = new Date(new java.util.Date().getTime());
        BigDecimal cuotas = BigDecimal.valueOf(personalAdelanto.getTotalCuotas());
        //Obtiene el importe por cuota
        BigDecimal importe = personalAdelanto.getImporte().divide(cuotas, 2, RoundingMode.HALF_UP);
        //Establece los datos a vencimiento y lo agrega a la lista
        for (int i = 0; i < personalAdelanto.getTotalCuotas(); i++) {
            cuota = new PersonalAdelanto();
            cuota.setImporte(importe);
            fechaString = LocalDate.parse(fechaString).plusDays(cantDias).toString();
            cuota.setFechaVto(Date.valueOf(fechaString));
            cuota.setFechaEmision(fecha);
            cuota.setCuota((short) (i + 1));
            cuota.setEmpresa(personalAdelanto.getEmpresa());
            cuota.setPersonal(personalAdelanto.getPersonal());
            cuota.setSucursal(personalAdelanto.getSucursal());
            cuota.setTipoComprobante(tipoComprobanteDAO.findById(16).get());
            cuota.setObservaciones(personalAdelanto.getObservaciones());
            cuota.setTotalCuotas(personalAdelanto.getTotalCuotas());
            cuota.setUsuarioAlta(personalAdelanto.getUsuarioAlta());
            cuota.setNumeroLote(0);
            cuota.setEstaAnulado(false);
            cuotasAdelantos.add(cuota);
            total = total.add(importe);
        }
        /*
        * Verifica si existe algun resto al operar con numeros decimales 
        * y se lo suma a la ultima cuota
        */
        BigDecimal imp;
        switch (total.compareTo(personalAdelanto.getImporte())) {
            case 1:
                BigDecimal resto = total.subtract(personalAdelanto.getImporte());
                imp = importe.add(resto);
                break;
            case -1:
                imp = personalAdelanto.getImporte().subtract(total);
                break;
            default:
                imp = importe;
                break;
        }
        cuotasAdelantos.get(personalAdelanto.getTotalCuotas() - 1).setImporte(imp);
        return aplicarFiltros(null, cuotasAdelantos);
    }

    //Obtiene la lista por filtro
    public Object listarPorFiltros(PersonalAdelantoLoteDTO personalAdelanto) throws IOException {
        List<PersonalAdelanto> adelantos = elementoDAO.listarPorFiltros(personalAdelanto.getIdEmpresa(),
                personalAdelanto.getIdSucursal(), personalAdelanto.getFechaDesde(),
                personalAdelanto.getFechaHasta(), personalAdelanto.getEstado(), personalAdelanto.getAlias(),
                personalAdelanto.getAdelanto());
        if (adelantos.isEmpty()) {
            throw new DataIntegrityViolationException(MensajeRespuesta.LISTA_SIN_CONTENIDO);
        }
        return aplicarFiltros(null, adelantos);
    }

    //Agrega un lote
    @Transactional(rollbackFor = Exception.class)
    public List<PersonalAdelantoLoteDTO> listarLotes(Date fechaDesde, Date fechaHasta, int idEmpresa) {
        //Obtiene un listado de personales por filtros
        PersonalAdelantoLoteDTO palDto;
        List<PersonalAdelantoLoteDTO> adelantosDto = new ArrayList<>();
        Object[] adelantos = elementoDAO.listarLotes(fechaDesde, fechaHasta, idEmpresa);
        Object[] elementos;
        for (Object elemento : adelantos) {
            elementos = (Object[]) elemento;
            palDto = new PersonalAdelantoLoteDTO();
            palDto.setNumeroLote((Integer) elementos[0]);
            palDto.setLegajos((BigInteger) elementos[1]);
            palDto.setIdEmpresa((Integer) elementos[2]);
            palDto.setIdSucursal((Integer) elementos[3]);
            palDto.setIdUsuarioAlta((Integer) elementos[7]);
            palDto.setImporte((BigDecimal) elementos[4]);
            palDto.setObservaciones((String) elementos[5]);
            palDto.setFechaEmision((Date) elementos[6]);
            palDto.setImporteTotal(palDto.getImporte().multiply(BigDecimal.valueOf(palDto.getNumeroLote())));
            adelantosDto.add(palDto);
        }
        return adelantosDto;
    }

    //Agrega un lote
    @Transactional(rollbackFor = Exception.class)
    public Object agregarLote(PersonalAdelantoLoteDTO adelantos) throws IOException {
        //Obtiene un listado de personales por filtros
        List<Personal> personales = personalDAO.listarPersonalesParaAdelanto(adelantos.getIdEmpresa(),
                adelantos.getIdSucursal(), adelantos.getIdCategoria());
        List<PersonalAdelanto> adelantosFallados = new ArrayList<>();
        Date emision = new Date(new java.util.Date().getTime());
        PersonalAdelanto adelanto;
        int lote = (elementoDAO.findTopByOrderByNumeroLoteDesc() != null
                ? elementoDAO.findTopByOrderByNumeroLoteDesc().getNumeroLote() + 1 : 1);
        BigDecimal importeCategoria;
        BasicoCategoria basico;
        for (Personal personal : personales) {
            basico = basicoCategoriaDAO.obtenerPorCategoria(personal.getCategoria().getId());
            importeCategoria = (basico != null
                    ? basico.getBasico().multiply(basico.getCategoria().getTopeBasicoAdelantos().divide(new BigDecimal(100.00)))
                    : BigDecimal.ZERO);
            adelanto = new PersonalAdelanto();
            if (importeCategoria.compareTo(adelantos.getImporte()) >= 0) {
                adelanto.setTipoComprobante(tipoComprobanteDAO.findById(16).get());
                adelanto.setEstaAnulado(false);
                adelanto.setFechaVto(emision);
                adelanto.setCuota((short) 1);
                adelanto.setTotalCuotas((short) 1);
                adelanto.setUsuarioAlta(usuarioDAO.findById(adelantos.getIdUsuarioAlta()).get());
                adelanto.setObservaciones(adelantos.getObservaciones());
                adelanto.setNumeroLote(lote);
                adelanto.setSucursal(sucursalDAO.findById(adelantos.getIdSucursal()).get());
                elementoDAO.saveAndFlush(adelanto);
            } else {
                adelanto.setObservaciones(MensajeRespuesta.ADELANTO_NO_OTORGADO);
                adelantosFallados.add(adelanto);
            }
            adelanto.setFechaEmision(emision);
            adelanto.setImporte(adelantos.getImporte());
            adelanto.setEmpresa(personal.getEmpresa());
            adelanto.setPersonal(personal);
        }
        return aplicarFiltros(null, adelantosFallados);
    }

    //Control para ver si todos los adelantos de un lote fracasaron
    public boolean controlAdelantosFallidos(Object elemento, PersonalAdelantoLoteDTO adelanto) {
        List<PersonalAdelanto> adelantos = (List<PersonalAdelanto>) ((Object) elemento);
        List<Personal> personales = personalDAO.listarPersonalesParaAdelanto(adelanto.getIdEmpresa(),
                adelanto.getIdSucursal(), adelanto.getIdCategoria());
        return (adelantos.size() == personales.size());
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public List<PersonalAdelanto> agregarPrestamo(List<PersonalAdelanto> elementos) {
        for (PersonalAdelanto elemento : elementos) {
            elemento.setEstaAnulado(false);
            elemento.setNumeroLote(0);
            elemento.setTipoComprobante(tipoComprobanteDAO.findById(16).get());
            elementoDAO.saveAndFlush(elemento);
        }
        elementos.get(0).getPersonal().setRecibePrestamo(false);
        elementos.get(0).getPersonal().setCuotasPrestamo((short) 1);
        personalDAO.save(elementos.get(0).getPersonal());
        return elementos;
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void anularLote(PersonalAdelanto elemento) {
        List<PersonalAdelanto> adel = elementoDAO.anularPorLote(elemento.getNumeroLote());
        for (PersonalAdelanto per : adel) {
            per.setEstaAnulado(true);
            per.setUsuarioMod(elemento.getUsuarioMod());
            per.setObservacionesAnulado(elemento.getObservacionesAnulado());
        }
    }

    //Anula un registro - un adelanto personal
    @Transactional(rollbackFor = Exception.class)
    public void anular(PersonalAdelanto elemento) {
       elemento.setEstaAnulado(true);
       elementoDAO.save(elemento);
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public PersonalAdelanto agregar(PersonalAdelanto elemento) {
        elemento.setFechaVto(new Date(new java.util.Date().getTime()));
        elemento.setCuota((short) 1);
        elemento.setEstaAnulado(false);
        elemento.setNumeroLote(0);
        elemento.setTipoComprobante(tipoComprobanteDAO.findById(16).get());
        return elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(PersonalAdelanto elemento) {
        elementoDAO.save(elemento);
    }

    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int id) {
        elementoDAO.deleteById(id);
    }

    //retorna un object despues de aplicar los filtros
    private Object aplicarFiltros(PersonalAdelanto elemento, List<PersonalAdelanto> elementos) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("datos", "viajeTramos", "viajeCombustibles",
                        "viajeEfectivos", "viajeInsumos", "viajeGastos", "viajePeajes");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("viajefiltro", theFilter)
                .addFilter("personaladelantofiltro", theFilter)
                .addFilter("filtroPdf", theFilter)
                .addFilter("filtroFoto", theFilter);
        String string = mapper.writer(filters).writeValueAsString(elemento!=null? elemento : elementos);
        return mapper.readValue(string, Object.class);
    }
}
