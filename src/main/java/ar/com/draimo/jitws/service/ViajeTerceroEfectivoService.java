package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IViajeTerceroEfectivoDAO;
import ar.com.draimo.jitws.model.ViajeTerceroEfectivo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio ViajeTerceroEfectivo
 * @author blas
 */

@Service
public class ViajeTerceroEfectivoService {

    //Define la referencia al dao
    @Autowired
    IViajeTerceroEfectivoDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        ViajeTerceroEfectivo elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId()+1 : 1;
    }
    
    //Obtiene la lista completa
    public List<ViajeTerceroEfectivo> listar() {
        return elementoDAO.findAll();
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public ViajeTerceroEfectivo agregar(ViajeTerceroEfectivo elemento) {
        elemento = formatearStrings(elemento);
        return elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(ViajeTerceroEfectivo elemento) {
        elemento = formatearStrings(elemento);
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(ViajeTerceroEfectivo elemento) {
        elementoDAO.delete(elemento);
    }
    
    //Formatea los strings
    private ViajeTerceroEfectivo formatearStrings(ViajeTerceroEfectivo elemento) {
        if(elemento.getObservaciones() != null) {
            elemento.setObservaciones(elemento.getObservaciones().trim());
        }
        if(elemento.getObservacionesAnulado() != null) {
            elemento.setObservacionesAnulado(elemento.getObservacionesAnulado().trim());
        }
        return elemento;
    }

}
