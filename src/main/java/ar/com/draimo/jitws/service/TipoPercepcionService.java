//Paquete al que pertenece el servicio
package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.ITipoPercepcionDAO;
import ar.com.draimo.jitws.model.TipoPercepcion;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servvicio de TipoPercepcion
 *
 * @author blas
 */
@Service
public class TipoPercepcionService {

    //Define la referencia al dao
    @Autowired
    ITipoPercepcionDAO elementoDAO;

    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        TipoPercepcion elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId() + 1 : 1;
    }

    //Obtiene la lista completa
    public List<TipoPercepcion> listar() {
        return elementoDAO.findAll();
    }

    //Obtiene una lista por nombre
    public List<TipoPercepcion> listarPorNombre(String nombre) {
        return nombre.equals("*") ? elementoDAO.findAll()
                : elementoDAO.findByNombreContaining(nombre);
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public TipoPercepcion agregar(TipoPercepcion elemento) {
        return elementoDAO.saveAndFlush(formatearStrings(elemento));
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(TipoPercepcion elemento) {
        elementoDAO.save(formatearStrings(elemento));
    }

    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }

    //Formatea los strings
    private TipoPercepcion formatearStrings(TipoPercepcion elemento) {
        elemento.setNombre(elemento.getNombre().trim());
        return elemento;
    }

}