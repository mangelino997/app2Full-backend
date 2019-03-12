package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IOrdenRecoleccionDAO;
import ar.com.draimo.jitws.dao.IRepartoTerceroComprobanteDAO;
import ar.com.draimo.jitws.dao.IRepartoTerceroDAO;
import ar.com.draimo.jitws.dao.IVentaComprobanteDAO;
import ar.com.draimo.jitws.dao.IViajeRemitoDAO;
import ar.com.draimo.jitws.model.RepartoTerceroComprobante;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio RepartoPropioComprobante
 * @author blas
 */

@Service
public class RepartoTerceroComprobanteService {
    
    //Define la referencia al dao
    @Autowired
    IRepartoTerceroComprobanteDAO elementoDAO;
    
    //Define la referencia al dao de RepartoTercero
    @Autowired
    IRepartoTerceroDAO repartoTerceroDAO;
    
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
        RepartoTerceroComprobante elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento.getId()+1;
    }
    
    //Obtiene la lista completa
    public List<RepartoTerceroComprobante> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene la lista por repartoTercero
    public List<RepartoTerceroComprobante> listarComprobantes(int idRepartoTercero) {
        return elementoDAO.findByRepartoTercero(repartoTerceroDAO.findById(idRepartoTercero).get());
    }
    
    //Quita un comprobante de la tabla y la planilla
    public int quitarComprobante(int id) {
        int idrt = elementoDAO.findById(id).get().getRepartoTercero().getId();
        elementoDAO.deleteById(id);
        return idrt;
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public RepartoTerceroComprobante agregar(RepartoTerceroComprobante c) {
        //Consulta si venta comprobante no es nula
        if(c.getVentaComprobante()!= null) {
            //Setea al comprobante la venta comprobante por puntoventa letra y numero
            c.setVentaComprobante(ventaComprobanteDAO.findByPuntoVentaAndLetraAndNumero(
                c.getVentaComprobante().getPuntoVenta(),c.getVentaComprobante().getLetra(),
                c.getVentaComprobante().getNumero()));
            //Consulta si viajeRemito no es nulo
        }else if(c.getViajeRemito()!=null){
            //Setea al comprobante un viaje remito por punto venta letra y numero
            c.setViajeRemito(viajeRemitoDAO.findByPuntoVentaAndLetraAndNumero(
                    c.getViajeRemito().getPuntoVenta(), c.getViajeRemito().getLetra(),
                    c.getViajeRemito().getNumero()));
        }
        //Controla que haya uno de los tres comprobantes
        if(c.getOrdenRecoleccion() == null && c.getViajeRemito()== null
                && c.getVentaComprobante() == null){
            return new RepartoTerceroComprobante();
        }else return elementoDAO.saveAndFlush(c);
    }
    
    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(RepartoTerceroComprobante elemento) {
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(RepartoTerceroComprobante elemento) {
        elementoDAO.delete(elemento);
    }
    
}
