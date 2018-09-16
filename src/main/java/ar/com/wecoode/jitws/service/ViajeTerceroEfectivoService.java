package ar.com.wecoode.jitws.service;

import ar.com.wecoode.jitws.constant.Funcion;
import ar.com.wecoode.jitws.dao.IViajeTerceroEfectivoDAO;
import ar.com.wecoode.jitws.model.ViajeTerceroEfectivo;
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
        return elemento.getId()+1;
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
        elemento.setObservaciones(Funcion.primerLetraAMayuscula(elemento.getObservaciones().trim()));
        elemento.setObservacionesAnulado(Funcion.primerLetraAMayuscula(elemento.getObservacionesAnulado().trim()));
        return elemento;
    }

}
