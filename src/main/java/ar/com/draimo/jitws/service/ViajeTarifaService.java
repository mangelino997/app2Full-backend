//Paquete al que pertenece el servicio
package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IViajeTarifaDAO;
import ar.com.draimo.jitws.model.ViajeTarifa;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio ViajeTarifa
 * @author blas
 */

@Service
public class ViajeTarifaService {

    //Define la referencia al dao
    @Autowired
    IViajeTarifaDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        ViajeTarifa elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId()+1 : 1;
    }
    
    //Obtiene la lista completa
    public List<ViajeTarifa> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista por nombre
    public List<ViajeTarifa> listarPorNombre(String nombre) {
        return nombre.equals("*")?elementoDAO.findAll():
            elementoDAO.findByNombreContaining(nombre);
    }
    
    //Obtiene una lista por es costo tramo true
    public List<ViajeTarifa> listarPorCostoTramoTrue() {
        return elementoDAO.findByEsCostoPorTramoTrue();
    }
    
    //Obtiene una lista por es costo tramo false
    public List<ViajeTarifa> listarPorCostoTramoFalse() {
        return elementoDAO.findByEsCostoPorTramoFalse();
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public ViajeTarifa agregar(ViajeTarifa elemento) {
        return elementoDAO.saveAndFlush(formatearStrings(elemento));
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(ViajeTarifa elemento) {
        elementoDAO.save(formatearStrings(elemento));
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }
    
    //Formatea los strings
    private ViajeTarifa formatearStrings(ViajeTarifa elemento) {
        elemento.setNombre(elemento.getNombre().trim());
        return elemento;
    }

}