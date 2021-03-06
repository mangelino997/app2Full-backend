//Paquete al que pertenece a el servicio
package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.ICuentaBancariaDAO;
import ar.com.draimo.jitws.dao.IEmpresaDAO;
import ar.com.draimo.jitws.dao.IMonedaDAO;
import ar.com.draimo.jitws.dao.ITipoCuentaBancariaDAO;
import ar.com.draimo.jitws.dto.InitCuentaBancariaDTO;
import ar.com.draimo.jitws.model.CuentaBancaria;
import java.sql.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio de cuentaBancaria
 * @author blas
 */
@Service
public class CuentaBancariaService {

    //Referencia al DAO
    @Autowired
    ICuentaBancariaDAO elementoDAO;

    //Referencia al DAO de empresa
    @Autowired
    IEmpresaDAO empresaDAO;

    //Referencia al DAO de moneda
    @Autowired
    IMonedaDAO monedaDAO;

    //Referencia al DAO de tipoCuentaBancariaDAO
    @Autowired
    ITipoCuentaBancariaDAO tipoCuentaBancariaDAO;

    //Referencia al service de subpocionpestania
    @Autowired
    SubopcionPestaniaService subopcionPestaniaService;

    //Obtiene los listados necesarios para inicializar el componente
    public InitCuentaBancariaDTO inicializar(int idRol, int idSubopcion) {
        InitCuentaBancariaDTO p = new InitCuentaBancariaDTO();
        p.setMonedas(monedaDAO.findAll());
        p.setTipoCuentaBancarias(tipoCuentaBancariaDAO.findAll());
        p.setUltimoId(obtenerSiguienteId());
        p.setPestanias(subopcionPestaniaService.listarPestaniasPorRolYSubopcion(idRol, idSubopcion));
        return p;
    }

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

    //Obtiene una lista por chequeras por empresa
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
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }

}