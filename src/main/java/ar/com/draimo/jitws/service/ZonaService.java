//Paquete al que pertenece el servicio
package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IZonaDAO;
import ar.com.draimo.jitws.model.Zona;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio Zona
 *
 * @author blas
 */
@Service
public class ZonaService {

    //Define la referencia al dao
    @Autowired
    IZonaDAO elementoDAO;

    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        Zona elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId() + 1 : 1;
    }

    //Obtiene la lista completa
    public List<Zona> listar() {
        return elementoDAO.findAllByOrderByNombreAsc();
    }

    //Obtiene la lista completa ordenada
    public List<Zona> listarOrdenado(String elemento) {
        return elemento.equals("nombre") ? elementoDAO.findAllByOrderByNombreAsc()
                : elemento.equals("id") ? elementoDAO.findByOrderByIdAsc()
                : elementoDAO.findAll();
    }

    //Obtiene una lista por nombre
    public List<Zona> listarPorNombre(String nombre) {
        return nombre.equals("*") ? elementoDAO.findAllByOrderByNombreAsc()
                : elementoDAO.findByNombreContaining(nombre);
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public Zona agregar(Zona elemento) {
        return elementoDAO.saveAndFlush(formatearStrings(elemento));
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(Zona elemento) {
        elementoDAO.save(formatearStrings(elemento));
    }

    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }

    //Formatea los strings
    private Zona formatearStrings(Zona elemento) {
        elemento.setNombre(elemento.getNombre().trim());
        return elemento;
    }

}
