//Paquete al que pertenece el servicio
package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.ISoporteEstadoDAO;
import ar.com.draimo.jitws.model.SoporteEstado;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio SeguimientoEstado
 *
 * @author blas
 */
@Service
public class SoporteEstadoService {

    //Define la referencia al dao
    @Autowired
    ISoporteEstadoDAO elementoDAO;

    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        SoporteEstado elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId() + 1 : 1;
    }

    //Obtiene la lista completa
    public List<SoporteEstado> listar() {
        return elementoDAO.findAll();
    }

    //Obtiene una lista por nombre
    public List<SoporteEstado> listarPorNombre(String nombre) {
        return nombre.equals("***") ? elementoDAO.findAll()
                : elementoDAO.findByNombreContaining(nombre);
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public SoporteEstado agregar(SoporteEstado elemento) {
        return elementoDAO.saveAndFlush(formatearStrings(elemento));
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(SoporteEstado elemento) {
        elementoDAO.save(formatearStrings(elemento));
    }

    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }

    //Formatea los strings
    private SoporteEstado formatearStrings(SoporteEstado elemento) {
        elemento.setNombre(elemento.getNombre().trim());
        return elemento;
    }

}