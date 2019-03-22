package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IOrdenRecoleccionDAO;
import ar.com.draimo.jitws.dao.IRepartoPropioComprobanteDAO;
import ar.com.draimo.jitws.dao.IRepartoPropioDAO;
import ar.com.draimo.jitws.dao.IVentaComprobanteDAO;
import ar.com.draimo.jitws.dao.IViajeRemitoDAO;
import ar.com.draimo.jitws.model.RepartoPropioComprobante;
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
 * Servicio RepartoPropioComprobante
 * @author blas
 */

@Service
public class RepartoPropioComprobanteService {
    
    //Define la referencia al dao
    @Autowired
    IRepartoPropioComprobanteDAO elementoDAO;
    
    //Define la referencia al dao de RepartoPropio
    @Autowired
    IRepartoPropioDAO repartoPropioDAO;
    
    //Define la referencia al dao de RepartoPropio
    @Autowired
    IVentaComprobanteDAO ventaComprobanteDAO;
    
    //Define la referencia al dao de RepartoPropio
    @Autowired
    IViajeRemitoDAO viajeRemitoDAO;
    
    //Define la referencia al dao de RepartoPropio
    @Autowired
    IOrdenRecoleccionDAO ordenRecoleccionDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        RepartoPropioComprobante elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento.getId()+1;
    }
    
    //Obtiene la lista completa
    public List<RepartoPropioComprobante> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene la lista por repartoPropio
    public Object listarComprobantes(int idRepartoPropio) throws IOException {
        List<RepartoPropioComprobante> comprobantes = elementoDAO.findByRepartoPropio(repartoPropioDAO.findById(idRepartoPropio).get());
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("ordenVenta");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("filtroOrdenVentaEscala", theFilter)
                .addFilter("filtroOrdenVentaTramo", theFilter);
        String string =  mapper.writer(filters).writeValueAsString(comprobantes);
        return new ObjectMapper().readValue(string, Object.class);
    }
    
    //Quita un comprobante de la tabla y la planilla
    public int quitarComprobante(int id) {
        int idrp = elementoDAO.findById(id).get().getRepartoPropio().getId();
        elementoDAO.deleteById(id);
        return idrp;
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public RepartoPropioComprobante agregar(RepartoPropioComprobante c) {
        if(c.getVentaComprobante()!= null) {
            c.setVentaComprobante(ventaComprobanteDAO.findByPuntoVentaAndLetraAndNumero(
                c.getVentaComprobante().getPuntoVenta(),c.getVentaComprobante().getLetra(),
                c.getVentaComprobante().getNumero()));
        }else if(c.getViajeRemito()!=null){
            c.setViajeRemito(viajeRemitoDAO.findByPuntoVentaAndLetraAndNumero(
                    c.getViajeRemito().getPuntoVenta(), c.getViajeRemito().getLetra(),
                    c.getViajeRemito().getNumero()));
        }
        return (c.getOrdenRecoleccion() == null && c.getViajeRemito()== null
                && c.getVentaComprobante() == null ? new RepartoPropioComprobante()
                : elementoDAO.saveAndFlush(c));
    }
    
    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public RepartoPropioComprobante actualizar(RepartoPropioComprobante elemento) {
        return elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(RepartoPropioComprobante elemento) {
        elementoDAO.delete(elemento);
    }
    
}
