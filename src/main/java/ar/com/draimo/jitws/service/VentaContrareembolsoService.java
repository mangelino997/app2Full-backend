package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IVentaContrareembolsoDAO;
import ar.com.draimo.jitws.model.VentaContrareembolso;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio VentaContrareembolso
 * @author blas
 */

@Service
public class VentaContrareembolsoService {

    //Define la referencia al dao
    @Autowired
    IVentaContrareembolsoDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        VentaContrareembolso elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId()+1 : 1;
    }
    
    //Obtiene la lista completa
    public List<VentaContrareembolso> listar() {
        return elementoDAO.findAll();
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public VentaContrareembolso agregar(VentaContrareembolso elemento) {
        return elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(VentaContrareembolso elemento) {
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(VentaContrareembolso elemento) {
        elementoDAO.delete(elemento);
    }
    
}
