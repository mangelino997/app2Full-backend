package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IChequeraDAO;
import ar.com.draimo.jitws.dao.ICuentaBancariaDAO;
import ar.com.draimo.jitws.dao.IEmpresaDAO;
import ar.com.draimo.jitws.model.Chequera;
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
    
    @Autowired
    IEmpresaDAO empresaDAO;
    
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
    public List<Chequera> listarPorEmpresa(int idEmpresa) {
            return elementoDAO.listarPorEmpresa(idEmpresa);
    }
    
    //Obtiene una lista por CuentaBancaria
    public List<Chequera> listarPorCuentaBancaria(int idCuentaBancaria) {
            return elementoDAO.findByCuentaBancaria(cuentaBancariaDAO.findById(idCuentaBancaria).get());
    }
    
    //Obtiene una lista por CuentaBancaria
    public List<Chequera> validarDesdeHasta(int idCuentaBancaria, int idTipoChequera,int desdeHasta) {
            return elementoDAO.listarPorCtaBancariaTipoChequeraDesdeHasta(idCuentaBancaria, idTipoChequera, desdeHasta);
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public Chequera agregar(Chequera elemento) throws Exception {
        Date fecha = new Date(new java.util.Date().getTime());
        elemento.setFechaAlta(fecha);
        if(elemento.getDesde()>elemento.getHasta()) {
            throw new Exception("'Hasta' no puede ser mayor a 'Desde'");
        }
        List<Chequera> chequerasDesde = validarDesdeHasta(
                elemento.getCuentaBancaria().getId(), elemento.getTipoChequera().getId(),
                elemento.getDesde());
        List<Chequera> chequerasHasta = validarDesdeHasta(
                elemento.getCuentaBancaria().getId(), elemento.getTipoChequera().getId(),
                elemento.getHasta());
        if(!chequerasDesde.isEmpty()) {
            throw new Exception("Para cuenta bancaria-tipo chequera: desde existente");
        }
        if(!chequerasHasta.isEmpty()) {
            throw new Exception("Para cuenta bancaria-tipo chequera: hasta existente");
        }
        return elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(Chequera elemento) throws Exception {
        if(elemento.getDesde()>elemento.getHasta()) {
            throw new Exception("'Hasta' no puede ser mayor a 'Desde'");
        }
        List<Chequera> chequerasDesde = validarDesdeHasta(
                elemento.getCuentaBancaria().getId(), elemento.getTipoChequera().getId(),
                elemento.getDesde());
        List<Chequera> chequerasHasta = validarDesdeHasta(
                elemento.getCuentaBancaria().getId(), elemento.getTipoChequera().getId(),
                elemento.getHasta());
        if(!chequerasDesde.isEmpty()) {
            throw new Exception("Para cuenta bancaria-tipo chequera: desde existente");
        }
        if(!chequerasHasta.isEmpty()) {
            throw new Exception("Para cuenta bancaria-tipo chequera: hasta existente");
        }
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(Chequera elemento) {
        elementoDAO.delete(elemento);
    }
    
}
