package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IRetiroDepositoComprobanteDAO;
import ar.com.draimo.jitws.dao.IRetiroDepositoDAO;
import ar.com.draimo.jitws.dao.IVentaComprobanteDAO;
import ar.com.draimo.jitws.dao.IViajeRemitoDAO;
import ar.com.draimo.jitws.model.RetiroDepositoComprobante;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio retiroDepositoComprobante
 * @author blas
 */

@Service
public class RetiroDepositoComprobanteService {
    
    //Define la referencia al dao
    @Autowired
    IRetiroDepositoComprobanteDAO elementoDAO;
    
    //Define la referencia al dao de RetiroDeposito
    @Autowired
    IRetiroDepositoDAO retiroDepositoDAO;
    
    //Define la referencia al dao de RepartoPropio
    @Autowired
    IVentaComprobanteDAO ventaComprobanteDAO;
    
    //Define la referencia al dao de RepartoPropio
    @Autowired
    IViajeRemitoDAO viajeRemitoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        RetiroDepositoComprobante elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento!= null ? elemento.getId()+1: 1;
    }
    
    //Obtiene la lista completa
    public Object listar() throws IOException {
        List<RetiroDepositoComprobante> elementos = elementoDAO.findAll();
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("ventaComprobante", "ordenVenta","cliente");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("filtroVentaComprobanteItemFA", theFilter)
                .addFilter("filtroVentaComprobanteItemCR", theFilter)
                .addFilter("filtroVentaComprobanteItemNC", theFilter)
                .addFilter("filtroOrdenVentaEscala", theFilter)
                .addFilter("clienteordenventafiltro", theFilter)
                .addFilter("filtroOrdenVentaTramo", theFilter);
        String string = mapper.writer(filters).writeValueAsString(elementos);
        return new ObjectMapper().readValue(string, Object.class);
    }
    
    //Obtiene la lista por RetiroDeposito
    public Object listarComprobantes(int idRetiroDeposito) throws IOException {
        List<RetiroDepositoComprobante> elementos = elementoDAO.findByRetiroDeposito(retiroDepositoDAO.findById(idRetiroDeposito).get());
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("ventaComprobante", "ordenVenta","cliente");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("filtroVentaComprobanteItemFA", theFilter)
                .addFilter("filtroVentaComprobanteItemCR", theFilter)
                .addFilter("filtroVentaComprobanteItemNC", theFilter)
                .addFilter("filtroOrdenVentaEscala", theFilter)
                .addFilter("clienteordenventafiltro", theFilter)
                .addFilter("filtroOrdenVentaTramo", theFilter);
        String string = mapper.writer(filters).writeValueAsString(elementos);
        return new ObjectMapper().readValue(string, Object.class);
    }
    
    //Quita un comprobante de la tabla y la planilla
    public int quitarComprobante(int id) {
        int idrp = elementoDAO.findById(id).get().getRetiroDeposito().getId();
        elementoDAO.deleteById(id);
        return idrp;
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public RetiroDepositoComprobante agregar(RetiroDepositoComprobante c) {
//        if(c.getVentaComprobante()!= null) {
//            c.setVentaComprobante(ventaComprobanteDAO.findByPuntoVentaAndLetraAndNumero(
//                c.getVentaComprobante().getPuntoVenta(),c.getVentaComprobante().getLetra(),
//                c.getVentaComprobante().getNumero())
//             );
//        }else if(c.getViajeRemito()!=null){
//            c.setViajeRemito(viajeRemitoDAO.findByPuntoVentaAndLetraAndNumero(
//                    c.getViajeRemito().getPuntoVenta(), c.getViajeRemito().getLetra(),
//                    c.getViajeRemito().getNumero()));
//        }
//        return elementoDAO.saveAndFlush(c);
        return new RetiroDepositoComprobante();
    }
    
    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public RetiroDepositoComprobante actualizar(RetiroDepositoComprobante elemento) {
        return elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }
    
}
