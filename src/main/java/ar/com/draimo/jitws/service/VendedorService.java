//Paquete al que pertenece el servicio
package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IVendedorDAO;
import ar.com.draimo.jitws.model.Vendedor;
import java.sql.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio Vendedor
 *
 * @author blas
 */
@Service
public class VendedorService {

    //Define la referencia al dao
    @Autowired
    IVendedorDAO elementoDAO;

    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        Vendedor elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId() + 1 : 1;
    }

    //Obtiene la lista completa
    public List<Vendedor> listar() {
        return elementoDAO.findAllByOrderByNombreAsc();
    }

    //Obtiene una lista por nombre
    public List<Vendedor> listarPorNombre(String nombre) {
        return nombre.equals("*") ? elementoDAO.findAll()
                : elementoDAO.findByNombreContaining(nombre);
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public Vendedor agregar(Vendedor elemento) {
        elemento.setFechaAlta(new Date(new java.util.Date().getTime()));
        return elementoDAO.saveAndFlush(formatearStrings(elemento));
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(Vendedor elemento) {
        elementoDAO.save(formatearStrings(elemento));
    }

    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }

    //Formatea los strings
    private Vendedor formatearStrings(Vendedor elemento) {
        elemento.setNombre(elemento.getNombre().trim());
        return elemento;
    }

}