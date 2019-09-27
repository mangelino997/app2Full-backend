package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IBasicoCategoriaDAO;
import ar.com.draimo.jitws.dao.IPersonalAdelantoDAO;
import ar.com.draimo.jitws.dao.IPersonalDAO;
import ar.com.draimo.jitws.dao.ISucursalDAO;
import ar.com.draimo.jitws.dao.ITipoComprobanteDAO;
import ar.com.draimo.jitws.dao.IUsuarioDAO;
import ar.com.draimo.jitws.dto.PersonalAdelantoLoteDTO;
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

    //Referencia al DAO de TipoComprobante
    @Autowired
    ITipoComprobanteDAO tipoComprobanteDAO;

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
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("datos");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("viajefiltro", theFilter)
                .addFilter("personaladelantofiltro", theFilter)
                .addFilter("filtroPdf", theFilter)
                .addFilter("filtroFoto", theFilter);
        String string = mapper.writer(filters).writeValueAsString(personalAdelantos);
        return mapper.readValue(string, Object.class);
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
        BigDecimal dif = new BigDecimal(0);
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
        if (total.compareTo(personalAdelanto.getImporte()) == 1) {
            dif = total.subtract(personalAdelanto.getImporte());
            cuotasAdelantos.get(personalAdelanto.getTotalCuotas() - 1).setImporte(importe.subtract(dif));

        } else if (total.compareTo(personalAdelanto.getImporte()) == -1) {
            dif = personalAdelanto.getImporte().subtract(total);
            cuotasAdelantos.get(personalAdelanto.getTotalCuotas() - 1).setImporte(importe.add(dif));
        }
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("datos");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("viajefiltro", theFilter)
                .addFilter("personaladelantofiltro", theFilter)
                .addFilter("filtroPdf", theFilter)
                .addFilter("filtroFoto", theFilter);
        String string = mapper.writer(filters).writeValueAsString(cuotasAdelantos);
        return mapper.readValue(string, Object.class);
    }

    //Obtiene la lista por filtro
    public Object listarPorFiltros(PersonalAdelantoLoteDTO personalAdelanto) throws IOException, Exception {
        List<PersonalAdelanto> adelantos = elementoDAO.listarPorFiltros(personalAdelanto.getIdEmpresa(),
                personalAdelanto.getIdSucursal(), personalAdelanto.getFechaDesde(),
                personalAdelanto.getFechaHasta(), personalAdelanto.getEstado(), personalAdelanto.getAlias(),
                personalAdelanto.getAdelanto());
        if (adelantos.isEmpty()) {
            throw new DataIntegrityViolationException("No se encontraron registros.");
        }
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("datos", "viajeTramos", "viajeCombustibles",
                        "viajeEfectivos", "viajeInsumos", "viajeGastos", "viajePeajes");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("viajefiltro", theFilter)
                .addFilter("personaladelantofiltro", theFilter)
                .addFilter("filtroPdf", theFilter)
                .addFilter("filtroFoto", theFilter);
        String string = mapper.writer(filters).writeValueAsString(adelantos);
        return mapper.readValue(string, Object.class);
    }

    //Agrega un lote
    @Transactional(rollbackFor = Exception.class)
    public List<PersonalAdelantoLoteDTO> listarLotes(Date fechaDesde, Date fechaHasta, int idEmpresa) throws IOException {
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
        BigDecimal importeCategoria = new BigDecimal(BigInteger.ZERO);
        BasicoCategoria basico;
        for (Personal personal : personales) {
            basico = basicoCategoriaDAO.obtenerPorCategoria(personal.getCategoria().getId());
            if (basico != null) {
                importeCategoria = basico.getBasico().multiply(basico.getCategoria().getTopeBasicoAdelantos().divide(new BigDecimal(100.00)));
            } else {
                importeCategoria = BigDecimal.ZERO;
            }
            if (importeCategoria.compareTo(adelantos.getImporte()) >= 0) {
                adelanto = new PersonalAdelanto();
                adelanto.setEmpresa(personal.getEmpresa());
                adelanto.setPersonal(personal);
                adelanto.setTipoComprobante(tipoComprobanteDAO.findById(16).get());
                adelanto.setEstaAnulado(false);
                adelanto.setFechaEmision(emision);
                adelanto.setFechaVto(emision);
                adelanto.setImporte(adelantos.getImporte());
                adelanto.setCuota((short) 1);
                adelanto.setTotalCuotas((short) 1);
                adelanto.setUsuarioAlta(usuarioDAO.findById(adelantos.getIdUsuarioAlta()).get());
                adelanto.setObservaciones(adelantos.getObservaciones());
                adelanto.setNumeroLote(lote);
                adelanto.setSucursal(sucursalDAO.findById(adelantos.getIdSucursal()).get());
                elementoDAO.saveAndFlush(adelanto);
            } else {
                adelanto = new PersonalAdelanto();
                adelanto.setEmpresa(personal.getEmpresa());
                adelanto.setPersonal(personal);
                adelanto.setFechaEmision(emision);
                adelanto.setImporte(adelantos.getImporte());
                adelanto.setObservaciones("No logró alcanzar el importe para recibir el adelanto");
                adelantosFallados.add(adelanto);
            }
        }
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("datos");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("viajefiltro", theFilter)
                .addFilter("personaladelantofiltro", theFilter)
                .addFilter("filtroPdf", theFilter)
                .addFilter("filtroFoto", theFilter);
        String string = mapper.writer(filters).writeValueAsString(adelantosFallados);
        return mapper.readValue(string, Object.class);
    }

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
        List<PersonalAdelanto> adelantos = elementoDAO.anular(elemento.getFechaEmision(), elemento.getPersonal().getId());
        for (PersonalAdelanto adelanto : adelantos) {
            adelanto.setEstaAnulado(true);
            adelanto.setUsuarioMod(elemento.getUsuarioMod());
            adelanto.setObservacionesAnulado(elemento.getObservacionesAnulado());
        }
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

}