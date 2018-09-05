package ar.com.wecoode.jitws.service;

import ar.com.wecoode.jitws.dao.ITipoCuentaBancariaDAO;
import ar.com.wecoode.jitws.model.TipoCuentaBancaria;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio TipoCuentaBancaria
 * @author blas
 */

@Service
public class TipoCuentaBancariaService {

    //Define la referencia al dao
    @Autowired
    ITipoCuentaBancariaDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        return elementoDAO.obtenerSiguienteId();
    }
    
    //Obtiene la lista completa
    public List<TipoCuentaBancaria> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista por nombre
    public List<TipoCuentaBancaria> listarPorNombre(String nombre) {
        return elementoDAO.findByNombreContaining(nombre);
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public void agregar(TipoCuentaBancaria elemento) {
        elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(TipoCuentaBancaria elemento) {
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(TipoCuentaBancaria elemento) {
        elementoDAO.delete(elemento);
    }

}
