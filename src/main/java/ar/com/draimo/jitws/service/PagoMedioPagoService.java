//Paquete al que pertenece el servicio
package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IPagoMedioPagoDAO;
import ar.com.draimo.jitws.dao.IPagoDAO;
import ar.com.draimo.jitws.model.PagoMedioPago;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio de PagoMedioPago
 * @author blas
 */

@Service
public class PagoMedioPagoService {

    //Define la referencia al DAO
    @Autowired
    IPagoMedioPagoDAO elementoDAO;
    
    //Define la referencia al DAO de empresa
    @Autowired
    IPagoDAO pagoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        PagoMedioPago elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId()+1 : 1;
    }
    
    //Obtiene la lista completa
    public List<PagoMedioPago> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista por pago 
    public List<PagoMedioPago> listarPorPago(int idPago) {
            return elementoDAO.findByPago(pagoDAO.findById(idPago).get());
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public PagoMedioPago agregar(PagoMedioPago elemento) throws Exception {
        return elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(PagoMedioPago elemento) throws Exception {
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }
    
}