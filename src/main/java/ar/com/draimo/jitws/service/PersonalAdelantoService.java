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
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Define PersonalAdelantoService
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
        return elemento != null ? elemento.getId()+1 : 1;
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
        for(PersonalAdelanto personalAdelanto : personalAdelantos) {
            id = elementoDAO.obtenerIdViaje(personalAdelanto.getId());
            if(id != null) {
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
                .serializeAllExcept();
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("viajefiltro", theFilter)
                .addFilter("personaladelantofiltro", theFilter);
        String string = mapper.writer(filters).writeValueAsString(personalAdelantos);
        return mapper.readValue(string, Object.class);
    }
    
    //Obtiene la lista completa
    public Object listarPorLote(Date fechaEmision, int numeroLote) throws IOException {
        return elementoDAO.listarPorNumeroLote(numeroLote, fechaEmision);
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
        BigDecimal importeCategoria = new BigDecimal(BigInteger.ZERO);
        BasicoCategoria basico ;
        for (Personal personal : personales) {
            basico = basicoCategoriaDAO.obtenerPorCategoria(personal.getCategoria().getId());
            importeCategoria = basico.getBasico().multiply(basico.getCategoria().getTopeBasicoAdelantos().divide(new BigDecimal(100.00)));
            if(importeCategoria.compareTo(adelantos.getImporte()) >= 0) {
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
                adelanto.setNumeroLote(1);
                adelanto.setSucursal(sucursalDAO.findById(adelantos.getIdSucursal()).get());
                elementoDAO.saveAndFlush(adelanto);
            } else{
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
                .serializeAllExcept();
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("viajefiltro", theFilter)
                .addFilter("personaladelantofiltro", theFilter);
        String string = mapper.writer(filters).writeValueAsString(adelantosFallados);
        return mapper.readValue(string, Object.class);
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public PersonalAdelanto agregar(PersonalAdelanto elemento) {
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