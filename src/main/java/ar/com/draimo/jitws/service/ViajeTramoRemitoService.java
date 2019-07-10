package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IViajeRemitoDAO;
import ar.com.draimo.jitws.model.ViajeTramoRemito;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ar.com.draimo.jitws.dao.IViajeTramoRemitoDAO;

/**
 *
 * @author blas
 */

@Service
public class ViajeTramoRemitoService {

    @Autowired
    IViajeTramoRemitoDAO elementoDAO;

    @Autowired
    IViajeRemitoDAO viajeRemitoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        ViajeTramoRemito elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId()+1 : 1;
    }
    
    //Obtiene la lista completa
    public List<ViajeTramoRemito> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista por nombre
    public List<ViajeTramoRemito> listarPorViajeRemito(int idRemito) {
        return elementoDAO.findByViajeRemito(viajeRemitoDAO.findById(idRemito).get());
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public ViajeTramoRemito agregar(ViajeTramoRemito elemento) {
        return elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(ViajeTramoRemito elemento) {
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(ViajeTramoRemito elemento) {
        elementoDAO.delete(elemento);
    }

}
