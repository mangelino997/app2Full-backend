package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IViajeRemitoDAO;
import ar.com.draimo.jitws.dao.IViajeRemitoSeguimientoDAO;
import ar.com.draimo.jitws.dao.ISeguimientoEstadoDAO;
import ar.com.draimo.jitws.model.ViajeRemito;
import ar.com.draimo.jitws.model.ViajeRemitoSeguimiento;
import ar.com.draimo.jitws.model.SeguimientoEstado;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio de ViajeRemitoSeguimiento
 * @author blas
 */

@Service
public class ViajeRemitoSeguimientoService {
    
    //Define el dao
    @Autowired
    IViajeRemitoSeguimientoDAO elementoDAO;
    
    //Define el dao de seguimiento estado
    @Autowired
    ISeguimientoEstadoDAO seguimientoEstadoDAO;
    
    //Define el dao de ordenRecoleccion
    @Autowired
    IViajeRemitoDAO viajeRemitoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        ViajeRemitoSeguimiento elemento = elementoDAO.findTopByOrderByIdDesc();
        return (elemento!=null?elemento.getId()+1:1);
    }
    
    //Obtiene la lista completa
    public List<ViajeRemitoSeguimiento> listar() {
        return elementoDAO.findByOrderByFechaDesc();
    }
    
    //Obtiene una lista por OrdenRecoleccion
    public List<ViajeRemitoSeguimiento> listarPorViajeRemito(int idViajeRemito) {
        ViajeRemito v = viajeRemitoDAO.findById(idViajeRemito).get();
        return elementoDAO.findByViajeRemito(v);
    }
    
    //Obtiene una lista por SeguimientoEstado
    public List<ViajeRemitoSeguimiento> listarPorSeguimientoEstado(int idSeguimientoEstado) {
        SeguimientoEstado s = seguimientoEstadoDAO.findById(idSeguimientoEstado).get();
        return elementoDAO.findBySeguimientoEstado(s);
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public ViajeRemitoSeguimiento agregar(ViajeRemitoSeguimiento elemento) {
        return elementoDAO.save(elemento);
    }
    
    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(ViajeRemitoSeguimiento elemento) {
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }
    
}
