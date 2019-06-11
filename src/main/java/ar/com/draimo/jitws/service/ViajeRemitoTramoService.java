package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IViajeRemitoDAO;
import ar.com.draimo.jitws.dao.IViajeRemitoTramoDAO;
import ar.com.draimo.jitws.model.ViajeRemitoTramo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author blas
 */

@Service
public class ViajeRemitoTramoService {

    @Autowired
    IViajeRemitoTramoDAO elementoDAO;

    @Autowired
    IViajeRemitoDAO viajeRemitoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        ViajeRemitoTramo elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId()+1 : 1;
    }
    
    //Obtiene la lista completa
    public List<ViajeRemitoTramo> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista por nombre
    public List<ViajeRemitoTramo> listarPorViajeRemito(int idRemito) {
        return elementoDAO.findByViajeRemito(viajeRemitoDAO.findById(idRemito).get());
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public ViajeRemitoTramo agregar(ViajeRemitoTramo elemento) {
        return elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(ViajeRemitoTramo elemento) {
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(ViajeRemitoTramo elemento) {
        elementoDAO.delete(elemento);
    }

}
