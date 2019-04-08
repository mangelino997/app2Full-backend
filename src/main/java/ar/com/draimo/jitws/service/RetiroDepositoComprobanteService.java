package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IRetiroDepositoComprobanteDAO;
import ar.com.draimo.jitws.dao.IRetiroDepositoDAO;
import ar.com.draimo.jitws.dao.IVentaComprobanteDAO;
import ar.com.draimo.jitws.dao.IViajeRemitoDAO;
import ar.com.draimo.jitws.model.RetiroDepositoComprobante;
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
        return elemento.getId()+1;
    }
    
    //Obtiene la lista completa
    public List<RetiroDepositoComprobante> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene la lista por RetiroDeposito
    public List<RetiroDepositoComprobante> listarComprobantes(int idRetiroDeposito) {
        return elementoDAO.findByRetiroDeposito(retiroDepositoDAO.findById(idRetiroDeposito).get());
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
        if(c.getVentaComprobante()!= null) {
            c.setVentaComprobante(ventaComprobanteDAO.findByPuntoVentaAndLetraAndNumero(
                c.getVentaComprobante().getPuntoVenta(),c.getVentaComprobante().getLetra(),
                c.getVentaComprobante().getNumero()));
        }else if(c.getViajeRemito()!=null){
            c.setViajeRemito(viajeRemitoDAO.findByPuntoVentaAndLetraAndNumero(
                    c.getViajeRemito().getPuntoVenta(), c.getViajeRemito().getLetra(),
                    c.getViajeRemito().getNumero()));
        }
        return c;
    }
    
    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public RetiroDepositoComprobante actualizar(RetiroDepositoComprobante elemento) {
        return elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(RetiroDepositoComprobante elemento) {
        elementoDAO.delete(elemento);
    }
    
}
