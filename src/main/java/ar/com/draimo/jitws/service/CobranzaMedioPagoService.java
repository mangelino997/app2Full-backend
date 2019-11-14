//Paquete al que pertenece el servicio
package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.ICobranzaMedioPagoDAO;
import ar.com.draimo.jitws.dao.ICobranzaDAO;
import ar.com.draimo.jitws.model.CobranzaMedioPago;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio de CobranzaMedioPago
 * @author blas
 */

@Service
public class CobranzaMedioPagoService {

    //Define la referencia al DAO
    @Autowired
    ICobranzaMedioPagoDAO elementoDAO;
    
    //Define la referencia al DAO de empresa
    @Autowired
    ICobranzaDAO cobranzaDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        CobranzaMedioPago elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId()+1 : 1;
    }
    
    //Obtiene la lista completa
    public List<CobranzaMedioPago> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista por Cobranza 
    public List<CobranzaMedioPago> listarPorCobranza(int idCobranza) {
            return elementoDAO.findByCobranza(cobranzaDAO.findById(idCobranza).get());
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public CobranzaMedioPago agregar(CobranzaMedioPago elemento) throws Exception {
        return elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(CobranzaMedioPago elemento) throws Exception {
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }
    
}