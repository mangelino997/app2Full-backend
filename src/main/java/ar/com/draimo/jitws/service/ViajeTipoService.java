//Paquete al que pertenece el servicio
package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IViajeTipoDAO;
import ar.com.draimo.jitws.model.ViajeTipo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio ViajeTipo
 *
 * @author blas
 */
@Service
public class ViajeTipoService {

    //Define la referencia al dao
    @Autowired
    IViajeTipoDAO elementoDAO;

    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        ViajeTipo elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId() + 1 : 1;
    }

    //Obtiene la lista completa
    public List<ViajeTipo> listar() {
        return elementoDAO.findAll();
    }

    //Obtiene una lista por nombre
    public List<ViajeTipo> listarPorNombre(String nombre) {
        return nombre.equals("*") ? elementoDAO.findAll()
                : elementoDAO.findByNombreContaining(nombre);
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public ViajeTipo agregar(ViajeTipo elemento) {
        return elementoDAO.saveAndFlush(formatearStrings(elemento));
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(ViajeTipo elemento) {
        elementoDAO.save(formatearStrings(elemento));
    }

    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }

    //Formatea los strings
    private ViajeTipo formatearStrings(ViajeTipo elemento) {
        elemento.setNombre(elemento.getNombre().trim());
        elemento.setAbreviatura(elemento.getAbreviatura().trim());
        return elemento;
    }

}