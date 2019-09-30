//Paquete al que pertenece el servicio
package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IQuincenaDAO;
import ar.com.draimo.jitws.model.Quincena;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio Quincena
 *
 * @author blas
 */
@Service
public class QuincenaService {

    //Define la referencia al dao
    @Autowired
    IQuincenaDAO elementoDAO;

    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        Quincena elemento = elementoDAO.findTopByOrderByIdDesc();
        return (elemento != null ? elemento.getId() + 1 : 1);
    }

    //Obtiene la lista completa
    public List<Quincena> listar() {
        return elementoDAO.findAll();
    }

    //Obtiene una lista por nombre
    public List<Quincena> listarPorNombre(String nombre) {
        return nombre.equals("***") ? elementoDAO.findAll()
                : elementoDAO.findByNombreContaining(nombre);
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public Quincena agregar(Quincena elemento) {
        elemento = formatearStrings(elemento);
        return elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(Quincena elemento) {
        elemento = formatearStrings(elemento);
        elementoDAO.save(elemento);
    }

    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }

    //Formatea los strings
    private Quincena formatearStrings(Quincena elemento) {
        elemento.setNombre(elemento.getNombre().trim());
        return elemento;
    }

}
