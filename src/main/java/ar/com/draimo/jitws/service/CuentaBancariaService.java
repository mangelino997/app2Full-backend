package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.ICuentaBancariaDAO;
import ar.com.draimo.jitws.dao.IEmpresaDAO;
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
public class CuentaBancariaService {

    @Autowired
    ICuentaBancariaDAO elementoDAO;

    @Autowired
    IEmpresaDAO empresaDAO;

    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        CuentaBancaria elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId() + 1 : 1;
    }

    //Obtiene la lista completa
    public List<CuentaBancaria> listar() {
        return elementoDAO.findAll();
    }

    //Obtiene una lista por empresa
    public List<CuentaBancaria> listarPorEmpresa(int idEmpresa) {
        return elementoDAO.listarPorEmpresa(idEmpresa);
    }

    //Obtiene una lista por empresa
    public List<CuentaBancaria> listarConChequerasPorEmpresa(int idEmpresa) {
        return elementoDAO.listarConChequerasPorEmpresa(idEmpresa);
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public CuentaBancaria agregar(CuentaBancaria elemento) {
        elemento.setFechaAlta(new Date(new java.util.Date().getTime()));
        return elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(CuentaBancaria elemento) {
        elementoDAO.save(elemento);
    }

    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(CuentaBancaria elemento) {
        elementoDAO.delete(elemento);
    }

}