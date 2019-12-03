//Paquete al que pertenece el servicio
package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.ISeguimientoSituacionDAO;
import ar.com.draimo.jitws.model.SeguimientoSituacion;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio SeguimientoSituacion
 *
 * @author blas
 */
@Service
public class SeguimientoSituacionService {

    //Define laa referencia al dao
    @Autowired
    ISeguimientoSituacionDAO elementoDAO;

    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        SeguimientoSituacion elemento = elementoDAO.findTopByOrderByIdDesc();
        return (elemento != null ? elemento.getId() + 1 : 1);
    }

    //Obtiene la lista completa
    public List<SeguimientoSituacion> listar() {
        return elementoDAO.findAll();
    }

    //Obtiene una lista por nombre
    public List<SeguimientoSituacion> listarPorNombre(String nombre) {
        return nombre.equals("*") ? elementoDAO.findAll()
                : elementoDAO.findByNombreContaining(nombre);
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public SeguimientoSituacion agregar(SeguimientoSituacion elemento) {
        return elementoDAO.saveAndFlush(formatearString(elemento));
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(SeguimientoSituacion elemento) {
        elementoDAO.save(formatearString(elemento));
    }

    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }

    //Formatea los string 
    private SeguimientoSituacion formatearString(SeguimientoSituacion elemento) {
        elemento.setNombre(elemento.getNombre().trim());
        return elemento;
    }

}