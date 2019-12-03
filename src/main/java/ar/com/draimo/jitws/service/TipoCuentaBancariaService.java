//Paquete al que pertenece el servicio
package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.ITipoCuentaBancariaDAO;
import ar.com.draimo.jitws.model.TipoCuentaBancaria;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio TipoCuentaBancaria
 *
 * @author blas
 */
@Service
public class TipoCuentaBancariaService {

    //Define la referencia al dao
    @Autowired
    ITipoCuentaBancariaDAO elementoDAO;

    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        TipoCuentaBancaria elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId() + 1 : 1;
    }

    //Obtiene la lista completa
    public List<TipoCuentaBancaria> listar() {
        return elementoDAO.findAll();
    }

    //Obtiene una lista por nombre
    public List<TipoCuentaBancaria> listarPorNombre(String nombre) {
        return nombre.equals("*") ? elementoDAO.findAll()
                : elementoDAO.findByNombreContaining(nombre);
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public TipoCuentaBancaria agregar(TipoCuentaBancaria elemento) {
        return elementoDAO.saveAndFlush(formatearStrings(elemento));
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(TipoCuentaBancaria elemento) {
        elementoDAO.save(formatearStrings(elemento));
    }

    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }

    //Formatea los strings
    private TipoCuentaBancaria formatearStrings(TipoCuentaBancaria elemento) {
        elemento.setNombre(elemento.getNombre().trim());
        return elemento;
    }

}