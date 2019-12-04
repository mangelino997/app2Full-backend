//Paquete al que pertenece el servicio
package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.ITipoChequeraDAO;
import ar.com.draimo.jitws.model.TipoChequera;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio de TipoChequera
 *
 * @author blas
 */
@Service
public class TipoChequeraService {

    @Autowired
    ITipoChequeraDAO elementoDAO;

    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        TipoChequera elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId() + 1 : 1;
    }

    //Obtiene la lista completa
    public List<TipoChequera> listar() {
        return elementoDAO.findAll();
    }

    //Obtiene una lista por nombre
    public List<TipoChequera> listarPorNombre(String nombre) {
        return nombre.equals("*") ? elementoDAO.findAll()
                : elementoDAO.findByNombreContaining(nombre);
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public TipoChequera agregar(TipoChequera elemento) {
        return elementoDAO.saveAndFlush(formatearStrings(elemento));
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(TipoChequera elemento) {
        elementoDAO.save(formatearStrings(elemento));
    }

    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }

    //Formatea los strings
    private TipoChequera formatearStrings(TipoChequera elemento) {
        elemento.setNombre(elemento.getNombre().trim());
        return elemento;
    }

}