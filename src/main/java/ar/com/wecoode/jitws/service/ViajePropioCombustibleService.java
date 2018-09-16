package ar.com.wecoode.jitws.service;

import ar.com.wecoode.jitws.constant.Funcion;
import ar.com.wecoode.jitws.dao.IViajePropioCombustibleDAO;
import ar.com.wecoode.jitws.model.ViajePropioCombustible;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio ViajePropioCombustible
 * @author blas
 */

@Service
public class ViajePropioCombustibleService {

    //Define la referencia al dao
    @Autowired
    IViajePropioCombustibleDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        ViajePropioCombustible elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento.getId()+1;
    }
    
    //Obtiene la lista completa
    public List<ViajePropioCombustible> listar() {
        return elementoDAO.findAll();
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public ViajePropioCombustible agregar(ViajePropioCombustible elemento) {
        elemento = formatearStrings(elemento);
        return elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(ViajePropioCombustible elemento) {
        elemento = formatearStrings(elemento);
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(ViajePropioCombustible elemento) {
        elementoDAO.delete(elemento);
    }
    
    //Formatea los strings
    private ViajePropioCombustible formatearStrings(ViajePropioCombustible elemento) {
        elemento.setObservaciones(Funcion.primerLetraAMayuscula(elemento.getObservaciones().trim()));
        elemento.setObservacionesAnulado(Funcion.primerLetraAMayuscula(elemento.getObservacionesAnulado().trim()));
        return elemento;
    }

}
