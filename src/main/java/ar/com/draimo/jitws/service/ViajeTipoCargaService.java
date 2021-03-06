//Paqeute al que pertenece el servicio
package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IViajeTipoCargaDAO;
import ar.com.draimo.jitws.model.ViajeTipoCarga;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio ViajeTipoCarga
 *
 * @author blas
 */
@Service
public class ViajeTipoCargaService {

    //Define la referencia al dao
    @Autowired
    IViajeTipoCargaDAO elementoDAO;

    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        ViajeTipoCarga elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId() + 1 : 1;
    }

    //Obtiene la lista completa
    public List<ViajeTipoCarga> listar() {
        return elementoDAO.findAll();
    }

    //Obtiene una lista por nombre
    public List<ViajeTipoCarga> listarPorNombre(String nombre) {
        return nombre.equals("*") ? elementoDAO.findAll()
                : elementoDAO.findByNombreContaining(nombre);
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public ViajeTipoCarga agregar(ViajeTipoCarga elemento) {
        return elementoDAO.saveAndFlush(formatearStrings(elemento));
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(ViajeTipoCarga elemento) {
        elementoDAO.save(formatearStrings(elemento));
    }

    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }

    //Formatea los strings
    private ViajeTipoCarga formatearStrings(ViajeTipoCarga elemento) {
        elemento.setNombre(elemento.getNombre().trim());
        return elemento;
    }

}