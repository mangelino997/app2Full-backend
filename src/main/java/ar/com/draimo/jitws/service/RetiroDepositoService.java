package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IEmpresaDAO;
import ar.com.draimo.jitws.dao.IRetiroDepositoDAO;
import ar.com.draimo.jitws.model.Empresa;
import ar.com.draimo.jitws.model.RetiroDeposito;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio RetiroDeposito
 * @author blas
 */

@Service
public class RetiroDepositoService {

    //Define la referencia al dao
    @Autowired
    IRetiroDepositoDAO elementoDAO;
    
    //Define la referencia al dao empresa
    @Autowired
    IEmpresaDAO empresaDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        RetiroDeposito elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId()+1 : 1;
    }
    
    //Obtiene la lista completa
    public List<RetiroDeposito> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene la lista de planillas abiertas
    public List<RetiroDeposito> listarPorEstaCerrada(boolean estaCerrada) {
        return elementoDAO.listarPorEstaCerrada(estaCerrada);
    }
    
    //Cierra un reparto
    public void cerrarReparto() {
    }
    
    //Obtiene una lista por empresa
    public List<RetiroDeposito> listarPorEmpresa(int id) {
        Optional<Empresa> elemento = empresaDAO.findById(id);
        return elementoDAO.findByEmpresa(elemento);
    }
    
    //Obtiene por numeroDocumento
    public List<RetiroDeposito> obtenerPorNumeroDocumento(String elemento) {
        return elementoDAO.findByNumeroDocumento(elemento);
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public RetiroDeposito agregar(RetiroDeposito elemento) {
        elemento = formatearStrings(elemento);
        return elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(RetiroDeposito elemento) {
        elemento = formatearStrings(elemento);
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(RetiroDeposito elemento) {
        elementoDAO.delete(elemento);
    }
    
    private RetiroDeposito formatearStrings(RetiroDeposito e) {
        e.setNumeroDocumento(e.getNumeroDocumento().trim());
        return e;
    }

}
