//Paquete al que pertenece el servicio
package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IPaisDAO;
import ar.com.draimo.jitws.model.Pais;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio Pais
 *
 * @author blas
 */
@Service
public class PaisService {

    //Define la referencia al dao
    @Autowired
    IPaisDAO elementoDAO;

    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        Pais elemento = elementoDAO.findTopByOrderByIdDesc();
        return (elemento != null ? elemento.getId() + 1 : 1);
    }

    //Obtiene la lista completa
    public List<Pais> listar() {
        return elementoDAO.findAll();
    }

    //Obtiene una lista por nombre
    public List<Pais> listarPorNombre(String nombre) {
        return nombre.equals("***") ? elementoDAO.findAll()
                : elementoDAO.findByNombreContaining(nombre);
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public Pais agregar(Pais elemento) {
        elemento = formatearStrings(elemento);
        return elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(Pais elemento) {
        elemento = formatearStrings(elemento);
        elementoDAO.save(elemento);
    }

    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }

    //Formatea los strings
    private Pais formatearStrings(Pais elemento) {
        elemento.setNombre(elemento.getNombre().trim());
        return elemento;
    }

}
