//Paquete al que pertenece el servicio
package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IUnidadMedidaDAO;
import ar.com.draimo.jitws.model.UnidadMedida;
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
public class UnidadMedidaService {

    //Define la referencia al dao
    @Autowired
    IUnidadMedidaDAO elementoDAO;

    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        UnidadMedida elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId() + 1 : 1;
    }

    //Obtiene la lista completa
    public List<UnidadMedida> listar() {
        return elementoDAO.findAllByOrderByNombreAsc();
    }

    //Obtiene una lista por nombre
    public List<UnidadMedida> listarPorNombre(String nombre) {
        return nombre.equals("*") ? elementoDAO.findAll()
                : elementoDAO.findByNombreContaining(nombre);
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public UnidadMedida agregar(UnidadMedida elemento) {
        return elementoDAO.saveAndFlush(formatearStrings(elemento));
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(UnidadMedida elemento) {
        elementoDAO.save(formatearStrings(elemento));
    }

    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }

    //Formatea los strings
    private UnidadMedida formatearStrings(UnidadMedida elemento) {
        elemento.setNombre(elemento.getNombre().trim());
        return elemento;
    }

}