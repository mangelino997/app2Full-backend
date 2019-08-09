package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.ISeguimientoDAO;
import ar.com.draimo.jitws.dao.ISeguimientoEstadoDAO;
import ar.com.draimo.jitws.dao.ISucursalDAO;
import ar.com.draimo.jitws.model.Seguimiento;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio Seguimiento
 * @author blas
 */

@Service
public class SeguimientoService {
    
    //Define la referencia al dao
    @Autowired
    ISeguimientoDAO elementoDAO;
    
    //Define la referencia al dao SeguimientoEstado
    @Autowired
    ISeguimientoEstadoDAO seguimientoEstadoDAO;
    
    //Define la referencia al dao sucursal
    @Autowired
    ISucursalDAO sucursalDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        Seguimiento elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId()+1 : 1;
    }
    
    //Obtiene la lista completa
    public List<Seguimiento> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista por SeguimientoEstado
    public List<Seguimiento> listarPorSeguimientoEstado(int id) {
        return elementoDAO.findBySeguimientoEstado(seguimientoEstadoDAO.findById(id).get());
    }
    
    //Obtiene una lista por sucursal
    public List<Seguimiento> listarPorSucursal(int id) {
        return elementoDAO.findBySucursal(sucursalDAO.findById(id).get());
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public Seguimiento agregar(Seguimiento elemento) {
        return elementoDAO.saveAndFlush(elemento);
    }
    
    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(Seguimiento elemento) {
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }
    
}
