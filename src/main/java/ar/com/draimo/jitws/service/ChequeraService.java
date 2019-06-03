package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IChequeraDAO;
import ar.com.draimo.jitws.dao.ICuentaBancariaDAO;
import ar.com.draimo.jitws.model.Chequera;
import ar.com.draimo.jitws.model.CuentaBancaria;
import java.sql.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author blas
 */

@Service
public class ChequeraService {

    @Autowired
    IChequeraDAO elementoDAO;

    @Autowired
    ICuentaBancariaDAO cuentaBancariaDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        Chequera elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId()+1 : 1;
    }
    
    //Obtiene la lista completa
    public List<Chequera> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista por CuentaBancaria
    public List<Chequera> listarPorCuentaBancaria(int idCtaBancaria) {
        CuentaBancaria cuentaBancaria = cuentaBancariaDAO.findById(idCtaBancaria).get();
            return elementoDAO.findByCuentaBancaria(cuentaBancaria);
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public Chequera agregar(Chequera elemento) {
        Date fecha = new Date(new java.util.Date().getTime());
        elemento.setFechaAlta(fecha);
        return elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(Chequera elemento) {
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(Chequera elemento) {
        elementoDAO.delete(elemento);
    }
    
}
