//Paquete al que pertenece el servicio
package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IViajeCierreDocumentacionDAO;
import ar.com.draimo.jitws.model.ViajeCierreDocumentacion;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio ViajeCierreDocumentacion
 * @author blas
 */
@Service
public class ViajeCierreDocumentacionService {

    //Define la referencia al dao
    @Autowired
    IViajeCierreDocumentacionDAO elementoDAO;

    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        ViajeCierreDocumentacion elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId() + 1 : 1;
    }

    //Obtiene la lista completa
    public List<ViajeCierreDocumentacion> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene el ultimo cierre de documentacion de un vehiculo
    public ViajeCierreDocumentacion obtenerUltimoCierreDeVehiculo(int idVehiculo) {
        return elementoDAO.obtenerUltimoCierreDeVehiculo(idVehiculo);
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public ViajeCierreDocumentacion agregar(ViajeCierreDocumentacion elemento) {
        return elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(ViajeCierreDocumentacion elemento) {
        elementoDAO.save(elemento);
    }

    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int id) {
        elementoDAO.deleteById(id);
    }

}