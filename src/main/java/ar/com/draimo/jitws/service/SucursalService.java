package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.ISucursalDAO;
import ar.com.draimo.jitws.model.Sucursal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio Sucursal
 *
 * @author blas
 */
@Service
public class SucursalService {

    //Define la referencia al dao
    @Autowired
    ISucursalDAO elementoDAO;

    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        Sucursal elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId() + 1 : 1;
    }

    //Obtiene la lista completa
    public List<Sucursal> listar() {
        return elementoDAO.findAll();
    }

    //Obtiene una lista por nombre
    public List<Sucursal> listarPorNombre(String nombre) {
        return nombre.equals("*") ? elementoDAO.findAll()
                : elementoDAO.findByNombreContaining(nombre);
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public Sucursal agregar(Sucursal elemento) {
        return elementoDAO.saveAndFlush(formatearStrings(elemento));
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(Sucursal elemento) {
        elementoDAO.save(formatearStrings(elemento));
    }

    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }

    //Formatea los strings
    private Sucursal formatearStrings(Sucursal elemento) {
        elemento.setNombre(elemento.getNombre().trim());
        elemento.setDomicilio(elemento.getDomicilio().trim());
        return elemento;
    }

}