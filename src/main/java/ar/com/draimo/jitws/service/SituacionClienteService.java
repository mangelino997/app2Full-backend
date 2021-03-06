//Paquete al que pertenece el servicio
package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.ISituacionClienteDAO;
import ar.com.draimo.jitws.model.SituacionCliente;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio SituacionCliente
 *
 * @author blas
 */
@Service
public class SituacionClienteService {

    //Define la referencia al dao
    @Autowired
    ISituacionClienteDAO elementoDAO;

    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        SituacionCliente elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId() + 1 : 1;
    }

    //Obtiene la lista completa
    public List<SituacionCliente> listar() {
        return elementoDAO.findAll();
    }

    //Obtiene una lista por nombre
    public List<SituacionCliente> listarPorNombre(String nombre) {
        return nombre.equals("*") ? elementoDAO.findAll()
                : elementoDAO.findByNombreContaining(nombre);
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public SituacionCliente agregar(SituacionCliente elemento) {
        return elementoDAO.saveAndFlush(formatearStrings(elemento));
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(SituacionCliente elemento) {
        elementoDAO.save(formatearStrings(elemento));
    }

    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }

    //Formatea los strings
    private SituacionCliente formatearStrings(SituacionCliente elemento) {
        elemento.setNombre(elemento.getNombre().trim());
        return elemento;
    }

}