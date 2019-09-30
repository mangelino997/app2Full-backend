//Paquete al que pertenece el servicio
package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IMonedaCotizacionDAO;
import ar.com.draimo.jitws.dao.IMonedaDAO;
import ar.com.draimo.jitws.model.MonedaCotizacion;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio MonedaCotizacion
 * @author blas
 */

@Service
public class MonedaCotizacionService {

    //Define la referencia al dao
    @Autowired
    IMonedaCotizacionDAO elementoDAO;
    
    //Define la referencia al dao moneda
    @Autowired
    IMonedaDAO monedaDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        MonedaCotizacion elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId()+1 : 1;
    }
    
    //Obtiene la lista completa
    public List<MonedaCotizacion> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista por moneda
    public List<MonedaCotizacion> listarPorMoneda(int id) {
        return elementoDAO.listarPorMoneda(id);
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public MonedaCotizacion agregar(MonedaCotizacion elemento) throws Exception {
        MonedaCotizacion monedaCotizacion;
        try {
            monedaCotizacion = elementoDAO.findByMonedaAndFecha(elemento.getMoneda(), elemento.getFecha());
            monedaCotizacion = elementoDAO.saveAndFlush(elemento);
        } catch(Exception e) {
            throw new Exception("1");
        }
        return monedaCotizacion;
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(MonedaCotizacion elemento) throws Exception {
        MonedaCotizacion monedaCotizacion = elementoDAO.findByMonedaAndFecha(elemento.getMoneda(), elemento.getFecha());
        if(elemento.getId() != monedaCotizacion.getId()) {
            throw new Exception("1");
        }
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int id) {
        elementoDAO.deleteById(id);
    }

}
